package com.vinoigitare.components.navigator;

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
import com.vinoigitare.Vinoigitare;
import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongSelected;
import com.vinoigitare.model.Song;

public class SongTree extends Tree {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(SongTree.class.getName());

	private Collection<Song> songs;
	private TreeMap<String, TreeSet<Song>> songsByArtists = new TreeMap<String, TreeSet<Song>>();
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
					SongSelected songSelected = new SongSelected(song);
					eventBus.publish(songSelected);
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
