package com.vinoigitare.components;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.navigator.Navigator;
import com.vinoigitare.components.songpanel.SongPanelTestData;
import com.vinoigitare.components.songviewer.SongViewer;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongRemoved;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.events.SongUpdated;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings({ "serial", "rawtypes" })
public class MainLayout extends VerticalLayout implements EventHandler {

	private EventBus eventBus;
	private DataService<Song> songService;

	private ToolsPanel toolsPanel;
	private Navigator navigator;
	private SongViewer songViewer;

	private HorizontalSplitPanel panel;

	public MainLayout(Vinoigitare vinoigitare) {

		this.eventBus = vinoigitare.getEventBus();
		this.songService = vinoigitare.getSongService();

		eventBus.registerForEvents(SongSelected.class, this);
		eventBus.registerForEvents(SongCreated.class, this);
		eventBus.registerForEvents(SongUpdated.class, this);
		eventBus.registerForEvents(SongRemoved.class, this);

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		toolsPanel = new ToolsPanel();
		eventBus.registerForEvents(SongSelected.class, toolsPanel);
		addComponent(toolsPanel);

		navigator = new Navigator(vinoigitare);

		Song song = SongPanelTestData.generate();
		songViewer = new SongViewer(song);

		panel = new HorizontalSplitPanel(navigator, songViewer);
		panel.setSplitPosition(300, Unit.PIXELS);
		addComponent(panel);

		setExpandRatio(panel, 1.0f);

	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {
		if (event.getType().equals(SongSelected.class)) {
			onSongSelected((SongSelected) event);
		} else if (event.getType().equals(SongCreated.class)) {
			onSongCreated((SongCreated) event);
		} else if (event.getType().equals(SongUpdated.class)) {
			onSongUpdated((SongUpdated) event);
		} else if (event.getType().equals(SongRemoved.class)) {
			onSongRemoved((SongRemoved) event);
		}

	}

	private void onSongSelected(SongSelected event) {
		Song song = event.getSong();
		panel.removeComponent(songViewer);
		songViewer = new SongViewer(song);
		panel.setSecondComponent(songViewer);
	}

	private void onSongCreated(SongCreated event) {

		Song song = event.getSong();
		try {
			songService.store(song);
		} catch (DataServiceException e) {
			Notification.show(e.getMessage(), Type.WARNING_MESSAGE);
		}

		panel.removeComponent(songViewer);
		songViewer = new SongViewer(song);
		panel.setSecondComponent(songViewer);

		panel.removeComponent(navigator);

		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		panel.setFirstComponent(navigator);

	}

	private void onSongUpdated(SongUpdated event) {

		Song newVersion = event.getNewVersion();
		Song oldVersion = event.getOldVersion();

		try {
			songService.remove(oldVersion);
			songService.store(newVersion);
		} catch (DataServiceException e) {
			Notification.show(e.getMessage(), Type.WARNING_MESSAGE);
		}

		panel.removeComponent(songViewer);

		songViewer = new SongViewer(newVersion);
		panel.setSecondComponent(songViewer);

		panel.removeComponent(navigator);

		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		panel.setFirstComponent(navigator);
	}

	private void onSongRemoved(SongRemoved event) {

		Song song = event.getSong();
		try {
			songService.remove(song);
		} catch (DataServiceException e) {
			Notification.show(e.getMessage(), Type.WARNING_MESSAGE);
		}

		panel.removeComponent(songViewer);

		panel.removeComponent(navigator);

		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		panel.setFirstComponent(navigator);
	}

}
