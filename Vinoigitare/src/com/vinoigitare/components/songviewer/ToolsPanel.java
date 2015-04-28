package com.vinoigitare.components.songviewer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vinoigitare.components.songeditor.EditWindow;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class ToolsPanel extends Panel implements EventHandler<SongSelected> {

	private Song song;

	public ToolsPanel(Song song) {
		this.song = song;

		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.addStyleName("toolspanel");

		final Button editButton = getEditButon();
		layout.addComponent(editButton);

		setContent(layout);
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

	@Override
	public void onEvent(SongSelected event) {
		this.song = event.getSong();
	}

}
