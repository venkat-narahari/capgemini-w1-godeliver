package com.stackroute.logisticsservice.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Domain Object for Specfic location
 */
@Document
public class Location {
	
	private String orderLatitude;
	private String orderLongitude;
	
	public Location() {
		
	}
	
	public Location(String orderLatitude, String orderLongitude) {
		super();
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
		return "Location [orderLatitude=" + orderLatitude + ", orderLongitude=" + orderLongitude + "]";
	}
	
	
}
