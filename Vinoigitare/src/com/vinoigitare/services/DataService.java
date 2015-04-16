package com.vinoigitare.services;

import java.util.Collection;
import java.util.List;

public interface DataService<T> {

	/**
	 * Store specified object. If there is an object of the same class and the
	 * same id already in the data store, it will be overridden.
	 * 
	 * @param object
	 *            object to be stored
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	void store(T object) throws DataServiceException;

	/**
	 * Removes object from data store.
	 * 
	 * @param object
	 *            reference to object to be removed
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	void remove(T object) throws DataServiceException;

	/**
	 * Checks if there is an object of the same class and the same id already in
	 * the data store.
	 * <p>
	 * Note: this function does not check for equality. If you need to check if
	 * the stored object is equal to <code>object</code>, get the stored object
	 * by it's id and then compare the two objects.
	 * 
	 * @param object
	 *            reference to object for which to check
	 * @return <code>true</code> if stored, <code>false</code> if not
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	boolean exists(T object) throws DataServiceException;

	/**
	 * Loads object of the given type and with the given id from data store.
	 *
	 * @param id
	 *            object's identifier
	 * @return the object of the type <code>T</code> and with the id
	 *         <code>id</code> if such object exists, and <code>null</code> if
	 *         not
	 * @throws DataServiceException
	 *             if any error occurs
	 */

	T load(Object id) throws DataServiceException;

	/**
	 * Returns all stored objects of the type <code>T</code>. Note: if there
	 * are many objects in the datastore, this method might run slow. In such
	 * cases, consider using {@link #listIds(Class)} and lazy load.
	 * 
	 * @param T
	 *            class type of objects to load
	 * @return list of all objects of the type <code>T</code>
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	Collection<T> loadAll() throws DataServiceException;

	/**
	 * Returns ids of all stored objects of the type <code>T</code>.
	 * 
	 * @return list of ids of all stored objects of the type <code>T</code>
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	Collection<?> listIds() throws DataServiceException;

}
