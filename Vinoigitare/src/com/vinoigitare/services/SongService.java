package com.vinoigitare.services;

import java.util.List;

import com.vinoigitare.model.Song;

public class SongService implements DataService<Song> {

	@Override
	public void store(Song object) throws DataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Song object) throws DataServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Song object) throws DataServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Song load(Song id) throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Song> loadAll() throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> listIds() throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
