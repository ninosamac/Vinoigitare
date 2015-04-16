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
	
	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetId() {
		Artist artist = new Artist();
		artist.setId("new id");
		assertEquals("new id", artist.getId());
	}
	
	@Test(groups = { "fast" }, suiteName = "model", expectedExceptions={NullPointerException.class})
	public void testSetIdThrowsNullPointerException() {
		Artist artist = new Artist();
		artist.setId(null);
		fail("Exception not thrown");
	}
	
	@Test(groups = { "fast" }, suiteName = "model")
	public void testSetName() {
		Artist artist = new Artist();
		artist.setName("new name");
		assertEquals("new name", artist.getName());
	}
	
	@Test(groups = { "fast" }, suiteName = "model", expectedExceptions={NullPointerException.class})
	public void testSetNameThrowsNullPointerException() {
		Artist artist = new Artist();
		artist.setName(null);
		fail("Exception not thrown");
	}
}
