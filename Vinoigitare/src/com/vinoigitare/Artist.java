package com.vinoigitare;

import java.io.Serializable;

public class Artist implements Serializable, Comparable<Artist> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;

	public Artist(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Artist o) {
		return name.compareTo(o.getName());
	}

}
