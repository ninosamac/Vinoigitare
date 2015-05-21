package com.vinoigitare.criteria;

import java.io.Serializable;

/**
 * Classes that filter some collection of objects of type <code>T</code> should
 * implement this interface.<br>
 * See {@link Filter} and {@link Criteria} for usage of filtering logic.
 * 
 * @author nino.samac
 *
 * @param <T>
 */
public interface HasFilter<T> extends Serializable {

	void setFilter(Filter<T> filter);

	Filter<T> getFilter();

}