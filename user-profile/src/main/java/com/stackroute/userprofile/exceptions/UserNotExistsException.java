package com.stackroute.userprofile.exceptions;

/**
 * class for throwing exceptions when the user tries to view with the profile which doesn't exists
 */
public class UserNotExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	String message;

	public UserNotExistsException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}