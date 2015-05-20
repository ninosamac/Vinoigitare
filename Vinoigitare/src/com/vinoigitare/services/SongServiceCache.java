package com.vinoigitare.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.vinoigitare.model.Song;

public class SongServiceCache {

	private TreeMap<String, Song> songsByIds = new TreeMap<String, Song>();

	public SongServiceCache() {
		// Does nothing.
	}

	public void store(Song song) {
		songsByIds.put(song.getId(), song);
	}

	public Song remove(String id) {
		return songsByIds.remove(id);
	}

	public boolean exists(String id) {
		if (id == null) {
			throw new NullPointerException("Invalid argument: " + id);
		}
		return songsByIds.containsKey(id);
	}

	public Song load(String id) {
		return songsByIds.get(id);
	}

	public Collection<Song> loadAll() {
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.addAll(songsByIds.values());
		return songs;
	}

	public Collection<String> listIds() {
		ArrayList<String> ids = new ArrayList<String>();
		ids.addAll(songsByIds.keySet());
		return ids;
	}

	public boolean isEmpty() {
		return songsByIds.isEmpty();
	}

}
