package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongSelected implements Event {

	private final static Class<SongSelected> type = SongSelected.class;

	private Song song;

	public SongSelected(Song song) {
		this.song = song;
	}

	public Song getSong() {
		return song;
	}

	@Override
	public Class<?> getType() {
		return type;
	}

}
