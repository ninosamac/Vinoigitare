package com.vinoigitare;

import com.vaadin.ui.Component;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.services.SongService;
import com.vinoigitare.settings.api.SettingsService;

public interface Vinoigitare {

	public EventBus getEventBus();

	public SettingsService getSettings();

	public SongService getSongService();

	public ActionRegistry getActionRegistry();

	public void show(Component component);
}
