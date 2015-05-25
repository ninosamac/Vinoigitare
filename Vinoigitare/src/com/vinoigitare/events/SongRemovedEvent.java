package com.vinoigitare.events;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

public class SongRemovedEvent implements Event {

	private final static Class<SongRemovedEvent> type = SongRemovedEvent.class;

	private Song song;

	public SongRemovedEvent(Song song) {
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
