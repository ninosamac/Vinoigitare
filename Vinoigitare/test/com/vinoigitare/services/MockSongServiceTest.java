package com.vinoigitare.services;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.vinoigitare.criteria.Criteria;
import com.vinoigitare.mockservices.MockSongService;
import com.vinoigitare.mockservices.MockStorageData;
import com.vinoigitare.model.Song;

public class MockSongServiceTest {

	@Test(groups = "fast")
	public void testStoreAndLoadSong() throws SongServiceException {

		MockSongService storage = new MockSongService();
		Song song = new MockStorageData().getMisoKovac_DalmacijaUMomOku();
		String id = song.getId();
		if (storage.contains(id)) {
			storage.remove(id);
		}
		assertFalse(storage.contains(id));

		Collection<?> ids = storage.listIds();

		assertFalse(ids.contains(id));

		storage.store(song);
		assertTrue(storage.contains(id));

		ids = (ArrayList<String>) storage.listIds();
		assertTrue(ids.contains(id));

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);

		storage.remove(id);
		assertFalse(storage.contains(id));

		ids = (ArrayList<String>) storage.listIds();
		assertFalse(ids.contains(id));

	}

	@SuppressWarnings("unchecked")
	@Test(groups = "fast")
	public void testLoadWithCriteria() throws SongServiceException {

		MockSongService storage = new MockSongService();
		Criteria<Song> criteria = null;
		Collection<Song> result = null;

		criteria = Criteria.NEVER_SATISFIED;
		result = storage.load(criteria);
		assertTrue(result.isEmpty());

		criteria = Criteria.ALWAYS_SATISFIED;
		result = storage.load(criteria);
		assertFalse(result.isEmpty());
		assertEquals(result.size(), 6);

		criteria = new MisoKovacCriteria();
		result = storage.load(criteria);
		assertFalse(result.isEmpty());
		assertEquals(result.size(), 3);
		assertTrue(result.contains(new MockStorageData().getMisoKovac_DalmacijaUMomOku()));

	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testStoreThrowsNullPointerException()
			throws SongServiceException {
		MockSongService storage = new MockSongService();
		storage.store(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testRemoveThrowsNullPointerException()
			throws SongServiceException {
		MockSongService storage = new MockSongService();
		storage.remove(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testExistsThrowsNullPointerException()
			throws SongServiceException {
		MockSongService storage = new MockSongService();
		storage.contains(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testLoadThrowsNullPointerException()
			throws SongServiceException {
		MockSongService storage = new MockSongService();
		String id = null;
		storage.load(id);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testLoadWithCriteriaThrowsNullPointerException()
			throws SongServiceException {
		MockSongService storage = new MockSongService();
		Criteria<Song> criteria = null;
		storage.load(criteria);
	}

	@SuppressWarnings("serial")
	class MisoKovacCriteria implements Criteria<Song> {

		@Override
		public boolean isSatisfiedBy(Song t) {
			String searchableText = t.getSearchableText();
			return searchableText.contains("miso kovac");
		}

	}

}
