package com.vinoigitare.textfilestorage;

import java.util.Collection;

import com.ninosamac.storage.file.util.FolderUtil;
import com.vinoigitare.model.Song;
import com.vinoigitare.services.DataService;
import com.vinoigitare.services.DataServiceException;

public class SongService implements DataService<Song>{

	private FolderUtil storage;
	
	public SongService(String folder){
		storage = new FolderUtil(folder);
	}

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
	public Song load(Object id) throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Song> loadAll() throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> listIds() throws DataServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
