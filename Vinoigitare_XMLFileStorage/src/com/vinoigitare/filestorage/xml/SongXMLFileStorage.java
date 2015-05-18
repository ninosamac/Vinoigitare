package com.vinoigitare.filestorage.xml;

import java.util.ArrayList;
import java.util.Collection;

import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.StorageId;
import com.ninosamac.storage.StringId;
import com.ninosamac.storage.file.xml.XMLFileStorage;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

@SuppressWarnings("serial")
public class SongXMLFileStorage implements DataService<Song> {

	private XMLFileStorage storage;

	public SongXMLFileStorage(String folder) {
		storage = new XMLFileStorage(folder);
	}

	@Override
	public String store(Song song) throws DataServiceException {
		StorageId<?> storageId = null;
		try {
			storageId = storage.store(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
		return (String) storageId.getValue();
	}

	@Override
	public Song remove(String id) throws DataServiceException {

		StringId storageId = new StringId((String) id);
		Song song = null;

		try {
			song = (Song) storage.load(Song.class, storageId);
			storage.remove(Song.class, storageId);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
		return song;
	}

	@Override
	public Song load(String id) throws DataServiceException {

		StringId storageId = new StringId((String) id);

		try {
			return (Song) storage.load(Song.class, storageId);
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
	public Collection<String> listIds() throws DataServiceException {
		try {
			Collection<StorageId<?>> storageIds = storage.listIds(Song.class);
			ArrayList<String> ids = new ArrayList<String>();
			for (StorageId<?> storageId : storageIds) {
				String id = (String) storageId.getValue();
				ids.add(id);
			}
			return ids;
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}

	}

	@Override
	public boolean contains(String id) throws DataServiceException {

		StringId storageId = new StringId((String) id);

		try {
			return storage.contains(Song.class, storageId);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

}
