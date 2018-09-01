package com.stackroute.userlogin.exceptions;

public class UserNameOrPasswordEmpty extends Exception{
	private static final long serialVersionUID = 1L;

	String message;

	public UserNameOrPasswordEmpty(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
