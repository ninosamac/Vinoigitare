package com.vinoigitare.filestorage.text;

import java.util.Collection;

import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;
import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.settings.Settings;

public class SongService implements DataService<Song> {

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
		storage.store(song);
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		storage.remove(song);

	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		return storage.exists(song);
	}

	@Override
	public Song load(Comparable<?> id) throws DataServiceException {
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

}
