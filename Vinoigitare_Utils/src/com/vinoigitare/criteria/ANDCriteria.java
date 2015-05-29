package com.vinoigitare.criteria;

import java.util.HashSet;

/**
 * A composite {@link Criteria} using AND logic. This criteria is satisfied if
 * all <code>Criteria</code> that compose this composite <code>Criteria</code>
 * are satisfied.
 * 
 * @author nino.samac
 *
 * @param <T>
 *            the type for this Criteria
 * @see ORCriteria
 * 
 */
@SuppressWarnings("serial")
public class ANDCriteria<T> implements Criteria<T> {

	protected HashSet<Criteria<T>> criteriaSet = new HashSet<Criteria<T>>();

	public void add(Criteria<T> criteria) {
		criteriaSet.add(criteria);
	}

	public void remove(Criteria<T> criteria) {
		criteriaSet.remove(criteria);
	}

	public void clear() {
		criteriaSet.clear();
	}

	public boolean isEmpty() {
		return criteriaSet.isEmpty();
	}

	@Override
	public boolean isSatisfiedBy(T t) {
		if (t == null) {
			throw new NullPointerException("Argument can not be null");
		}

		if (criteriaSet.isEmpty()) {
			throw new IllegalStateException("Criteria is not configured");
		}

		for (Criteria<T> criteria : criteriaSet) {
			if (!criteria.isSatisfiedBy(t)) {
				return false;
			}
		}
		return true;
	}
}
