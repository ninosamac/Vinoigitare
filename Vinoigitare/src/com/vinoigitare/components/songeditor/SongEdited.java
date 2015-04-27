package com.vinoigitare.components.songeditor;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.model.Song;

@SuppressWarnings("rawtypes")
public class SongEdited implements Event {

	private static final Class<SongEdited> type = SongEdited.class;
	private final Song oldVersion;
	private final Song newVersion;

	public SongEdited(Song oldVersion, Song newVersion) {
		this.oldVersion = oldVersion;
		this.newVersion = newVersion;
	}

	
	
	public Song getOldVersion() {
		return oldVersion;
	}



	public Song getNewVersion() {
		return newVersion;
	}



	@Override
	public Object getType() {
		return type;
	}

}
