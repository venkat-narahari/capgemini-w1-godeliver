package com.stackroute.bookservice.exceptions;

public class MongoConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	String message;

	public MongoConnectionException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
