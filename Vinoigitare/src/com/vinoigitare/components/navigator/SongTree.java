package com.vinoigitare.components.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongSelectedEvent;
import com.vinoigitare.model.Song;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(SongTree.class.getName());

	private TreeMap<String, TreeSet<Song>> songsByArtists = new TreeMap<String, TreeSet<Song>>();

	public SongTree(EventBus eventBus, Collection<Song> songs) {
		super();

		setSizeFull();
		setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		populateItems(songs);

		ItemClickListener listener = new SongTreeClickListener(eventBus);
		addItemClickListener(listener);

	}

	private void populateItems(Collection<Song> songs) {

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
			String name = artist;
			setItemCaption(artist, name);
			addItem(artist);
			setChildrenAllowed(artist, true);
			log.trace("Added Artist: " + artist);

			TreeSet<Song> songsByArtist = songsByArtists.get(artist);
			for (Song song : songsByArtist) {
				String title = song.getTitle();
				setItemCaption(song, title);
				addItem(song);
				setParent(song, artist);
				setChildrenAllowed(song, false);
				log.trace("Added Song: " + song);
			}
		}
	}

	public void addSong(Song song) {

		String artist = song.getArtist();

		if (!containsId(artist)) {

			setItemCaption(artist, artist);
			addItem(artist);
			setChildrenAllowed(artist, true);
			TreeSet<Song> songsByArtist = new TreeSet<Song>();
			songsByArtists.put(artist, songsByArtist);
			log.trace("Added Artist: " + artist);

		}

		addItem(song);

		String title = song.getTitle();
		setItemCaption(song, title);
		setParent(song, artist);
		setChildrenAllowed(song, false);
		TreeSet<Song> songsByArtist = songsByArtists.get(artist);
		songsByArtist.add(song);
		log.trace("Added Song: " + song);

	}

	public void removeSong(Song song) {

		String artist = (String) getParent(song);

		removeItem(song);
		TreeSet<Song> songsByArtist = songsByArtists.get(artist);
		songsByArtist.remove(song);
		log.trace("Removed Song: " + song);
		if (!hasChildren(artist)) {
			removeItem(artist);
			songsByArtists.remove(artist);
			log.trace("Removed Artist: " + artist);
		}
		sanitizeSelection();
	}

	public ArrayList<Song> getSongs() {

		ArrayList<Song> result = new ArrayList<Song>();

		Collection<TreeSet<Song>> songSets = songsByArtists.values();
		for (TreeSet<Song> songSet : songSets) {
			result.addAll(songSet);
		}

		return result;

	}

	@SuppressWarnings("serial")
	class SongTreeClickListener implements ItemClickListener {

		private EventBus eventBus;

		public SongTreeClickListener(EventBus eventBus) {
			super();
			this.eventBus = eventBus;
		}

		@Override
		public void itemClick(ItemClickEvent event) {

			Object itemId = event.getItemId();

			if (event.getButton() == MouseButton.LEFT) {

				if (itemId instanceof Song) {
					log.trace("Song selected: " + itemId);
					Song song = (Song) itemId;
					SongSelectedEvent songSelectedEvent = new SongSelectedEvent(
							song);
					eventBus.publish(songSelectedEvent);
				}

				else if (itemId instanceof String) {
					log.trace("Artist selected: " + itemId);
				}
			} else if (event.getButton() == MouseButton.RIGHT) {
				Notification.show("CONTEXT MENU!");
			}
		}
	}
}
