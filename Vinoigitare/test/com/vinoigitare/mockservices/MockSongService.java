package com.vinoigitare.mockservices;

import java.util.Collection;

import com.vinoigitare.criteria.Criteria;
import com.vinoigitare.criteria.SimpleFilter;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.SongService;
import com.vinoigitare.services.SongServiceException;
import com.vinoigitare.storage.api.StorageException;

@SuppressWarnings("serial")
public class MockSongService implements SongService {

	private MockStorageService storage = new MockStorageService();

	public MockSongService() {
		init();
	}

	private void init() {
		MockStorageData data = new MockStorageData();

		try {
			store(data.getMisoKovac_DalmacijaUMomOku());
			store(data.getQueen_ItsAKindOfMagic());
			store(data.getMisoKovac_SamoJedanDanZivota());
			store(data.getMisoKovac_SviPjevajuJaNeCujem());
			store(data.getDivljeJagode_JedinaMoja());
			store(data.getDivljeJagode_KrivoJeMore());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String store(Song song) throws SongServiceException {

		try {
			storage.store(song);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}

		return song.getId();
	}

	public Song remove(String id) throws SongServiceException {
		try {
			Song result = storage.load(id);
			storage.remove(id);
			return result;
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

	public boolean contains(String id) throws SongServiceException {
		try {
			return storage.contains(id);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

	public Song load(String id) throws SongServiceException {
		try {
			return storage.load(id);
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<Song> load(Criteria<Song> criteria)
			throws SongServiceException {
		if (criteria == null) {
			throw new NullPointerException("Invalid argument: null");
		}

		SimpleFilter<Song> filter = new SimpleFilter<Song>(criteria);
		try {
			return filter.applyTo(storage.loadAll());
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}

	}

	public Collection<Song> loadAll() throws SongServiceException {
		try {
			return storage.loadAll();
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

	public Collection<String> listIds() throws SongServiceException {
		try {
			return storage.listIds();
		} catch (StorageException e) {
			throw new SongServiceException(e.getMessage(), e);
		}
	}

}
