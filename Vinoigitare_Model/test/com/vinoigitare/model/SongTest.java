package com.vinoigitare.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

public class SongTest {

	@Test(groups = { "fast" }, suiteName = "model")
	public void testEmptyConstructor() {

		Song song = new Song();
		assertNotNull(song.getArtist());
		assertNotNull(song.getChords());
		assertNotNull(song.getId());
		assertNotNull(song.getTitle());
	}

	@Test(groups = { "fast" }, expectedExceptions = { NullPointerException.class }, suiteName = "model")
	public void testConstructorThrowsNullPointException() {
		new Song("artist", "title", "chords");
		new Song("artist", "title", null);
		new Song("artist", null, "chords");
		new Song(null, "title", "chords");
		fail("Expected Exception not thrown.");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testConstructor() {

		Song song = new Song("artist", "title", "chords");
		assertEquals(song.getArtist(), "artist");
		assertEquals(song.getChords(), "chords");
		assertEquals(song.getId(), "artist - title");
		assertEquals(song.getTitle(), "title");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testEquals() {

		String artist1 = "artist1";
		String artist2 = "artist2";

		String chords1 = "chords1";
		String chords2 = "chords2";

		String title1 = "title1";
		String title2 = "title2";

		Song song1 = new Song(artist1, title1, chords1);
		Song song2 = new Song(artist1, title1, chords1);
		assertEquals(true, song1.equals(song2));

		song2 = new Song(artist1, title1, chords2);
		assertEquals(false, song1.equals(song2));

		song2 = new Song(artist1, title2, chords1);
		assertEquals(false, song1.equals(song2));

		song2 = new Song(artist2, title1, chords1);
		assertEquals(false, song1.equals(song2));
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testId() {

		String artist1 = "artist1";
		String artist2 = "artist2";

		Song song1 = new Song(artist1, "title1", "chords");
		Song song2 = new Song(artist1, "title1", "chords");
		Song song3 = new Song(artist2, "title1", "chords");
		Song song4 = new Song(artist1, "title2", "chords");

		assertEquals(song1.getId().equals(song2.getId()), true);
		assertEquals(song1.getId().equals(song3.getId()), false);
		assertEquals(song1.getId().equals(song4.getId()), false);
		assertEquals(song1.getId(), "artist1 - title1");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetId() {
		Song song = new Song();
		song.setId("new id");
		assertEquals("new id", song.getId());
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testGetIdAfterSettersCalled() {
		Song song = new Song();
		assertEquals(song.getId(), "unknown - no title");

		song.setArtist("Vojislav Šešelj");
		assertEquals(song.getId(), "Vojislav Šešelj - no title");

		song.setTitle("S kim si me noæas vavava");
		assertEquals(song.getId(), "Vojislav Šešelj - S kim si me noæas vavava");

		song = new Song();
		song.setTitle("S kim si me noæas vavava");
		assertEquals(song.getId(), "unknown - S kim si me noæas vavava");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetArtist() {
		Song song = new Song();
		String artist = "unknown";
		song.setArtist(artist);
		assertEquals(artist, song.getArtist());
	}

	@Test(groups = { "fast" }, suiteName = "model", expectedExceptions = { NullPointerException.class })
	public void testSetArtistThrowsNullPointerException() {
		Song song = new Song();
		song.setArtist(null);
		fail("No exception was thrown");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetTitle() {
		Song song = new Song();
		String title = "new title";
		song.setTitle(title);
		assertEquals(title, song.getTitle());
	}

	@Test(groups = { "fast" }, suiteName = "model", expectedExceptions = { NullPointerException.class })
	public void testSetTitleThrowsNullPointerException() {
		Song song = new Song();
		song.setTitle(null);
		fail("No exception was thrown");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetChords() {
		Song song = new Song();
		String chords = "new chords";
		song.setChords(chords);
		assertEquals(chords, song.getChords());
	}

	@Test(groups = { "fast" }, suiteName = "model", expectedExceptions = { NullPointerException.class })
	public void testSetChordsThrowsNullPointerException() {
		Song song = new Song();
		song.setChords(null);
		fail("No exception was thrown");
	}

}
