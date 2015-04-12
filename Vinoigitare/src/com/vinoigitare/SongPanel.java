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

		setSizeFull();
		//setMargin(true);
		
		Label title = new Label(song.getTitle());
		title.addStyleName("song-title");
		addComponent(title);

		Label artist = new Label(song.getArtist());
		artist.addStyleName("song-artist");
		addComponent(artist);

		Label text = new Label();
		text.setContentMode(ContentMode.PREFORMATTED);
		text.setValue(song.getText());
		addComponent(text);
		

	}
}
