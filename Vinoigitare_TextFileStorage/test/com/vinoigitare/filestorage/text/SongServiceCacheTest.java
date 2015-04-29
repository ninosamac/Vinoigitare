package com.vinoigitare.filestorage.text;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataServiceException;

public class SongServiceCacheTest {
	
	@SuppressWarnings("unchecked")
	@Test(groups = "fast")
	public void testStoreAndLoadSong() throws DataServiceException {
		
		SongServiceCache storage = new SongServiceCache();
		Song song = new TestHelper().getTestSong();
		if (storage.exists(song.getId())) {
			storage.remove(song);
		}
		assertEquals(storage.exists(song.getId()), false);

		Collection<?> ids = storage.listIds();

		assertEquals(ids.contains(song.getId()), false);

		storage.store(song);
		assertEquals(storage.exists(song.getId()), true);

		ids = (ArrayList<String>) storage.listIds();
		assertEquals(ids.contains(song.getId()), true);

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);

		// clean up afterwards.
		storage.remove(song);
	
	}
	
	@Test(groups = "fast", expectedExceptions={NullPointerException.class})
	public void testStoreThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.store(null);
	}

	@Test(groups = "fast", expectedExceptions={NullPointerException.class})
	public void testRemoveThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.remove(null);
	}

	@Test(groups = "fast", expectedExceptions={NullPointerException.class})
	public void testExistsThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.exists(null);
	}
	
	@Test(groups = "fast", expectedExceptions={NullPointerException.class})
	public void testLoadThrowsNullPointerException() {
		SongServiceCache storage = new SongServiceCache();
		storage.load(null);		
	}

}
