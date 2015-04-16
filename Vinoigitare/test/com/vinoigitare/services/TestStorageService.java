package com.vinoigitare.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;

import com.ninosamac.storage.Storable;
import com.ninosamac.storage.StorageException;
import com.ninosamac.storage.StorageService;

public class TestStorageService implements StorageService {

	private TreeMap<Serializable, Storable<?>> storage = new TreeMap<Serializable, Storable<?>>();

	public TestStorageService() {
		// does nothing
	}

	@Override
	public void store(Storable<?> object) throws StorageException {
		storage.put((Serializable) object.getId(), object);
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
		return storage.values();
	}

	@Override
	public Collection<?> listIds(Class<? extends Storable<?>> clazz)
			throws StorageException {
		return storage.keySet();
	}

}
