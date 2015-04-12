package com.vinoigitare;

import java.io.Serializable;

public class Song implements Serializable, Comparable<Song> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String artist;
	private final String title;
	private final String chords;
	private String key;

	public Song(String artist, String title, String chords) {
		super();
		this.artist = artist;
		this.title = title;
		this.chords = chords;
		key = artist + " - " + title;
	}

	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getChords() {
		return chords;
	}

	public String getKey() {
		return key;
	}

	@Override
	public int compareTo(Song o) {
		return key.compareTo(o.getKey());
	}
	
}
