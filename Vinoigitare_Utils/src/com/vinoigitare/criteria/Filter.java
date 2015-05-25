package com.vinoigitare.criteria;

import java.io.Serializable;
import java.util.Collection;

/**
 * Classes used for filtering collections of type <code>T</code> should
 * implement this interface.
 * <p>
 * This interface couples nicely with {@link Criteria} which is used for
 * checking instances.
 * 
 * @author nino.samac
 * 
 * @param <T>
 *            the type of objects for this Filter.
 * @see Criteria
 */
public interface Filter<T> extends Serializable {

	Collection<T> applyTo(Collection<T> collection);

}