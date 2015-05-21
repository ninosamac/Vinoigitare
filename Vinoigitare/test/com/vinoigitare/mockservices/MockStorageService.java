package com.vinoigitare.mockservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.vinoigitare.model.Song;
import com.vinoigitare.storage.api.Storage;
import com.vinoigitare.storage.api.StorageException;

@SuppressWarnings("serial")
public class MockStorageService implements Storage<Song> {

	private final TreeMap<String, Song> storage = new TreeMap<String, Song>();

	public MockStorageService() {
		// does nothing
	}

	@Override
	public String store(Song song) throws StorageException {
		String id = song.getId();
		storage.put(id, song);
		return id;

	}

	@Override
	public void remove(String id) throws StorageException {
		storage.remove(id);
	}

	@Override
	public Song load(String id) throws StorageException {
		return storage.get(id);
	}

	@Override
	public Collection<Song> loadAll() throws StorageException {
		// because TreeMap.values() is NOT Serializable!
		return new ArrayList<Song>(storage.values());
	}

	@Override
	public Collection<String> listIds() throws StorageException {
		// because TreeMap.keySet() is NOT Serializable!
		ArrayList<String> ids = new ArrayList<String>(storage.keySet());
		return ids;
	}

	@Override
	public boolean contains(String id) throws StorageException {
		return storage.containsKey(id);
	}

}
