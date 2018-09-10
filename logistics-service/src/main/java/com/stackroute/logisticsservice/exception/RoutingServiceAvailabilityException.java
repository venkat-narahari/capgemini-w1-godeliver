package com.stackroute.logisticsservice.exception;

public class RoutingServiceAvailabilityException extends Exception{

	private static final long serialVersionUID = 1L;

	String message;

	public RoutingServiceAvailabilityException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}
}
