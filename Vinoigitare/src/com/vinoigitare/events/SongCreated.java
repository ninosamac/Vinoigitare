package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongCreated implements Event {

	private final static Class<SongCreated> type = SongCreated.class;

	private Song song;

	public SongCreated(Song song) {
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
