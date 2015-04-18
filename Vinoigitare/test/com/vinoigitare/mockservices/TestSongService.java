package com.vinoigitare.mockservices;

import java.util.Collection;
import java.util.Set;

import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.StorageService;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataService;
import com.vinoigitare.services.api.DataServiceException;

public class TestSongService implements DataService<Song> {

	private StorageService storage = new TestStorageService();

	public TestSongService() {
		init();
	}

	private void init() {
		TestStorageData data = new TestStorageData();

		try {
			store(data.getMisoKovac_DalmacijaUMomOku());
			store(data.getQueen_ItsAKindOfMagic());
			store(data.getMisoKovac_SamoJedanDanZivota());
			store(data.getMisoKovac_SviPjevajuJaNeCujem());
			store(data.getDivljeJagode_JedinaMoja());
			store(data.getDivljeJagode_KrivoJeMore());
		} catch (DataServiceException e) {
			e.printStackTrace();
		}

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
	public boolean exists(Song song) throws DataServiceException {
		try {
			return storage.exists(song);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Song load(Object id) throws DataServiceException {
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

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> listIds() throws DataServiceException {
		try {
			return (Set<String>) storage.listIds(Song.class);
		} catch (StorageException e) {
			throw new DataServiceException(e.getMessage(), e);
		}
	}

}
