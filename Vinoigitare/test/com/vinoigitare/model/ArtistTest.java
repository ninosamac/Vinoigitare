package com.vinoigitare.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class ArtistTest {
	
	@Test(groups = { "fast" }, suiteName = "model")
	public void testEmptyConstructor() {
		Artist artist = new Artist();
		assertNotNull(artist.getName());
		assertNotNull(artist.getId());		
	}
	
	@Test(groups = { "fast" }, expectedExceptions = { NullPointerException.class }, suiteName = "model")
	public void testConstructorThrowsNullPointException() {
		new Artist(null);		
		fail("Expected Exception not thrown.");
	}

	@Test(groups = { "fast" }, suiteName = "model")
	public void testConstructor() {
		Artist artist = new Artist("artist");
		assertEquals(artist.getName(), "artist");
	}
	
	@Test(groups = { "fast" }, suiteName = "model")
	public void testEquals() {
		Artist artist1 = new Artist("artist");
		Artist artist2 = new Artist("artist");
		Artist artist3 = new Artist("artist3");
		assertEquals(true, artist1.equals(artist2));
		assertEquals(false, artist1.equals(artist3));
	}
	
	
}
