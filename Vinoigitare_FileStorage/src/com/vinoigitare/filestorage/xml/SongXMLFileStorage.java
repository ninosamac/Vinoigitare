package com.vinoigitare.filestorage.xml;

import java.util.Collection;

import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.file.xml.FileXMLStorage;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class SongXMLFileStorage implements DataService<Song> {

	private FileXMLStorage storage;

	public SongXMLFileStorage(String folder) {
		storage = new FileXMLStorage(folder);
	}

	@Override
	public void store(Song song) throws DataServiceException {
		try {
			storage.store(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void remove(Song song) throws DataServiceException {
		try {
			storage.remove(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Song load(Comparable<?> id) throws DataServiceException {
		try {
			return (Song) storage.load(Song.class, id);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
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

	@Override
	public boolean contains(Comparable<?> id) throws DataServiceException {
		try {
			return storage.contains(Song.class, id);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

}
