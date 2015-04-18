package com.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vinoigitare.components.MainLayout;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.mockservices.TestSongService;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.api.DataService;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI implements Vinoigitare {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private static final EventBus eventBus = new EventBus();
	//private DataService<Song> songService = new TestSongService();
	private DataService<Song> songService = new SongService();

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

}