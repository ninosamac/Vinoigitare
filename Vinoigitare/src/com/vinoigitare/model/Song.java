package com.vinoigitare.model;

import java.io.Serializable;

import com.ninosamac.storage.Storable;

public class Song implements Serializable, Comparable<Song>, Storable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Artist artist;
	private final String title;
	private final String chords;
	private String id;

	public Song(Artist artist, String title, String chords) {
		super();
		this.artist = artist;
		this.title = title;
		this.chords = chords;
		id = artist + " - " + title;
	}

	public Artist getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getChords() {
		return chords;
	}

	@Override
	public int compareTo(Song o) {
		return id.compareTo(o.getId());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		throw new UnsupportedOperationException(
				"Setting id for Song is not supported. See the class constructor.");
	}

}
