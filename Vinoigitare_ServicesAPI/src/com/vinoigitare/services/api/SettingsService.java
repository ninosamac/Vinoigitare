package com.vinoigitare.services.api;

import java.util.Properties;

/**
 * This class manages various settings needed through the Vinoigitare
 * application. Application specific properties should be accessed using this
 * service.
 * 
 * @author nino.samac
 *
 */
public interface SettingsService {

	/**
	 * 
	 * @param key
	 * @return value for given key
	 */
	String getValue(String key);

	/**
	 * 
	 * @param sectionName
	 * @return Properties with the given prefix
	 */
	Properties getSection(String sectionName);

	/**
	 * 
	 * @param sectionName
	 * @param noPrefix
	 * @return Properties with the given name, but shorthanded if noPrefix is
	 *         set to true.
	 */
	Properties getSection(String sectionName, boolean noPrefix);

}
