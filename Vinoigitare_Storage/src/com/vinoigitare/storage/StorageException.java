package com.vinoigitare.storage;

@SuppressWarnings("serial")
public class StorageException extends Exception {

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Exception e) {
		super(message, e);
	}

}
