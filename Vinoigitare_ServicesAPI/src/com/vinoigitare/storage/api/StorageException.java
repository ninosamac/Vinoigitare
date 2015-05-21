package com.vinoigitare.storage.api;

@SuppressWarnings("serial")
public class StorageException extends Exception {

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Exception e) {
		super(message, e);
	}

}
