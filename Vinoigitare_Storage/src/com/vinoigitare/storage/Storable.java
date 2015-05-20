package com.vinoigitare.storage;

import java.io.Serializable;


/**
 * Objects that need to be persisted should implement this interface.
 * 
 * @author nino.samac
 * 
 */
public interface Storable extends Serializable {
	/**
	 * Returns the storage id of this object.
	 * 
	 * @return the storage id
	 */
	String getId();

	/**
	 * Sets the persistence id.
	 * 
	 * @param id
	 *            persistence id for this object
	 */
	void setId(String id);

}