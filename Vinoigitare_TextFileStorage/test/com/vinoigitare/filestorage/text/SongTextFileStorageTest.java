package com.vinoigitare.filestorage.text;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.testng.annotations.Test;

import com.vinoigitare.model.Artist;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.api.DataServiceException;

public class SongTextFileStorageTest {

	private static final String testFolder = System.getProperty("user.home")
			+ "/" + "Vinoigitare/Songs";

	@SuppressWarnings("unchecked")
	@Test(groups = "io")
	public void testStoreAndLoadSong() throws DataServiceException {
		SongTextFileStorage storage = new SongTextFileStorage(testFolder);

		Song song = getTestSong();
		if (storage.exists(song)) {
			storage.remove(song);
		}
		assertEquals(storage.exists(song), false);

		Collection<?> ids = storage.listIds();

		assertEquals(ids.contains(song.getId()), false);

		storage.store(song);
		assertEquals(storage.exists(song), true);

		ids = (ArrayList<String>) storage.listIds();
		assertEquals(ids.contains(song.getId()), true);

		Song song1 = null;
		song1 = storage.load(song.getId());
		assertEquals(song, song1);

		// clean up afterwards.
		// storage.remove(song);

	}

	private Song getTestSong() {
		Artist artist = new Artist("Mišo Kovaè");
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
