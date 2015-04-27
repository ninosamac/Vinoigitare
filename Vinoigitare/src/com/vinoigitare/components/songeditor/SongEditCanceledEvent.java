package com.vinoigitare.components.songeditor;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

@SuppressWarnings("rawtypes")
public class SongEditCanceledEvent implements Event{
	
	private final static Class<SongEditCanceledEvent> type = SongEditCanceledEvent.class;
	private Song song;
	
	public SongEditCanceledEvent(Song song) {
		this.song = song;
	}

	public Song getSong() {
		return song;
	}
	
	@Override
	public Object getType() {
		return type;
	}

}
