package com.vinoigitare.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vinoigitare.filestorage.text.SongTextFileStorage;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class SongService implements DataService<Song> {

	private static final String SONGS_FOLDER = "C://Vinoigitare/Songs";
	private SongTextFileStorage storage = new SongTextFileStorage(SONGS_FOLDER);
	private static final Log log = LogFactory.getLog(SongService.class
			.getName());

	@Override
	public void store(Song song) throws DataServiceException {
		log.debug("Storing: " + song);
		storage.store(song);
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		log.debug("Removing: " + song);
		storage.remove(song);
	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		return storage.exists(song);
	}

	@Override
	public Song load(Object id) throws DataServiceException {
		log.debug("Loading: "+id.toString());
		return storage.load(id);
	}

	@Override
	public List<Song> loadAll() throws DataServiceException {
		return storage.loadAll();
	}

	@Override
	public List<?> listIds() throws DataServiceException {
		return storage.listIds();
	}

}
