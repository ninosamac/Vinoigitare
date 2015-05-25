package com.vinoigitare.criteria;

import java.io.Serializable;

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
public interface Criteria<T> extends Serializable {

	
	@SuppressWarnings({ "serial", "rawtypes" })
	public static final Criteria ALWAYS_SATISFIED = new Criteria() {

		@Override
		public boolean isSatisfiedBy(Object t) {
			return true;
		}

	};

	@SuppressWarnings({ "serial", "rawtypes" })
	public static final Criteria NEVER_SATISFIED = new Criteria() {

		@Override
		public boolean isSatisfiedBy(Object t) {
			return false;
		}

	};

	public boolean isSatisfiedBy(T t);

}
