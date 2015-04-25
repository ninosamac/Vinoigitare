package com.vinoigitare.components.songviewer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vinoigitare.eventbus.SongSelected;
import com.vinoigitare.eventbus.SongSelectedHandler;
import com.vinoigitare.model.Song;

@SuppressWarnings("serial")
public class ToolsPanel extends Panel implements SongSelectedHandler {

	private Song song;

	public ToolsPanel(Song song) {
		this.song = song;
		
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.addStyleName("toolspanel");
		
		final Button editButton = new Button("Edit");
		ClickListener editButtonClickListener = getEditButtonClickListener();
		editButton.addClickListener(editButtonClickListener);
		layout.addComponent(editButton);
		
		setContent(layout);
	}

	private ClickListener getEditButtonClickListener() {
		return new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Window editWindow = new EditWindow(song);
				getUI().addWindow(editWindow);

			}

		};
	}

	@Override
	public void onEvent(SongSelected event) {
		this.song = event.getSong();

	}

}
