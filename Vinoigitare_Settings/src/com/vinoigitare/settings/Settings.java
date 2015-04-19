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
 * Implementation of {@link SettingsService}. Provides settings from settings
 * file in the root directory of Vinoigitare. Uses system property
 * %VINOIGITARE_HOME% to find this file.
 * 
 * @author nino.samac
 *
 */
public class Settings implements SettingsService {

	private static final Log log = LogFactory.getLog(Settings.class);

	private File settingsFile;

	private Properties settings;

	public Settings() {
		String vinoigitareFolder = System.getenv("VINOIGITARE_HOME");
	//	String vinoigitareFolder = Constants.VINOIGITARE_HOME;
		try {
			if (vinoigitareFolder == null || vinoigitareFolder.trim().isEmpty()) {
				log.error("System property VINOIGITARE_HOME has not been set.");
				throw new SettingsServiceException(
						"System property VINOIGITARE_HOME has not been set.");
			}

			settingsFile = new File(vinoigitareFolder+"/"+"vinoigitare.properties");
			settings = new Properties();

			settings.load(new FileInputStream(settingsFile));
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
		return settings.getProperty(key);
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
		Set<String> allKeys = settings.stringPropertyNames();
		for (String key : allKeys) {
			if (key.startsWith(prefix)) {
				String key2 = key;
				if (noPrefix) {
					key2 = key.substring(prefix.length() + 1);
				}
				result.put(key2, settings.get(key));
			}
		}
		return result;
	}
}
