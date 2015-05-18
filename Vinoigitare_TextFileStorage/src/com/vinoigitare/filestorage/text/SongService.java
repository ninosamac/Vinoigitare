package com.vinoigitare.filestorage.text;

import java.util.ArrayList;
import java.util.Collection;

import com.vinoigitare.eventbus.EventBus;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongRemoved;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;
import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.settings.Settings;

@SuppressWarnings("serial")
public class SongService implements DataService<Song> {

	private SettingsService settings = Settings.getInstance();
	private SongTextFileStorage storage;
	private SongServiceCache cache;
	private EventBus eventBus;

	public SongService(EventBus eventBus) {
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
	public String store(Song song) throws DataServiceException {
		if (song == null) {
			throw new DataServiceException("Can not store: null");
		}
		storage.store(song);
		cache.store(song);
		eventBus.publish(new SongCreated(song));
		return song.getId();
	}

	@Override
	public Song remove(String id) throws DataServiceException {
		if (id == null) {
			throw new DataServiceException("Can not remove: null");
		}
		Song song = storage.remove(id);
		cache.remove(id);
		eventBus.publish(new SongRemoved(song));
		return song;
	}

	@Override
	public Song load(String id) throws DataServiceException {
		if (id == null) {
			throw new DataServiceException("Invalid argument: null");
		}

		if (cache.exists((String) id)) {
			return cache.load(id);
		}

		Song song = storage.load(id);
		cache.store(song);
		return song;
	}

	@Override
	public Collection<Song> loadAll() throws DataServiceException {

		ArrayList<String> ids = (ArrayList<String>) storage.listIds();
		ArrayList<Song> songs = new ArrayList<Song>();
		for (String id : ids) {
			Song song = null;
			if (cache.exists(id)) {
				song = cache.load(id);
			} else {
				song = storage.load(id);
				cache.store(song);
			}
			songs.add(song);
		}
		return songs;
	}

	@Override
	public Collection<String> listIds() throws DataServiceException {
		return storage.listIds();
	}

	@Override
	public boolean contains(String id) throws DataServiceException {
		return storage.contains(id);
	}

}
