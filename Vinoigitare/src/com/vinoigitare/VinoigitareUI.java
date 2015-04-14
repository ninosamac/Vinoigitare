package com.vinoigitare;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vinoigitare.components.MainLayout;
import com.vinoigitare.event.EventBus;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private static final EventBus eventBus = new EventBus();
	
	@Override
	protected void init(VaadinRequest request) {
		MainLayout layout = new MainLayout(eventBus);
		System.out.println(layout.isAttached());
		
		setSizeFull();

		setContent(layout);
		System.out.println(layout.isAttached());
		
//		Collection<Song> testSongs = SongTreeTestData.generate();
//		SongTree songTree = new SongTree(testSongs, eventBus);
//
//		Song song = SongPanelTestData.generate();
//		SongPanel songPanel = new SongPanel(song);
//
//		HorizontalSplitPanel panel = new HorizontalSplitPanel(songTree,
//				songPanel);
//		panel.setSplitPosition(300, Unit.PIXELS);
//		layout.addComponent(panel);

	}

}