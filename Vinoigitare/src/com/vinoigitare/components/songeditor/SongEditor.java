package com.vinoigitare.components.songeditor;

import com.vaadin.data.Validator;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class SongEditor extends Panel {

	private static final String REGEX_VALID_INPUT = "[[A-Z][0-9][ÈÆŽŠÐèæžšð]].*";
	private Song song;
	private PropertysetItem songItem;
	private TextField artistField;
	private TextField titleField;
	private TextArea chordsArea;

	public SongEditor(Song song) {
		this.song = song;

		songItem = getItemFromSong(song);

		FormLayout form = new FormLayout();

		artistField = getArtistTextField(songItem);
		form.addComponent(artistField);

		titleField = getTitleTextField(songItem);
		form.addComponent(titleField);

		chordsArea = getChordsTextArea(songItem);
		form.addComponent(chordsArea);

		form.addComponent(getButtonOk());

		setContent(form);

	}

	private PropertysetItem getItemFromSong(Song song) {
		PropertysetItem songItem = new PropertysetItem();
		songItem.addItemProperty("artist", new ObjectProperty<String>(song
				.getArtist().getName()));
		songItem.addItemProperty("title",
				new ObjectProperty<String>(song.getTitle()));
		songItem.addItemProperty("chords",
				new ObjectProperty<String>(song.getChords()));
		return songItem;
	}

	@SuppressWarnings("serial")
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

	@SuppressWarnings("serial")
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

	@SuppressWarnings("serial")
	private TextArea getChordsTextArea(PropertysetItem songItem) {
		TextArea chordsTextArea = new TextArea("Chords",
				songItem.getItemProperty("chords"));
		chordsTextArea.setRequired(true);

		return chordsTextArea;
	}

	@SuppressWarnings("serial")
	private Button getButtonOk() {
		Button button = new Button("OK");

		ClickListener clickListener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					artistField.validate();
					titleField.validate();
					Notification.show("Thanks!");
				} catch (Exception e) {
					Notification.show("You fail!");
				}

			}
		};

		button.addClickListener(clickListener);
		return button;
	}

	@SuppressWarnings("serial")
	private Button getButtonCancel() {
		Button button = new Button("Cancel");

		ClickListener clickListener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {

					Notification.show("Thanks!");
				} catch (Exception e) {
					Notification.show("You fail!");
				}

			}
		};

		button.addClickListener(clickListener);
		return button;
	}

}