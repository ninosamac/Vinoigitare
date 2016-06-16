package com.vinoigitare.components.navigator;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.criteria.ANDCriteria;
import com.vinoigitare.criteria.Criteria;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.ClearSearchFilterEvent;
import com.vinoigitare.events.SearchEvent;
import com.vinoigitare.events.SongCreatedEvent;
import com.vinoigitare.events.SongRemovedEvent;
import com.vinoigitare.events.SongSelectedEvent;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;

@SuppressWarnings({ "serial", "rawtypes" })
public class Navigator extends Panel implements EventHandler {

	private SongService songService;
	private SongTree songTree;

	private ANDCriteria<Song> criteria = new ANDCriteria<Song>();

	private Collection<Song> songs;

	private VerticalLayout layout;
	private EventBus eventBus;

	private final static Log log = LogFactory.getLog(Navigator.class);

	public Navigator(Vinoigitare vinoigitare) {

		initServices(vinoigitare);
		initLayout(vinoigitare);

	}

	private void initServices(Vinoigitare vinoigitare) {

		songService = vinoigitare.getSongService();

		eventBus = vinoigitare.getEventBus();

		eventBus.registerForEvents(SongSelectedEvent.class, this);

		eventBus.registerForEvents(SongCreatedEvent.class, this);
		eventBus.registerForEvents(SongRemovedEvent.class, this);

		eventBus.registerForEvents(SearchEvent.class, this);
		eventBus.registerForEvents(ClearSearchFilterEvent.class, this);
	}

	private void initLayout(Vinoigitare vinoigitare) {

		layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);

		setSizeFull();

		songs = new ArrayList<Song>();
		try {
			songs = songService.loadAll();
		} catch (SongServiceException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}

		songTree = new SongTree(eventBus, songs);
		layout.addComponent(songTree);

		setContent(layout);

	}

	@Override
	public void onEvent(com.vinoigitare.eventbus.Event event) {

		Class<?> eventType = event.getType();

		if (eventType.equals(SearchEvent.class)) {
			onSearchEvent((SearchEvent) event);

		}

		else if (eventType.equals(ClearSearchFilterEvent.class)) {
			onClearSearchFilterEvent();
		}

		else if (eventType.equals(SongRemovedEvent.class)) {
			onSongRemovedEvent((SongRemovedEvent) event);

		}

		else if (eventType.equals(SongCreatedEvent.class)) {
			onSongCreatedEvent((SongCreatedEvent) event);

		}

	}

	private void onSongCreatedEvent(SongCreatedEvent event) {

		Song song = event.getSong();
		songTree.addSong(song);
		String artist = song.getArtist();
		songTree.expandItem(artist);
		songTree.select(song);

	}

	private void onSongRemovedEvent(SongRemovedEvent event) {

		Song song = event.getSong();
		songTree.removeSong(song);

	}

	@SuppressWarnings("unchecked")
	public void onSearchEvent(SearchEvent event) {

		String searchText = event.getSearchText();

		searchText = searchText.toLowerCase();
		searchText = searchText.replace('š', 's');
		searchText = searchText.replace('ð', 'd');
		searchText = searchText.replace('è', 'c');
		searchText = searchText.replace('æ', 'c');
		searchText = searchText.replace('ž', 'z');

		Criteria<Song> containsText = new ContainsText(searchText);
		criteria.add(containsText);

		reloadSongTree();

	}

	private void reloadSongTree() {
		Collection<Song> songs = new ArrayList<Song>();
		try {
			songs = songService.load(criteria);
		} catch (SongServiceException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}

		songTree.removeAllSongs();

		for (Song song : songs) {
			songTree.addSong(song);
		}

		songTree.sanitizeSelection();
	}

	@SuppressWarnings("unchecked")
	private void onClearSearchFilterEvent() {

		criteria.clear();
		criteria.add(Criteria.ALWAYS_SATISFIED);
		reloadSongTree();

	}

	class ContainsText implements Criteria<Song> {

		private String searchText;

		public ContainsText(String searchText) {
			this.searchText = searchText;
		}

		@Override
		public boolean isSatisfiedBy(Song song) {
			String searchableText = song.getSearchableText();
			return searchableText.contains(searchText);
		}

	}

}
