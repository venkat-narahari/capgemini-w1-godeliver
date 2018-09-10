package com.stackroute.logisticsservice.exception;

public class DateNotAvailableException extends Exception{
	
	private static final long serialVersionUID = 1L;

	String message;

	public DateNotAvailableException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
