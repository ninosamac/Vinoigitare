package com.vinoigitare.model;

import java.io.Serializable;

import com.ninosamac.storage.Storable;

public class Artist implements Serializable, Comparable<Artist>,
		Storable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private String id;

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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
