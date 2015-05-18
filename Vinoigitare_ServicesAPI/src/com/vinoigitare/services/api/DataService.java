package com.vinoigitare.services.api;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface for services providing storage for objects of type <code>T</code>.
 * Uses ids of type <code>String</String> to address objects.
 * <p>
 * Note: String <code>ids</code> used for accessing objects may be created by
 * {@link #store(Object)} method, or may be provided by user beforehand. Check
 * implementation for this.
 * 
 * @author nino.samac
 *
 * @param <T>
 */
public interface DataService<T> extends Serializable {

	/**
	 * Store specified object. If there is an object of the same class and the
	 * same id already in the data store, it will be overridden.
	 * <p>
	 * Note: id for object may be created by this method, or it
	 * 
	 * @param object
	 *            object to be stored
	 * @return id of stored object.
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	String store(T object) throws DataServiceException;

	/**
	 * Removes object from data store.
	 * 
	 * @param id
	 *            id of object to be removed
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	void remove(String id) throws DataServiceException;

	/**
	 * Checks if there is an object of the same class and the same id already in
	 * the data store.
	 * 
	 * @param id
	 *            object's identifier
	 * @return <code>true</code> if stored, <code>false</code> if not
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	boolean contains(String id) throws DataServiceException;

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

	T load(String id) throws DataServiceException;

	/**
	 * Returns all stored objects of the type <code>T</code>. Note: if there are
	 * many objects in the datastore, this method might run slow. In such cases,
	 * consider using {@link #listIds(Class)} and lazy load.
	 * 
	 * @param T
	 *            class type of objects to load
	 * @return all objects of the type <code>T</code>
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	Collection<T> loadAll() throws DataServiceException;

	/**
	 * Returns ids of all stored objects of the type <code>T</code>.
	 * 
	 * @return ids of all stored objects of the type <code>T</code>
	 * @throws DataServiceException
	 *             if any error occurs
	 */
	Collection<String> listIds() throws DataServiceException;

}
