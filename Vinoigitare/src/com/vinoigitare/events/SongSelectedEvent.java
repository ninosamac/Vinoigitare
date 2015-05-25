package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongSelectedEvent implements Event {

	private final static Class<SongSelectedEvent> type = SongSelectedEvent.class;

	private Song song;

	public SongSelectedEvent(Song song) {
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
