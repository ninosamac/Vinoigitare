package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongRemoved implements Event {

	private final static Class<SongRemoved> type = SongRemoved.class;

	private Song song;

	public SongRemoved(Song song) {
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
