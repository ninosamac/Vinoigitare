package com.vinoigitare.filestorage.text;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataServiceException;

public class SongTextFileStorageTest {

	private static final String testFolder = System.getProperty("user.home")
			+ "/" + "Vinoigitare/Songs";

	@SuppressWarnings("unchecked")
	@Test(groups = "io")
	public void testStoreAndLoadSong() throws DataServiceException {
		SongTextFileStorage storage = new SongTextFileStorage(testFolder);

		
		Song song = new TestHelper().getTestSong();
		if (storage.contains(song)) {
			storage.remove(song);
		}
		assertEquals(storage.contains(song), false);

		Collection<?> ids = storage.listIds();

		assertEquals(ids.contains(song.getId()), false);

		storage.store(song);
		assertEquals(storage.contains(song), true);

		ids = (ArrayList<String>) storage.listIds();
		assertEquals(ids.contains(song.getId()), true);

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);

		// clean up afterwards.
		storage.remove(song);
	}

}
