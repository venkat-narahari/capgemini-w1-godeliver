package com.stackroute.logisticsservice.exception;

public class SlotsNotAvailableException extends Exception{
	
	private static final long serialVersionUID = 1L;

	String message;

	public SlotsNotAvailableException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
