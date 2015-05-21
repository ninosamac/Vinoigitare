package com.vinoigitare.storage.file.text;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.vinoigitare.model.Song;
import com.vinoigitare.storage.api.StorageException;

public class SongTextFileStorageTest {

	private static final String FOLDER = System.getProperty("user.home") + "/"
			+ "temp/SongTextFileStorageTest";

	@Test(groups = "io")
	public void testStoreAndLoadSong() throws StorageException {
		SongTextFileStorage storage = new SongTextFileStorage(FOLDER);

		Song song = new TestHelper().getTestSong();
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
		song1 = storage.load(id);
		assertEquals(song, song1);

		storage.remove(id);
		assertFalse(storage.contains(id));
		ids = storage.listIds();
		assertFalse(ids.contains(id));

		// clean up afterwards.
		try {
			FileUtils.deleteDirectory(new File(FOLDER));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
