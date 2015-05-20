package com.vinoigitare.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.vinoigitare.mockservices.MockStorageData;
import com.vinoigitare.model.Song;

public class SongServiceCacheTest {

	@Test(groups = "fast")
	public void testStoreAndLoadSong(){

		SongServiceCache storage = new SongServiceCache();
		Song song = new MockStorageData().getMisoKovac_DalmacijaUMomOku();
		String id = song.getId();
		if (storage.exists(id)) {
			storage.remove(id);
		}
		assertFalse(storage.exists(id));

		Collection<?> ids = storage.listIds();

		assertFalse(ids.contains(id));

		storage.store(song);
		assertTrue(storage.exists(id));

		ids = (ArrayList<String>) storage.listIds();
		assertTrue(ids.contains(id));

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);

		storage.remove(id);
		assertFalse(storage.exists(id));

		ids = (ArrayList<String>) storage.listIds();
		assertFalse(ids.contains(id));

		assertTrue(ids.isEmpty());

	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testStoreThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.store(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testRemoveThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.remove(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testExistsThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.exists(null);
	}

	@Test(groups = "fast", expectedExceptions = { NullPointerException.class })
	public void testLoadThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.load(null);
	}

}
