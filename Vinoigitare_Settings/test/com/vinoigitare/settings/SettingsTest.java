package com.vinoigitare.settings;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SettingsTest {

	private static final Log log = LogFactory.getLog(SettingsTest.class);
	
  @Test(groups={"io"})
  public void testSettings() {
	  Settings settings = new Settings();
	  log.info("Found settings for Vinoigitare:");
	  Properties properties = settings.getSection("SONGS");
	  assertFalse(properties.isEmpty());
	  System.out.println(properties);
  }
}
