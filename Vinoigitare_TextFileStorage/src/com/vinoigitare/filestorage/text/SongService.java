package com.vinoigitare.filestorage.text;

import java.util.Collection;

import com.vinoigitare.eventbus.Event;
import com.vinoigitare.eventbus.EventHandler;
import com.vinoigitare.events.SongCreated;
import com.vinoigitare.events.SongRemoved;
import com.vinoigitare.events.SongUpdated;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;
import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.settings.Settings;

@SuppressWarnings("rawtypes")
public class SongService implements DataService<Song>, EventHandler {

	private SettingsService settings = Settings.getInstance();
	private SongTextFileStorage storage;

	public SongService() {

		String folder = getSongsFolder();
		storage = new SongTextFileStorage(folder);

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
	public void store(Song song) throws DataServiceException {
		if (song == null) {
			throw new DataServiceException("Can not store: null");
		}
		storage.store(song);
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		if (song == null) {
			throw new DataServiceException("Can not remove: null");
		}
		storage.remove(song);
	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		if (song == null) {
			throw new DataServiceException("Invalid argument: null");
		}
		return storage.exists(song);
	}

	@Override
	public Song load(Comparable<?> id) throws DataServiceException {
		if (id == null) {
			throw new DataServiceException("Invalid argument: null");
		}
		return storage.load(id);
	}

	@Override
	public Collection<Song> loadAll() throws DataServiceException {
		return storage.loadAll();
	}

	@Override
	public Collection<?> listIds() throws DataServiceException {
		return storage.listIds();
	}

	@Override
	public void onEvent(Event event) {
		if (event.getType().equals(SongCreated.class)) {
			onSongCreated((SongCreated) event);
		} else if (event.getType().equals(SongUpdated.class)) {
			onSongUpdated((SongUpdated) event);
		} else if (event.getType().equals(SongRemoved.class)) {
			onSongRemoved((SongRemoved) event);
		}

	}

	private void onSongCreated(SongCreated event) {
		try {
			store(event.getSong());
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void onSongUpdated(SongUpdated event) {
		try {
			remove(event.getOldVersion());
			store(event.getNewVersion());
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void onSongRemoved(SongRemoved event) {
		try {
			remove(event.getSong());
		} catch (DataServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
