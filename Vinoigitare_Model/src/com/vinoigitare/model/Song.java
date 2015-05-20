package com.vinoigitare.model;

import java.io.Serializable;

import com.vinoigitare.storage.Storable;

public class Song implements Serializable, Comparable<Song>, Storable
		{

	private static final String TITLE_UNKONWN = "no title";
	private static final String ARTIST_UNKNOWN = "unknown";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artist;
	private String title;
	private String chords;
	private String id;

	public Song() {
		artist = ARTIST_UNKNOWN;
		title = TITLE_UNKONWN;
		chords = "";
		id = artist + " - " + title;
	}

	public Song(String artist, String title, String chords) {
		super();
		if ((artist == null) || (title == null || chords == null))
			throw new NullPointerException("Constructor arguments are invalid.");
		this.artist = artist;
		this.title = title;
		this.chords = chords;
		id = artist + " - " + title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (artist == null) {
			throw new NullPointerException("Argument can not be null.");
		}
		this.artist = artist;
		this.id = artist + " - " + title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null) {
			throw new NullPointerException("Argument can not be null.");
		}
		this.title = title;
		this.id = artist + " - " + title;
	}

	public String getChords() {
		return chords;
	}

	public void setChords(String chords) {
		if (chords == null) {
			throw new NullPointerException("Argument can not be null.");
		}
		this.chords = chords;
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
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((chords == null) ? 0 : chords.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (chords == null) {
			if (other.chords != null)
				return false;
		} else if (!chords.equals(other.chords))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + id + "]";
	}

	public String asText() {
		StringBuilder sb = new StringBuilder();
		sb.append(artist);
		sb.append(" | ");
		sb.append(title);
		sb.append(" | ");
		sb.append(chords);
		return sb.toString();

	}
}
