package com.vinoigitare;

import java.io.Serializable;

public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String artist;
	private final String title;
	private final String chords;

	public Song(String artist, String title, String chords) {
		super();
		this.artist = artist;
		this.title = title;
		this.chords = chords;
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
		return artist + " - " + title;
	}

}
