package com.stackroute.orderdetails.exceptions;

public class DetailsAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	String message;

	public DetailsAlreadyExistsException(String message) {

		this.message = message;
	}

	public String toString() {
		return message;
	}

}
