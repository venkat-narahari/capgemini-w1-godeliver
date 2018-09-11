package com.stackroute.orderdetails.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Location {
	
	private String orderLatitude;
	private String orderLongitude;
	
	public Location(String orderLatitude, String orderLongitude) {
		
		this.orderLatitude = orderLatitude;
		this.orderLongitude = orderLongitude;
	}
	public Location() {
	}
	@Override
	public String toString() {
		return "Location [orderLatitude=" + orderLatitude + ", orderLongitude=" + orderLongitude + "]";
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
	
	

}
