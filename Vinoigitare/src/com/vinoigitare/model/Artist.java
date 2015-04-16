package com.vinoigitare.model;

import java.io.Serializable;

import com.ninosamac.storage.Storable;

public class Artist implements Serializable, Comparable<Artist>,
		Storable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;

	public Artist(String name) {
		super();
		if (name == null)
			throw new NullPointerException("Name can not be null.");
		this.name = name;
		id = name;
	}

	public Artist() {
		name = "unknown";
		id = "unknown";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("Argument can not be null.");
		}
		this.name = name;
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
		if (id == null) {
			throw new NullPointerException("Argument can not be null.");
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Artist other = (Artist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + "]";
	}

}
