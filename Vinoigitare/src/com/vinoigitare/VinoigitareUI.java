package com.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vinoigitare.components.MainLayout;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongRemoved;
import com.vinoigitare.events.SongUpdated;
import com.vinoigitare.filestorage.text.SongService;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.SettingsService;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI implements Vinoigitare {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private static final EventBus eventBus = new EventBus();
	private SettingsService settings;
	private SongService songService;

	public VinoigitareUI() {
		songService = new SongService();
		eventBus.registerForEvents(SongCreated.class, songService);
		eventBus.registerForEvents(SongUpdated.class, songService);
		eventBus.registerForEvents(SongRemoved.class, songService);
		// songService = new TestSongService();
	}

	public void bindSettingsService(SettingsService service) {
		this.settings = service;
	}

	@Override
	protected void init(VaadinRequest request) {
		MainLayout layout = new MainLayout(this);
		setSizeFull();
		setContent(layout);
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public DataService<Song> getSongService() {
		return songService;
	}

	@Override
	public SettingsService getSettings() {
		return settings;
	}

}