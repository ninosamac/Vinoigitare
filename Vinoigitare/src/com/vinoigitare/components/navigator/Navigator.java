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
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SearchEvent;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;

@SuppressWarnings({ "serial" })
public class Navigator extends Panel implements EventHandler<SearchEvent> {

	private SongService songService;
	private SongTree songTree;

	private ANDCriteria<Song> criteria = new ANDCriteria<Song>();
	private VerticalLayout layout;
	private Vinoigitare vinoigitare;

	private final static Log log = LogFactory.getLog(Navigator.class);

	public Navigator(Vinoigitare vinoigitare) {
		this.vinoigitare = vinoigitare;

		layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);

		songService = vinoigitare.getSongService();

		setSizeFull();

		Collection<Song> songs = new ArrayList<Song>();
		try {
			songs = songService.loadAll();
		} catch (SongServiceException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		songTree = new SongTree(vinoigitare, songs);
		layout.addComponent(songTree);

		setContent(layout);
	}

	@Override
	public void onEvent(SearchEvent event) {
		String searchText = event.getSearchText();

		searchText = searchText.toLowerCase();
		searchText = searchText.replace('š', 's');
		searchText = searchText.replace('ð', 'd');
		searchText = searchText.replace('è', 'c');
		searchText = searchText.replace('æ', 'c');
		searchText = searchText.replace('ž', 'z');

		Criteria<Song> containsText = new ContainsText(searchText);
		criteria.add(containsText);

		Collection<Song> songs = new ArrayList<Song>();
		try {
			songs = songService.load(criteria);
		} catch (SongServiceException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		layout.removeComponent(songTree);
		songTree = new SongTree(vinoigitare, songs);
		layout.addComponent(songTree);

		setContent(layout);
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
