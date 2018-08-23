package com.stackroute.userprofile.exceptions;

/**
 * class for throwing exceptions when the user tries to register with the email id that already exists
 */
public class EmailAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	String message;

	public EmailAlreadyExistsException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
