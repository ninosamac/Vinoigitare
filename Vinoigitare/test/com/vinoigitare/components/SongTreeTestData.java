package com.vinoigitare.components;

import java.util.ArrayList;
import java.util.Collection;

import com.vinoigitare.Artist;
import com.vinoigitare.Song;

public class SongTreeTestData {

	public static Collection<Song> generate() {
		ArrayList<Song> songs = new ArrayList<Song>();
	
		Artist artist1 = new Artist("Artist 1");
		Artist artist2 = new Artist("Artist 2");
		Artist artist3 = new Artist("Artist 3");
		songs.add(new Song(artist1, "Title 1", "Text 1/nText 1"));
		songs.add(new Song(artist1, "Title 2", "Text 2/nText 2"));
		songs.add(new Song(artist1, "Title 3", "Text 3/nText 3"));
		songs.add(new Song(artist2, "Title 4", "Text 4/nText 4"));
		songs.add(new Song(artist2, "Title 5", "Text 5/nText 5"));
		songs.add(new Song(artist3, "Title 6", "Text 6/nText 6"));
	
		return songs;
	
	}

}
