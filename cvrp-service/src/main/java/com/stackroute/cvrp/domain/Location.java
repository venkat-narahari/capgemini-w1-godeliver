package com.stackroute.cvrp.domain;

public class Location {
	private String orderLatitude;
	private String orderLongitude;

	public Location() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public Location(String orderLatitude, String orderLongitude) {
		this.orderLatitude = orderLatitude;
		this.orderLongitude = orderLongitude;
	}

	public String getOrderLatitude() {
		return orderLatitude;
	}

	public void setOrderLatitude(String orderLatitude) {
		this.orderLatitude = orderLatitude;
	}

	public String getOrderLongitude() {
		return orderLongitude;
	}

	public void setOrderLongitude(String orderLongitude) {
		this.orderLongitude = orderLongitude;
	}

	@Override
	public String toString() {
		return orderLatitude + "," + orderLongitude;
	}

}
