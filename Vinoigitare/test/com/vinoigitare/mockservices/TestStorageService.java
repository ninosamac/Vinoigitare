package com.vinoigitare.mockservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.ninosamac.storage.Storable;
import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.Storage;

public class TestStorageService implements Storage {
	
	private final TreeMap<String, Storable<?>> storage = new TreeMap<String, Storable<?>>();

	public TestStorageService() {
		// does nothing
	}

	
	@Override
	public void store(Storable<?> object) throws StorageException {
		storage.put((String) object.getId(), object);
	}

	@Override
	public void remove(Storable<?> object) throws StorageException {
		storage.remove(object.getId());
	}


	@Override
	public Storable<?> load(Class<? extends Storable<?>> clazz, Comparable<?> id)
			throws StorageException {
		return storage.get(id);
	}

	@Override
	public Collection<?> loadAll(Class<? extends Storable<?>> clazz)
			throws StorageException {
		// because strorage.values() is NOT Serializable!
		return new ArrayList<Storable<?>>(storage.values());
	}

	@Override
	public Collection<?> listIds(Class<? extends Storable<?>> clazz)
			throws StorageException {
		// because strorage.keySet() is NOT Serializable!
		return new ArrayList<String>(storage.keySet());
	}


	@Override
	public boolean contains(Class<? extends Storable<?>> clazz, Comparable<?> id)
			throws StorageException {
		return storage.containsKey(id);
	}

}
