package com.vinoigitare;

import java.io.Serializable;

public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String artist;
	private String title;
	private String text;
	

	public Song(String artist, String title, String text) {
		super();
		this.artist = artist;
		this.title = title;
		this.text = text;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
