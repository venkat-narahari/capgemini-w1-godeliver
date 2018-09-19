package com.stackroute.logisticsservice.domain;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Domain Object for Specific vehicle
 */
@Document
public class Vehicle {
	private String vehicleId;
	private String vehicleCapacity;
	private String vehicleLoadedCapacity;
	private String vehicleRouteDuration;
	private String vehicleRouteDurationLimit;
	private String vehicleCurrentLocation;
	private Order[] vehicleRoute;
	
	
	public Vehicle() {
		
	}
	
	public Vehicle(String vehicleId, String vehicleCapacity, String vehicleLoadedCapacity, String vehicleRouteDuration,
			String vehicleRouteDurationLimit, String vehicleCurrentLocation, Order[] vehicleRoute) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleCapacity = vehicleCapacity;
		this.vehicleLoadedCapacity = vehicleLoadedCapacity;
		this.vehicleRouteDuration = vehicleRouteDuration;
		this.vehicleRouteDurationLimit = vehicleRouteDurationLimit;
		this.vehicleRoute = vehicleRoute;
		this.vehicleCurrentLocation=vehicleCurrentLocation;
	}
	public String getVehicleCurrentLocation() {
		return vehicleCurrentLocation;
	}

	public void setVehicleCurrentLocation(String vehicleCurrentLocation) {
		this.vehicleCurrentLocation = vehicleCurrentLocation;
	}

	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleCapacity() {
		return vehicleCapacity;
	}
	public void setVehicleCapacity(String vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}
	public String getVehicleLoadedCapacity() {
		return vehicleLoadedCapacity;
	}
	public void setVehicleLoadedCapacity(String vehicleLoadedCapacity) {
		this.vehicleLoadedCapacity = vehicleLoadedCapacity;
	}
	public String getVehicleRouteDuration() {
		return vehicleRouteDuration;
	}
	public void setVehicleRouteDuration(String vehicleRouteDuration) {
		this.vehicleRouteDuration = vehicleRouteDuration;
	}
	public String getVehicleRouteDurationLimit() {
		return vehicleRouteDurationLimit;
	}
	public void setVehicleRouteDurationLimit(String vehicleRouteDurationLimit) {
		this.vehicleRouteDurationLimit = vehicleRouteDurationLimit;
	}
	public Order[] getVehicleRoute() {
		return vehicleRoute;
	}
	public void setVehicleRoute(Order[] vehicleRoute) {
		this.vehicleRoute = vehicleRoute;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleCapacity=" + vehicleCapacity + ", vehicleLoadedCapacity="
				+ vehicleLoadedCapacity + ", vehicleRouteDuration=" + vehicleRouteDuration
				+ ", vehicleRouteDurationLimit=" + vehicleRouteDurationLimit + ", vehicleCurrentLocation="
				+ vehicleCurrentLocation + ", vehicleRoute=" + Arrays.toString(vehicleRoute) + "]";
	}
	
	
	
}
