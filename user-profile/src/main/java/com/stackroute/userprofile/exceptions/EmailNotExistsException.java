package com.stackroute.userprofile.exceptions;

public class EmailNotExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	String message;
	
	public EmailNotExistsException(String message) {
		this.message=message;
	}
	
	public String toString() {
		return message;
	}
}
