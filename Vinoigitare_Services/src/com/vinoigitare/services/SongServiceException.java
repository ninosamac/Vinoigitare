package com.vinoigitare.services;

@SuppressWarnings("serial")
public class SongServiceException extends Exception {

	public SongServiceException(String message) {
		super(message);
	}

	public SongServiceException(String message, Exception e) {
		super(message, e);
	}
}
