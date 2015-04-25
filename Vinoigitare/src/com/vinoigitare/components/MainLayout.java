package com.vinoigitare.components;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.navigator.SongTree;
import com.vinoigitare.components.songpanel.SongPanelTestData;
import com.vinoigitare.components.songviewer.SongViewer;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings("serial")
public class MainLayout extends VerticalLayout implements
		EventHandler<SongSelected> {

	private SongTree songTree;
	private SongViewer songViewer;
	private EventBus eventBus;
	private HorizontalSplitPanel panel;
	private DataService<Song> songService;

	public MainLayout(Vinoigitare vinoigitare) {

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		this.eventBus = vinoigitare.getEventBus();
		songService = vinoigitare.getSongService();

		Collection<Song> songs = new ArrayList<Song>();
		try {
			songs = songService.loadAll();
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		songTree = new SongTree(vinoigitare, songs);

		Song song = SongPanelTestData.generate();
		songViewer = new SongViewer(song);

		panel = new HorizontalSplitPanel(songTree, songViewer);
		panel.setSplitPosition(300, Unit.PIXELS);
		addComponent(panel);

		eventBus.registerForEvents(SongSelected.class, this);
	}

	@Override
	public void onEvent(SongSelected event) {
		Song song = event.getSong();
		panel.removeComponent(songViewer);
		songViewer = new SongViewer(song);
		panel.setSecondComponent(songViewer);
	}

}
