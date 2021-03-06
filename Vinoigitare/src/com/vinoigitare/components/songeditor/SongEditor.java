package com.vinoigitare.components.songeditor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.data.Validator;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vinoigitare.Constants;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.events.SongSelectedEvent;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;

@SuppressWarnings("serial")
public class SongEditor extends Window {

	private static final String REGEX_VALID_INPUT = "[[A-Z][0-9][�Ǝ���枚�]].*";
	private Song song;
	private PropertysetItem songItem;
	private TextField artistField;
	private TextField titleField;
	private TextArea chordsArea;

	private final static Log log = LogFactory
			.getLog(SongEditor.class.getName());

	public SongEditor(Song song) {
		this.song = song;

		setSizeFull();
		center();
		
		setCaption("Edit song");
		setCloseShortcut(KeyCode.ESCAPE);
		
		Layout layout = createLayout();
		setContent(layout);

	}

	private Layout createLayout() {

		VerticalLayout layout = new VerticalLayout();

		songItem = getItemFromSong(song);
		layout.setMargin(true);

		Panel formPanel = new Panel();
		formPanel.addStyleName("songeditor");

		FormLayout form = new FormLayout();

		artistField = getArtistTextField(songItem);
		form.addComponent(artistField);

		titleField = getTitleTextField(songItem);
		form.addComponent(titleField);

		chordsArea = getChordsTextArea(songItem);
		form.addComponent(chordsArea);

		formPanel.setContent(form);

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(getButtonOk());
		buttons.addComponent(getButtonCancel());
		buttons.addStyleName(Constants.STYLE_CONFIRMATION_BUTTONS);

		layout.addComponent(formPanel);
		layout.addComponent(buttons);

		return layout;
	}

	private TextField getArtistTextField(PropertysetItem songItem) {
		TextField field = new TextField("Artist",
				songItem.getItemProperty("artist"));
		field.setRequired(true);
		field.addValidator(new Validator() {

			@Override
			public void validate(Object value) throws InvalidValueException {
				String input = (String) value;
				if (!input.matches(REGEX_VALID_INPUT)) {
					throw new InvalidValueException("Invalid artist name: "
							+ input);
				}

			}

		});
		return field;
	}

	private TextField getTitleTextField(PropertysetItem songItem) {
		TextField field = new TextField("Title",
				songItem.getItemProperty("title"));
		field.setRequired(true);
		field.addValidator(new Validator() {

			@Override
			public void validate(Object value) throws InvalidValueException {
				String input = (String) value;
				if (!input.matches(REGEX_VALID_INPUT)) {
					throw new InvalidValueException("Invalid title: " + input);
				}

			}

		});
		return field;
	}

	private TextArea getChordsTextArea(PropertysetItem songItem) {
		TextArea chordsTextArea = new TextArea("Chords",
				songItem.getItemProperty("chords"));
		chordsTextArea.setRequired(true);
		chordsTextArea.setWidth("80em");
		chordsTextArea.setHeight(25f, Unit.EX);

		chordsTextArea.addStyleName("song-chords");

		return chordsTextArea;
	}

	private Button getButtonOk() {
		Button button = new Button("OK");

		ClickListener clickListener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					artistField.validate();
					titleField.validate();
					chordsArea.validate();

					if (song == null) {
						onSongCreate();
					} else {
						onSongUpdate();
					}

				} catch (Exception e) {
					Notification.show("Please fill the fields properly."
							+ e.getMessage());
					e.printStackTrace();
				}

			}

		};

		button.addClickListener(clickListener);
		return button;
	}

	protected void onSongCreate() {

		song = getSongFromItem(songItem);

		Vinoigitare vinoigitare = (Vinoigitare) getUI();
		SongService songService = vinoigitare.getSongService();
		try {
			songService.store(song);
		} catch (SongServiceException e) {
			Notification.show("Could not store song: " + song, e.getMessage(),
					Type.WARNING_MESSAGE);
			e.printStackTrace();
		}

		Notification.show("Song created: " + song);
		vinoigitare.getEventBus().publish(new SongSelectedEvent(song));
		close();
	}

	protected void onSongUpdate() {

		Song previousVersion = new Song(song.getArtist(), song.getTitle(),
				song.getChords());
		song = getSongFromItem(songItem);

		Vinoigitare vinoigitare = (Vinoigitare) getUI();
		SongService songService = vinoigitare.getSongService();
		try {
			songService.remove(previousVersion.getId());
			songService.store(song);
		} catch (SongServiceException e) {
			Notification.show("Could not update song: " + previousVersion,
					e.getMessage(), Type.WARNING_MESSAGE);
			e.printStackTrace();
		}

		Notification.show("Song updated: " + song);
		vinoigitare.getEventBus().publish(new SongSelectedEvent(song));
	}

	private Button getButtonCancel() {
		Button button = new Button("Cancel");

		ClickListener clickListener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					close();
					log.trace("Song edit cancelled: " + song);
				} catch (Exception e) {
					Notification.show(e.getMessage());
					e.printStackTrace();
				}

			}
		};

		button.addClickListener(clickListener);
		return button;
	}

	private PropertysetItem getItemFromSong(Song song) {
		PropertysetItem songItem = new PropertysetItem();
		if (song == null) {
			songItem.addItemProperty("artist", new ObjectProperty<String>(""));
			songItem.addItemProperty("title", new ObjectProperty<String>(""));
			songItem.addItemProperty("chords", new ObjectProperty<String>(""));
			return songItem;
		}

		songItem.addItemProperty("artist",
				new ObjectProperty<String>(song.getArtist()));
		songItem.addItemProperty("title",
				new ObjectProperty<String>(song.getTitle()));
		songItem.addItemProperty("chords",
				new ObjectProperty<String>(song.getChords()));
		return songItem;
	}

	private Song getSongFromItem(PropertysetItem item) {
		Song song = new Song();

		String artist = (String) songItem.getItemProperty("artist").getValue();
		String title = (String) songItem.getItemProperty("title").getValue();
		String chords = (String) songItem.getItemProperty("chords").getValue();

		song.setArtist(artist);
		song.setTitle(title);
		song.setChords(chords);
		return song;

	}

}