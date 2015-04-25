package com.vinoigitare.components.songviewer;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.model.Song;

public class SongViewer extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128198025964496737L;
	private Song song;

	public SongViewer(Song song) {
		super();
		this.song = song;
	}

	@Override
	public void attach() {
		super.attach();
		init();
	}

	protected void init() {

		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		layout.setMargin(true);

		ToolsPanel toolsPanel = new ToolsPanel(song);
		layout.addComponent(toolsPanel);

		SongDisplay songDisplay = new SongDisplay(song);
		layout.addComponent(songDisplay);

		layout.setExpandRatio(songDisplay, 1.0f);
		setContent(layout);

	}

}
