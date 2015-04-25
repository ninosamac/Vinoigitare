package com.vinoigitare.components.songviewer;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.model.Song;

public class SongDisplay extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128198025964496737L;
	private Song song;

	public SongDisplay(Song song) {
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

		Label title = new Label(song.getTitle());
		title.addStyleName("song-title");
		layout.addComponent(title);

		Label artist = new Label(song.getArtist().getName());
		artist.addStyleName("song-artist");
		layout.addComponent(artist);

		Label chords = new Label();
		chords.setContentMode(ContentMode.PREFORMATTED);
		chords.addStyleName("song-chords");
		chords.setValue(song.getChords());
		layout.addComponent(chords);

		layout.setExpandRatio(chords, 1.0f);
		setContent(layout);

	}

}
