package com.stackroute.userprofile.exceptions;

public class EmailAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	String message;
	
	public EmailAlreadyExistsException(String message) {
		this.message=message;
	}
	
	public String toString() {
		return message;
	}
	
}
