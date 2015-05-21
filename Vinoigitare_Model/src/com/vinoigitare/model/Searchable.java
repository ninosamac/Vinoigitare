package com.vinoigitare.model;

/**
 * Classes implementing this interface may be searched for a certain String of
 * characters. Fields that need to be searched must be included in the search
 * String returned by #asText() function.
 * 
 */
public interface Searchable {

	/**
	 * String representation of all the searchable fields in this class. Field
	 * representations should be lowercase and stripped of any special
	 * characters. Also, language specific characters such as [èæžšð] should be
	 * replaced by [cczsd].
	 * 
	 * @return searchable text of this object
	 */
	String getSearchableText();
}
