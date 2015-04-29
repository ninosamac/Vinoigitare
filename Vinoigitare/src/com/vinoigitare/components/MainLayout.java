package com.vinoigitare.components;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.navigator.Navigator;
import com.vinoigitare.components.songpanel.SongPanelTestData;
import com.vinoigitare.components.songviewer.SongViewer;
import com.vinoigitare.components.songviewer.ToolsPanel;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.events.SongUpdated;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings({ "serial", "rawtypes" })
public class MainLayout extends VerticalLayout implements EventHandler {

	private Navigator navigator;
	private SongViewer songViewer;
	private EventBus eventBus;
	private HorizontalSplitPanel panel;
	private DataService<Song> songService;

	public MainLayout(Vinoigitare vinoigitare) {

		this.eventBus = vinoigitare.getEventBus();

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		
		
		
		navigator = new Navigator(vinoigitare);

		Song song = SongPanelTestData.generate();
		songViewer = new SongViewer(song);

		panel = new HorizontalSplitPanel(navigator, songViewer);
		panel.setSplitPosition(300, Unit.PIXELS);
		addComponent(panel);

		eventBus.registerForEvents(SongSelected.class, this);
		eventBus.registerForEvents(SongCreated.class, this);
		eventBus.registerForEvents(SongUpdated.class, this);
		
	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {
		if (event.getType().equals(SongSelected.class)) {
			onSongSelected((SongSelected) event);
		} else if (event.getType().equals(SongCreated.class)) {
			onSongCreated((SongCreated) event);	
		} else if (event.getType().equals(SongUpdated.class)) {
			onSongUpdated((SongUpdated) event);
		}

	}

	private void onSongCreated(SongCreated event) {
		panel.removeComponent(songViewer);
		
		Song song = ((SongCreated)event).getSong();
		songViewer = new SongViewer(song);
		panel.setSecondComponent(songViewer);

		panel.removeComponent(navigator);

		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		panel.setFirstComponent(navigator);
		
	}

	private void onSongSelected(SongSelected event) {
		Song song = event.getSong();
		panel.removeComponent(songViewer);
		songViewer = new SongViewer(song);
		panel.setSecondComponent(songViewer);
	}

	private void onSongUpdated(SongUpdated event) {
		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		DataService<Song> songService = vinoigitare.getSongService();

		Song oldVersion = event.getOldVersion();
		try {
			songService.remove(oldVersion);			
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Song newVersion = event.getNewVersion();
		try {
			songService.store(newVersion);
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		panel.removeComponent(songViewer);
		songViewer = new SongViewer(newVersion);
		panel.setSecondComponent(songViewer);

		panel.removeComponent(navigator);

		navigator = new Navigator(vinoigitare);

		panel.setFirstComponent(navigator);
	}

}
