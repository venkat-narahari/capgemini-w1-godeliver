package com.stackroute.userlogin.exceptions;

public class PasswordNotMatchException extends Exception {

	private static final long serialVersionUID = 1L;

	String message;

	public PasswordNotMatchException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
