package com.vinoigitare;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vinoigitare")
public class VinoigitareUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = com.vinoigitare.VinoigitareUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		setSizeFull();

		setContent(layout);

		Collection<Song> testSongs = SongTreeTestData.generate();
		SongTree songTree = new SongTree(testSongs);

		Song song = SongPanelTestData.generate();
		SongPanel songPanel = new SongPanel(song);

		HorizontalSplitPanel panel = new HorizontalSplitPanel(songTree,
				songPanel);
		panel.setSplitPosition(300, Unit.PIXELS);		
		layout.addComponent(panel);

	}

	
}