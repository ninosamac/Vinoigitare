package com.vinoigitare.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.menu.HelpMenu;
import com.vinoigitare.components.menu.LeftMenu;
import com.vinoigitare.components.navigator.Navigator;
import com.vinoigitare.components.songviewer.SongViewer;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongCreatedEvent;
import com.vinoigitare.events.SongRemovedEvent;
import com.vinoigitare.events.SongSelectedEvent;
import com.vinoigitare.model.Song;
import com.vinoigitare.pages.HelloPage;

@SuppressWarnings({ "serial", "rawtypes" })
public class MainLayout extends VerticalLayout implements EventHandler {

	private Navigator navigator;
	private SongViewer songViewer;

	private HorizontalSplitPanel horizontalSplitPanel;

	public MainLayout(Vinoigitare vinoigitare) {

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		GridLayout menuLayout = new GridLayout(2, 1);
		menuLayout.setWidth(100, Unit.PERCENTAGE);

		LeftMenu leftMenu = new LeftMenu(vinoigitare);
		menuLayout.addComponent(leftMenu, 0, 0);
		
		HelpMenu helpMenu = new HelpMenu(vinoigitare);
		menuLayout.addComponent(helpMenu, 1, 0);
		
		menuLayout.setColumnExpandRatio(1, 1);
		menuLayout.setComponentAlignment(helpMenu, Alignment.MIDDLE_RIGHT);
		
		addComponent(menuLayout);

		navigator = new Navigator(vinoigitare);

		HelloPage helloPage = new HelloPage();

		horizontalSplitPanel = new HorizontalSplitPanel(navigator, helloPage);
		horizontalSplitPanel.setSplitPosition(300, Unit.PIXELS);
		addComponent(horizontalSplitPanel);

		setExpandRatio(horizontalSplitPanel, 1.0f);
		
		EventBus eventBus = vinoigitare.getEventBus();

		eventBus.registerForEvents(SongSelectedEvent.class, this);
		eventBus.registerForEvents(SongCreatedEvent.class, this);
		eventBus.registerForEvents(SongRemovedEvent.class, this);

	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {

		if (event.getType().equals(SongSelectedEvent.class)) {
			onSongSelected((SongSelectedEvent) event);

		} else if (event.getType().equals(SongCreatedEvent.class)) {
			onSongCreated((SongCreatedEvent) event);

		} else if (event.getType().equals(SongRemovedEvent.class)) {
			onSongRemoved((SongRemovedEvent) event);
		}

	}

	private void onSongSelected(SongSelectedEvent event) {

		Component secondComponent = horizontalSplitPanel.getSecondComponent();

		if (secondComponent != null) {
			horizontalSplitPanel.removeComponent(secondComponent);
		}

		Song song = event.getSong();
		songViewer = new SongViewer(song);
		horizontalSplitPanel.setSecondComponent(songViewer);

	}

	private void onSongCreated(SongCreatedEvent event) {

		Component secondComponent = horizontalSplitPanel.getSecondComponent();

		if (secondComponent != null) {
			horizontalSplitPanel.removeComponent(secondComponent);
		}

		Song song = event.getSong();
		songViewer = new SongViewer(song);
		horizontalSplitPanel.setSecondComponent(songViewer);

	}

	private void onSongRemoved(SongRemovedEvent event) {

		Song song = event.getSong();

		if (songViewer.getSong().equals(song)) {
			horizontalSplitPanel.removeComponent(songViewer);
		}

	}

	public void show(Component component) {

		if (component != null) {
			horizontalSplitPanel.removeComponent(horizontalSplitPanel
					.getSecondComponent());
			horizontalSplitPanel.setSecondComponent(component);
		}

	}

}
