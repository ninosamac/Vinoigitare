package com.vinoigitare.criteria;

/**
 * Interface for classes that check objects of type <code>T</code> for
 * satisfying a given criteria.
 * 
 * @author nino.samac
 * 
 * @param <T>
 *            the type of object that need to be checked for satisfying this
 *            Criteria
 * 
 * @see ANDCriteria
 * @see ORCriteria
 * @see Filter
 * 
 */
public interface Criteria<T> {

	Criteria<Object> ALWAYS_SATISFIED = new Criteria<Object>() {

		@Override
		public boolean isSatisfiedBy(Object t) {
			return true;
		}

	};

	Criteria<Object> NEVER_SATISFIED = new Criteria<Object>() {

		@Override
		public boolean isSatisfiedBy(Object t) {
			return false;
		}

	};

	public boolean isSatisfiedBy(T t);

}
