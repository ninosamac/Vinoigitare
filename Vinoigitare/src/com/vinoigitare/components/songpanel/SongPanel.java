package com.vinoigitare.components.songpanel;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.model.Song;

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
		// setMargin(true);

		Label title = new Label(song.getTitle());
		title.addStyleName("song-title");
		addComponent(title);

		Label artist = new Label(song.getArtist().getName());
		artist.addStyleName("song-artist");
		addComponent(artist);

		Label chords = new Label();
		chords.setContentMode(ContentMode.PREFORMATTED);
		chords.setValue(song.getChords());
		addComponent(chords);

	}

}
