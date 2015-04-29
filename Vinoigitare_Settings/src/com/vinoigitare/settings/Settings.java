package com.vinoigitare.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vinoigitare.services.api.SettingsService;
import com.vinoigitare.services.api.SettingsServiceException;

/**
 * Implementation of {@link SettingsService}. Provides properties from
 * properties file in the root directory of Vinoigitare. Uses system property
 * %VINOIGITARE_HOME% to find this file.
 * 
 * @author nino.samac
 *
 */
public class Settings implements SettingsService {

	private static final Log log = LogFactory.getLog(Settings.class);

	private File settingsFile;

	private Properties properties;

	private static Settings INSTANCE;

	public static Settings getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Settings();
		}
		return INSTANCE;
	}

	private Settings() {
		String vinoigitareFolder = System.getenv("VINOIGITARE_HOME");
		try {
			if (vinoigitareFolder == null || vinoigitareFolder.trim().isEmpty()) {
				log.error("System property VINOIGITARE_HOME has not been set.");
				throw new SettingsServiceException(
						"System property VINOIGITARE_HOME has not been set.");
			}

			settingsFile = new File(vinoigitareFolder + "/"
					+ "vinoigitare.properties");
			properties = new Properties();

			properties.load(new FileInputStream(settingsFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SettingsServiceException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getValues(String prefix) {
		return getValues(prefix, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getValues(String prefix, boolean noPrefix) {
		Properties result = new Properties();
		Set<String> allKeys = properties.stringPropertyNames();
		for (String key : allKeys) {
			if (key.startsWith(prefix)) {
				String key2 = key;
				if (noPrefix) {
					key2 = key.substring(prefix.length() + 1);
				}
				result.put(key2, properties.get(key));
			}
		}
		return result;
	}
}
