package com.vinoigitare.settings;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SettingsTest {

	private static final Log log = LogFactory.getLog(SettingsTest.class);

	@Test(groups = { "io" })
	public void testSettings() {
		Settings settings = Settings.getInstance();
		log.info("Found settings for Vinoigitare:");
		Properties properties = settings.getValues("SONGS");
		assertFalse(properties.isEmpty());
		System.out.println(properties);
	}

	@Test(groups = { "fast" })
	public void testSystem() {
//		Map<String, String> environment = System.getenv();
//		System.out.println(environment);
//		
//		Properties systemProperties = System.getProperties();
//		System.out.println(systemProperties);
		
//		String vinoigitareHome = System.getenv("VINOIGITARE_HOME");
//		System.out.println(vinoigitareHome);
	}
}
