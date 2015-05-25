package com.vinoigitare.services;

import java.io.Serializable;
import java.util.Collection;

import com.vinoigitare.criteria.Criteria;
import com.vinoigitare.criteria.Filter;
import com.vinoigitare.model.Song;

public interface SongService extends Serializable {

	public abstract String store(Song song) throws SongServiceException;

	public abstract Song remove(String id) throws SongServiceException;

	public abstract Song load(String id) throws SongServiceException;
	
	public abstract Collection<Song> load(Criteria<Song> criteria) throws SongServiceException;

	public abstract Collection<Song> loadAll() throws SongServiceException;

	public abstract Collection<String> listIds() throws SongServiceException;

	public abstract boolean contains(String id) throws SongServiceException;
	
}