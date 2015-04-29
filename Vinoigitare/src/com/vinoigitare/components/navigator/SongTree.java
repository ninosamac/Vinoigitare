package com.vinoigitare.components.navigator;

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(SongTree.class.getName());

	private Collection<Song> songs;
	private TreeMap<Artist, TreeSet<Song>> songsByArtists = new TreeMap<Artist, TreeSet<Song>>();
	private EventBus eventBus;

	public SongTree(Vinoigitare vinoigitare, Collection<Song> songs) {
		super();
		this.eventBus = vinoigitare.getEventBus();
		this.songs = songs;

		setSizeFull();
		setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		populateItems();

		ItemClickListener listener = new SongTreeClickListener(eventBus);
		addItemClickListener(listener);

	}

	private void populateItems() {

		TreeSet<Artist> artists = new TreeSet<Artist>();

		for (Song song : songs) {
			Artist artist = song.getArtist();
			artists.add(artist);
		}

		for (Artist artist : artists) {
			TreeSet<Song> songsByArtist = new TreeSet<Song>();
			songsByArtists.put(artist, songsByArtist);
		}

		for (Song song : songs) {
			Artist artist = song.getArtist();
			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			songsByArtist.add(song);
		}

		for (Artist artist : artists) {
			String name = artist.getName();
			setItemCaption(artist, name);
			addItem(artist);
			setChildrenAllowed(artist, true);
			log.debug("Added Artist: " + artist);

			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			for (Song song : songsByArtist) {
				String title = song.getTitle();
				setItemCaption(song, title);
				addItem(song);
				setParent(song, artist);
				setChildrenAllowed(song, false);
				log.debug("Added Song: " + song);
			}
		}
	}
}
