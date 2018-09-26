package com.stackroute.userlogin.exceptions;

public class UserNameOrPasswordEmptyException extends Exception{
	private static final long serialVersionUID = 1L;

	String message;

	public UserNameOrPasswordEmptyException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
