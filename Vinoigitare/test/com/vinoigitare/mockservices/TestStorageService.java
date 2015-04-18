package com.vinoigitare.mockservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import com.ninosamac.storage.Storable;
import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.StorageService;

public class TestStorageService implements StorageService {
	
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
	public boolean exists(Storable<?> object) throws StorageException {
		return storage.containsKey(object.getId());
	}

	@Override
	public Storable<?> load(Class<? extends Storable<?>> clazz, Object id)
			throws StorageException {
		return storage.get(id);
	}

	@Override
	public Collection<?> loadAll(Class<? extends Storable<?>> clazz)
			throws StorageException {
		// TODO: strorage.values() is NOT serializable!
		return new ArrayList<Storable<?>>(storage.values());
	}

	@Override
	public Collection<?> listIds(Class<? extends Storable<?>> clazz)
			throws StorageException {
		// TODO: strorage.keySet() is NOT serializable!
		return new ArrayList<String>(storage.keySet());
	}

}
