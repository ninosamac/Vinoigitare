package com.vinoigitare.services;

import java.util.ArrayList;
import java.util.Collection;

import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongRemoved;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.settings.Settings;
import com.vinoigitare.storage.StorageException;
import com.vinoigitare.storage.file.text.SongTextFileStorage;

@SuppressWarnings("serial")
public class TextFileSongService implements SongService {

	private SettingsService settings = Settings.getInstance();
	private SongTextFileStorage storage;
	private SongServiceCache cache;
	private EventBus eventBus;

	public TextFileSongService(EventBus eventBus) {
		String folder = getSongsFolder();
		storage = new SongTextFileStorage(folder);
		cache = new SongServiceCache();
		this.eventBus = eventBus;
	}

	public void bindSettingsService(SettingsService service) {
		this.settings = service;
	}

	public void unbindSettingsService() {
		// Does nothing.
	}

	private String getSongsFolder() {
		return System.getenv("VINOIGITARE_HOME") + "/"
				+ settings.getValue("SONGS_FOLDER");
	}

	@Override
	public String store(Song song) throws SongServiceException {
		if (song == null) {
			throw new SongServiceException("Can not store: null");
		}
		try {
			storage.store(song);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
		cache.store(song);
		eventBus.publish(new SongCreated(song));
		return song.getId();
	}

	@Override
	public Song remove(String id) throws SongServiceException {
		if (id == null) {
			throw new SongServiceException("Can not remove: null");
		}
		try {
			storage.remove(id);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
		Song song = cache.remove(id);
		eventBus.publish(new SongRemoved(song));
		return song;
	}

	@Override
	public Song load(String id) throws SongServiceException {
		if (id == null) {
			throw new SongServiceException("Invalid argument: null");
		}

		if (cache.exists((String) id)) {
			return cache.load(id);
		}

		Song song;
		try {
			song = storage.load(id);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
		cache.store(song);
		return song;
	}

	@Override
	public Collection<Song> loadAll() throws SongServiceException {

		ArrayList<String> ids;
		try {
			ids = (ArrayList<String>) storage.listIds();
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
		ArrayList<Song> songs = new ArrayList<Song>();
		for (String id : ids) {
			Song song = null;
			if (cache.exists(id)) {
				song = cache.load(id);
			} else {
				try {
					song = storage.load(id);
					cache.store(song);
				} catch (StorageException e) {
					throw new SongServiceException(e.getMessage(), e);
				}
			}
			songs.add(song);
		}
		return songs;
	}

	@Override
	public Collection<String> listIds() throws SongServiceException {
		try {
			return storage.listIds();
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

	@Override
	public boolean contains(String id) throws SongServiceException {
		try {
			return storage.contains(id);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

}
