package com.vinoigitare;

import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.SettingsService;

public interface Vinoigitare {

	public EventBus getEventBus();
	
	public SettingsService getSettings();

	public DataService<Song> getSongService();
}
