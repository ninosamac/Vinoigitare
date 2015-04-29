package com.vinoigitare.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.songeditor.EditWindow;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings("serial")
public class ToolsPanel extends Panel implements EventHandler<SongSelected> {

	private Song song = null;

	public ToolsPanel() {
		// this.song = song;

		GridLayout grid = new GridLayout(1, 1);
		grid.setSizeFull();

		final HorizontalLayout buttons = new HorizontalLayout();
		buttons.addStyleName("toolspanel");

		final Button newButton = getNewButon();
		buttons.addComponent(newButton);

		final Button removeButton = getRemoveButon();
		buttons.addComponent(removeButton);

		final Button editButton = getEditButon();
		buttons.addComponent(editButton);

		grid.addComponent(buttons);
		grid.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);
		setContent(grid);
	}

	private Button getNewButon() {
		Button button = new Button("New");
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Window editWindow = new EditWindow(null);
				getUI().addWindow(editWindow);
			}

		};

		button.addClickListener(listener);
		return button;
	}

	private Button getEditButon() {
		Button button = new Button("Edit");
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Window editWindow = new EditWindow(song);
				getUI().addWindow(editWindow);
			}

		};

		button.addClickListener(listener);
		return button;
	}

	private Button getRemoveButon() {
		Button button = new Button("Remove");
		ClickListener listener = new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Vinoigitare vinoigitare = (Vinoigitare) getUI();
				DataService<Song> songService = vinoigitare.getSongService();
				try {
					songService.remove(song);
				} catch (DataServiceException e) {
					Notification.show("Could not remove song: " + song,
							e.getMessage(), Type.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}

		};

		button.addClickListener(listener);
		return button;
	}

	@Override
	public void onEvent(SongSelected event) {
		this.song = event.getSong();
	}

}
