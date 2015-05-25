package com.vinoigitare.criteria;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utility class for filtering collections based on {@link ANDCriteria}.
 * 
 * @author nino.samac
 * 
 * @param <T>
 *            the type for this {@link Filter}
 */
@SuppressWarnings("serial")
public class ANDFilter<T> implements Filter<T> {

	private ANDCriteria<T> criteriaSet = new ANDCriteria<T>();

	@Override
	public Collection<T> applyTo(Collection<T> collection) {
		ArrayList<T> result = new ArrayList<T>();

		if (criteriaSet.isEmpty()) {
			return result;
		}

		for (T t : collection) {
			if (criteriaSet.isSatisfiedBy(t)) {
				result.add(t);
			}
		}

		return result;
	}

	public void addCriteria(Criteria<T> criteria) {
		criteriaSet.add(criteria);
	}

	public void removeCriteria(Criteria<T> criteria) {
		criteriaSet.remove(criteria);
	}

	public void clear() {
		criteriaSet.clear();
	}

	public boolean isEmpty() {
		return criteriaSet.isEmpty();
	}

}
