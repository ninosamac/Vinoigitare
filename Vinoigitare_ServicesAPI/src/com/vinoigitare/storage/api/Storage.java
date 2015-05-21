package com.vinoigitare.storage.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * Classes implementing this interface provide persistence for {@link Storable}
 * objects. Use one instance per class.
 * <p>
 * Note: {@link Storable} objects have a <code>getId()</code> and
 * <code>setId()</code> methods used for Storage ids. Some implementations of
 * this interface may set the {@link StorageId} on storing the object, while
 * others may expect {@link StorageId} to be set by user beforehand.
 * 
 * @author nino.samac
 * 
 */
public interface Storage<T extends Storable> extends Serializable{

	/**
	 * Store specified object. If there is an object of the same class and the
	 * same id already in the data store, it will be overridden.
	 * 
	 * @param object
	 *            object to be stored
	 * @return id of stored object
	 * @throws StorageException
	 *             if any error occurs
	 */
	public String store(T object) throws StorageException;

	/**
	 * Removes object from data store.
	 * 
	 * @param id
	 *            identifier of the object to be removed
	 * @throws StorageException
	 *             if any error occurs
	 */
	void remove(String id) throws StorageException;


	/**
	 * Checks if there is an object of the same class and the same id already in
	 * the data store.
	 * 
	 * @param id
	 *            id to check
	 * @return <code>true</code> if stored, <code>false</code> if not
	 * @throws StorageException
	 *             if any error occurs
	 */
	boolean contains(String id)
			throws StorageException;
	
	/**
	 * Loads object of the given type and with the given id from data store.
	 * 
	 * @param id
	 *            object's identifier
	 * @return the object with the id <code>id</code> if such object exists, and
	 *         <code>null</code> if not
	 * @throws StorageException
	 *             if any error occurs
	 */
	Storable load(String id) throws StorageException;

	/**
	 * Lists all stored objects. Note: if there
	 * are many objects in the datastore, this method might run slow. In such
	 * cases, consider using {@link #listIds(Class)} and lazy load.
	 * 
	 * @return list of all stored objects
	 * @throws StorageException
	 *             if any error occurs
	 */
	Collection<T> loadAll()
			throws StorageException;

	/**
	 * Lists ids of all stored objects.
	 * 
	 * @param clazz
	 *            class type of objects to list
	 * @return list of ids of all stored objects of the type <code>clazz</code>
	 * @throws StorageException
	 *             if any error occurs
	 */
	Collection<String> listIds()
			throws StorageException;


}
