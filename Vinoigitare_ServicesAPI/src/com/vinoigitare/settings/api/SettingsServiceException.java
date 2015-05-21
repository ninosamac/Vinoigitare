package com.vinoigitare.settings.api;

@SuppressWarnings("serial")
public class SettingsServiceException extends Exception {

	public SettingsServiceException(String message) {
		super(message);
	}

	public SettingsServiceException(String message, Exception e) {
		super(message, e);
	}
}
