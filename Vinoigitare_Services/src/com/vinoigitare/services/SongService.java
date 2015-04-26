package com.vinoigitare.services;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ninosamac.storage.Storage;
import com.ninosamac.storage.StorageException;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class SongService implements DataService<Song> {

	private Storage storage;
	private static final Log log = LogFactory.getLog(SongService.class
			.getName());

	public SongService(Storage storage){
		this.storage = storage;
	}
	
	@Override
	public void store(Song song) throws DataServiceException {
		log.debug("Storing: " + song);
		try {
			storage.store(song);
		} catch (StorageException e) {
		throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		log.debug("Removing: " + song);
		try {
			storage.remove(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public boolean exists(Song song) throws DataServiceException {
		try {
			return storage.exists(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Song load(Comparable<?> id) throws DataServiceException {
		log.debug("Loading: "+id.toString());
		try {
			return (Song) storage.load(Song.class, id);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<Song> loadAll() throws DataServiceException {
		try {
			return (Collection<Song>) storage.loadAll(Song.class);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<?> listIds() throws DataServiceException {
		try {
			return storage.listIds(Song.class);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

}
