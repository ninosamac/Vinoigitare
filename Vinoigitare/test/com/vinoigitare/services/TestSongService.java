package com.vinoigitare.services;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import com.vinoigitare.model.Song;

public class TestSongService implements DataService<Song> {

	private TreeMap<String, Song> storage = new TreeMap<String, Song>();

	@Override
	public void store(Song song) throws DataServiceException {
		storage.put(song.getId(), song);
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		storage.remove(song);
	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		return storage.containsKey(song.getId());
	}

	@Override
	public Song load(Object id) throws DataServiceException {
		return storage.get(id);
	}

	@Override
	public Collection<Song> loadAll() throws DataServiceException {
		return storage.values();
	}

	@Override
	public Set<String> listIds() throws DataServiceException {
		return storage.keySet();
	}

}