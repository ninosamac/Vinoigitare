package com.vinoigitare;

import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;

public interface Vinoigitare {

	public EventBus getEventBus();

	public DataService<Song> getSongService();
}
