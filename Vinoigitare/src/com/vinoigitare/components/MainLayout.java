package com.vinoigitare.components;

import java.util.Collection;
import java.util.logging.Logger;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.components.navigator.SongTree;
import com.vinoigitare.components.navigator.SongTreeTestData;
import com.vinoigitare.components.songpanel.SongPanel;
import com.vinoigitare.components.songpanel.SongPanelTestData;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.SongSelected;
import com.vinoigitare.eventbus.SongSelectedHandler;
import com.vinoigitare.model.Song;

public class MainLayout extends VerticalLayout implements SongSelectedHandler {

	private SongTree songTree;
	private SongPanel songPanel;
	private EventBus eventBus;
	private HorizontalSplitPanel panel;
	
	private static final Logger log = Logger.getLogger(MainLayout.class.getName());

	public MainLayout(EventBus eventBus) {
		this.eventBus = eventBus;
		
		setSizeFull();
		Collection<Song> testSongs = SongTreeTestData.generate();
		songTree = new SongTree(eventBus, testSongs);		

		Song song = SongPanelTestData.generate();
		songPanel = new SongPanel(song);

		panel = new HorizontalSplitPanel(songTree, songPanel);
		panel.setSplitPosition(300, Unit.PIXELS);
		addComponent(panel);
		
		eventBus.registerForEvents(SongSelected.class, this);
		
		log.fine("FINE");
		log.finer("FINER");
		log.finest("FINEST");
		log.info("INFO");
		log.warning("WARNING");
		log.severe("SEVERE");
	}


	@Override
	public void onEvent(SongSelected event) {
		Song song = event.getSong();
		panel.removeComponent(songPanel);
		songPanel = new SongPanel(song);
		panel.setSecondComponent(songPanel);
	}

}
