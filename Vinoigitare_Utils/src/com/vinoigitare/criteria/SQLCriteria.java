package com.vinoigitare.criteria;

/**
 * {@link Criteria} classes that pull data from the database should use this
 * interface.
 * 
 * @author nino.samac
 * 
 * @see Criteria
 * 
 */
public interface SQLCriteria<T> extends Criteria<T> {
	/**
	 * Returns the String used in where clause of a standard JDBC SQL query.
	 * <p>
	 * i.e if criteria is that a given id should be less than 100, than the
	 * returned SQL string should be "id < 100" and would be used as a part of a
	 * SQL select statement in the form of
	 * <p>
	 * SELECT x from T WHERE <b>id < 100</b>
	 * 
	 * @return the String used in where clause of a standard JDBC SQL query.
	 */
	String getSQLWhereClause();
}
