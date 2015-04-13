package com.vinoigitare;

import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
//import com.vaadin.external.org.slf4j.Logger;
//import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.ui.Tree;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger
			.getLogger(SongTree.class.getName());

	private Collection<Song> songs;
	private TreeMap<String, TreeSet<Song>> songsByArtists = new TreeMap<String, TreeSet<Song>>();

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

		TreeSet<String> artists = new TreeSet<String>();

		for (Song song : songs) {
			String artist = song.getArtist();
			artists.add(artist);
		}

		for (String artist : artists) {
			TreeSet<Song> songsByArtist = new TreeSet<Song>();
			songsByArtists.put(artist, songsByArtist);
		}

		for (Song song : songs) {
			String artist = song.getArtist();
			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			songsByArtist.add(song);
		}

		for (String artist : artists) {
			Item item = addItem(artist);
			setChildrenAllowed(artist, true);

			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			for (Song song : songsByArtist) {
				String title = song.getTitle();
				addItem(title);
				setParent(title, artist);
				setChildrenAllowed(title, false);
			}
		}
	}

	// private void populateItems() {
	// addItem("Families");
	// addItem("The Jackson");
	// addItem("The Simpsons");
	// addItem("The Rothschilds");
	// addItem("The Hapsburgs");
	// addItem("The Addams");
	// setParent("The Jackson", "Families");
	// setParent("The Simpsons", "Families");
	// setParent("The Rothschilds", "Families");
	// setParent("The Hapsburgs", "Families");
	// setParent("The Addams", "Families");
	// setChildrenAllowed("The Jackson", false);
	// setChildrenAllowed("The Simpsons", false);
	// setChildrenAllowed("The Rothschilds", false);
	// setChildrenAllowed("The Hapsburgs", false);
	// setChildrenAllowed("The Addams", false);
	// expandItemsRecursively("Families");
	// }
}
