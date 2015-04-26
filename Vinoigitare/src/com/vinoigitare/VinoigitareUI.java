package com.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vinoigitare.components.MainLayout;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.filestorage.text.SongTextFileStorage;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.settings.Settings;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI implements Vinoigitare {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private static final EventBus eventBus = new EventBus();
	private SettingsService settings = new Settings();
	private DataService<Song> songService;

	public VinoigitareUI() {
		songService = createSongService();
	}

	@Override
	protected void init(VaadinRequest request) {
		MainLayout layout = new MainLayout(this);
		setSizeFull();
		setContent(layout);
	}

	private DataService<Song> createSongService() {
		String folder = getSongsFolder();
		return new SongTextFileStorage(folder);
	}

	private String getSongsFolder() {
		return System.getenv("VINOIGITARE_HOME") + "/"
				+ settings.getValue("SONGS_FOLDER");
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