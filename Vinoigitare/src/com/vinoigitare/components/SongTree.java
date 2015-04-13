package com.vinoigitare.components;

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
//import com.vaadin.external.org.slf4j.Logger;
//import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.ui.Tree;
import com.vinoigitare.Artist;
import com.vinoigitare.Song;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(SongTree.class.getName());

	private Collection<Song> songs;
	private TreeMap<Artist, TreeSet<Song>> songsByArtists = new TreeMap<Artist, TreeSet<Song>>();

	public SongTree(Collection<Song> songs) {
		super();
		this.songs = songs;
	}

	@Override
	public void attach() {
		super.attach();
		init();
	}

	protected void init() {

		setSizeFull();
		populateItems();

		@SuppressWarnings("serial")
		ItemClickListener listener = new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				Item item = event.getItem();
				System.out.println(event.getItemId());

			}
		};
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
			
			Item item = addItem(artist.getName());
			setChildrenAllowed(artist, true);

			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			for (Song song : songsByArtist) {
				String title = song.getTitle();
				addItem(title);
				setParent(title, artist.getName());
				setChildrenAllowed(title, false);
			}
		}
	}
}
