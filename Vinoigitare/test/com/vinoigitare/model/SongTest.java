package com.vinoigitare.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SongTest {
	@Test(groups = { "fast" }, expectedExceptions = { NullPointerException.class }, suiteName = "model")
	public void testConstructorThrowsNullPointException() {
		new Song(new Artist("artist"), "title", "chords");
		new Song(new Artist("artist"), "title", null);
		new Song(new Artist("artist"), null, "chords");
		new Song(null, "title", "chords");
		fail("Expected Exception not thrown.");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testConstructor() {
		
		Song song = new Song(new Artist("artist"), "title", "chords");
		assertEquals(song.getArtist(), new Artist("artist"));
		assertEquals(song.getChords(), "chords");
		assertEquals(song.getId(), "artist - title");
		assertEquals(song.getTitle(), "title");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testEquals() {
		
		Artist artist1 = new Artist("artist1");
		Artist artist2 = new Artist("artist2");

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
		
		Artist artist1 = new Artist("artist");
		Artist artist2 = new Artist("artist");
		
		Song song1 = new Song(artist1, "title", "chords");		
		Song song2 = new Song(artist1, "title", "chords");
		Song song3 = new Song(artist2, "title", "chords");
				
		assertEquals(song1.getId().equals(song2.getId()), true);
		assertEquals(song1.getId().equals(song3.getId()), true);
		assertEquals(song1.getId(), "artist - title");
		
	}
	
}
