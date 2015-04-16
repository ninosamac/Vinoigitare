package com.vinoigitare.services;

@SuppressWarnings("serial")
public class DataServiceException extends Exception {

	public DataServiceException(String message) {
		super(message);
	}

	public DataServiceException(String message, Exception e) {
		super(message, e);
	}
}