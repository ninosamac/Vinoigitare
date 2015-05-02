package com.vinoigitare.components;

import com.vaadin.ui.HorizontalSplitPanel;
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
import com.vinoigitare.model.Song;

@SuppressWarnings({ "serial", "rawtypes" })
public class MainLayout extends VerticalLayout implements EventHandler {

	private EventBus eventBus;

	private ToolsPanel toolsPanel;
	private Navigator navigator;
	private SongViewer songViewer;

	private HorizontalSplitPanel horizontalSplitPanel;

	public MainLayout(Vinoigitare vinoigitare) {

		this.eventBus = vinoigitare.getEventBus();

		eventBus.registerForEvents(SongSelected.class, this);
		eventBus.registerForEvents(SongCreated.class, this);
		eventBus.registerForEvents(SongRemoved.class, this);

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		toolsPanel = new ToolsPanel();
		eventBus.registerForEvents(SongSelected.class, toolsPanel);
		addComponent(toolsPanel);

		navigator = new Navigator(vinoigitare);

//		Song song = SongPanelTestData.generate();
//		songViewer = new SongViewer(song);

		HelloPage helloPage = new HelloPage();
		
		horizontalSplitPanel = new HorizontalSplitPanel(navigator, helloPage);
		horizontalSplitPanel.setSplitPosition(300, Unit.PIXELS);
		addComponent(horizontalSplitPanel);

		setExpandRatio(horizontalSplitPanel, 1.0f);

	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {
		
		if (event.getType().equals(SongSelected.class)) {
			onSongSelected((SongSelected) event);

		} else if (event.getType().equals(SongCreated.class)) {
			onSongCreated((SongCreated) event);

		} else if (event.getType().equals(SongRemoved.class)) {
			onSongRemoved((SongRemoved) event);
		}

	}

	private void onSongSelected(SongSelected event) {
		Song song = event.getSong();
		horizontalSplitPanel.removeComponent(horizontalSplitPanel.getSecondComponent());
		songViewer = new SongViewer(song);
		horizontalSplitPanel.setSecondComponent(songViewer);
	}

	private void onSongCreated(SongCreated event) {

		horizontalSplitPanel.removeComponent(songViewer);
		Song song = event.getSong();
		songViewer = new SongViewer(song);
		horizontalSplitPanel.setSecondComponent(songViewer);

		horizontalSplitPanel.removeComponent(navigator);

		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		horizontalSplitPanel.setFirstComponent(navigator);

	}

	private void onSongRemoved(SongRemoved event) {

		Song song = event.getSong();
		if (songViewer.getSong().equals(song)) {
			horizontalSplitPanel.removeComponent(songViewer);
		}

		horizontalSplitPanel.removeComponent(navigator);
		Vinoigitare vinoigitare = (Vinoigitare) this.getUI();
		navigator = new Navigator(vinoigitare);

		horizontalSplitPanel.setFirstComponent(navigator);
	}

}
