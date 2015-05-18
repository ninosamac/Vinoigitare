package com.vinoigitare.filestorage.xml;

import static org.testng.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataServiceException;

public class SongXMLFileStorageTest {
	
	private static final String FOLDER = System.getProperty("user.home") + "/"
			+ "temp/SongXMLFileStorage";

	@Test(groups = "io")
	public void testStoreAndLoadSong() throws DataServiceException {
		SongXMLFileStorage storage = new SongXMLFileStorage(FOLDER);

		Song song = getTestSong();
		String id = song.getId();
		if (storage.contains(id)) {
			storage.remove(id);
		}
		assertEquals(storage.contains(id), false);

		Collection<?> ids = storage.listIds();
		assertEquals(ids.contains(id), false);

		storage.store(song);
		assertEquals(storage.contains(id), true);		

		ids = storage.listIds();
		assertTrue(ids.contains(id));
		Collection<Song> songs = storage.loadAll();
		assertTrue(songs.contains(song));		

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);
		
		storage.remove(id);
		assertFalse(storage.contains(id));
		assertFalse(storage.listIds().contains(id));

		// clean up afterwards.
		try {
			FileUtils.deleteDirectory(new File(FOLDER));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Song getTestSong() {
		String artist = "Mišo Kovaè";
		Song song = new Song(artist, "Dalmacija u mom oku",
				"Pitaju me zasto pjevam\n" + "o tom moru, buri, jugu\n"
						+ "govore mi kako sanjam\n" + "obecanu zemlju drugu\n"
						+ "\n" + "Govore mi da se trgnem\n"
						+ "dok je vrijeme, dok se moze\n"
						+ "k'o da mogu isprat kise\n"
						+ "onu davnu sol sa koze\n" + "\n" + "Ref.\n"
						+ "Vidim jata onih istih ptica\n"
						+ "istu zvijezdu previsoku\n"
						+ "opet zivi stotinama lica\n"
						+ "Dalmacija u mom oku\n" + "\n"
						+ "Znam da nije bila samo moja\n"
						+ "mozda sam u snu duboku\n"
						+ "ali zivi stotinama boja\n" + "Dalmacija u mom oku\n"
						+ "\n" + "Govore mi cemu price\n"
						+ "ljudi drugi zivot zive\n"
						+ "odvezane sve su barke\n"
						+ "i brodovi s' tvoje rive\n" + "\n"
						+ "Ali negdje zvona zvone\n" + "i skidaju ljudi kape\n"
						+ "i dok djecji plac se cuje\n"
						+ "sastaju se nove klape\n" + "\n" + "Ref.");
		return song;
	}

}
