package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongCreatedEvent implements Event {

	private final static Class<SongCreatedEvent> type = SongCreatedEvent.class;

	private Song song;

	public SongCreatedEvent(Song song) {
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
