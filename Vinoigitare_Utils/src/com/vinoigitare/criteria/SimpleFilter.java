package com.vinoigitare.criteria;

import java.util.Collection;
import java.util.TreeSet;

/**
 * A simple {@link Filter} that uses a single {@link Criteria} for filtering
 * collections of objects of type <code>T</code>.<br>
 * Note: Criteria for this filter can be simple or very complex, i.e. a
 * composite ORCriteria consisting of many ANDCriteria.
 * 
 * @see Filter
 * @see Criteria
 * @see ANDCriteria
 * @see ORCriteria
 * 
 * @author nino.samac
 *
 * @param <T>
 *            the type for this Filter
 */
@SuppressWarnings("serial")
public class SimpleFilter<T> implements Filter<T> {

	private Criteria<T> criteria;

	public SimpleFilter(){
		// Does nothing.
	}
	
	public SimpleFilter(Criteria<T> criteria) {
		this.criteria = criteria;
	}

	public Criteria<T> getCriteria() {
		return this.criteria;
	}

	public void setCriteria(Criteria<T> criteria) {
		this.criteria = criteria;
	}

	@Override
	public Collection<T> applyTo(Collection<T> collection) {
		TreeSet<T> result = new TreeSet<T>();

		if (collection == null) {
			throw new NullPointerException("Can not filter null");
		}

		// if no criteria is set, return empty collection
		if (criteria == null) {
			return result;
		}

		for (T t : collection) {
			if (criteria.isSatisfiedBy(t)) {
				result.add(t);
			}
		}

		return result;
	}
}
