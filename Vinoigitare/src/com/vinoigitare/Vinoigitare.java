package com.vinoigitare;

import com.vaadin.ui.Component;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.SettingsService;

public interface Vinoigitare {

	public EventBus getEventBus();
	
	public SettingsService getSettings();

	public DataService<Song> getSongService();
	
	public ActionRegistry getActionRegistry();
	
	public void show(Component component);
}
