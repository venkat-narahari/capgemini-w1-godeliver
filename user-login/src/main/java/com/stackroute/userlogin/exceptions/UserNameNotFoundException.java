package com.stackroute.userlogin.exceptions;

public class UserNameNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	String message;

	public UserNameNotFoundException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}


}
