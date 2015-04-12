package com.vinoigitare;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SongPanel extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128198025964496737L;
	private Song song;

	public SongPanel(Song song) {
		super();
		this.song = song;
	}

	@Override
	public void attach() {
		super.attach();
		init();
	}

	protected void init() {

		Label title = new Label(song.getTitle());
		addComponent(title);

		Label artist = new Label(song.getArtist());
		addComponent(artist);

		Label text = new Label();
		text.setContentMode(ContentMode.PREFORMATTED);
		text.setValue(song.getText());
		addComponent(text);
	}
}
