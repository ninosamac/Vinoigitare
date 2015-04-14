package com.vinoigitare.components.navigator;

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
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
		setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		populateItems();

		@SuppressWarnings("serial")
		ItemClickListener listener = new ItemClickListener() {

			@Override
			public void itemClick(ItemClickEvent event) {
				Item item = event.getItem();
				Object itemId = event.getItemId();
				System.out.println(itemId);
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
			String name = artist.getName();				
			setItemCaption(artist, name);
			addItem(artist);
			setChildrenAllowed(artist, true);

			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			for (Song song : songsByArtist) {
				String title = song.getTitle();				
				setItemCaption(song, title);
				addItem(song);				
				setParent(song, artist);
				setChildrenAllowed(song, false);
			}
		}
	}
}
