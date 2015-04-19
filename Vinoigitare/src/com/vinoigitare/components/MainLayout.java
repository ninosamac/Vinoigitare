package com.vinoigitare.components;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.components.navigator.SongTree;
import com.vinoigitare.components.songpanel.SongPanel;
import com.vinoigitare.components.songpanel.SongPanelTestData;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.SongSelected;
import com.vinoigitare.eventbus.SongSelectedHandler;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings("serial")
public class MainLayout extends VerticalLayout implements SongSelectedHandler {

	private SongTree songTree;
	private SongPanel songPanel;
	private EventBus eventBus;
	private HorizontalSplitPanel panel;
	private DataService<Song> songService;

	public MainLayout(Vinoigitare vinoigitare) {

		setWidth(100, Unit.PERCENTAGE);
		setHeightUndefined();

		this.eventBus = vinoigitare.getEventBus();
		songService = vinoigitare.getSongService();

		Collection<Song> testSongs = new ArrayList<Song>();
		try {
			testSongs = songService.loadAll();
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		songTree = new SongTree(vinoigitare, testSongs);

		Song song = SongPanelTestData.generate();
		songPanel = new SongPanel(song);

		panel = new HorizontalSplitPanel(songTree, songPanel);
		panel.setSplitPosition(300, Unit.PIXELS);
		addComponent(panel);

		eventBus.registerForEvents(SongSelected.class, this);
	}

	@Override
	public void onEvent(SongSelected event) {
		Song song = event.getSong();
		panel.removeComponent(songPanel);
		songPanel = new SongPanel(song);
		panel.setSecondComponent(songPanel);
	}

}
