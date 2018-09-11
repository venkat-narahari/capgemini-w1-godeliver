package com.stackroute.orderdetails.exceptions;

public class NoDetailsFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	String message;

	public NoDetailsFoundException(String message) {

		this.message = message;
	}

	public String toString() {
		return message;
	}

}
