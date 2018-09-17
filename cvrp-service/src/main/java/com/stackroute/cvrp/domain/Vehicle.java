package com.stackroute.cvrp.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vehicle {

	private String vehicleId;
	private String vehicleCapacity;
	private String vehicleLoadedCapacity;
	private String vehicleRouteDuration;
	private String vehicleRouteDurationLimit;
	private String vehicleCurrentLocation;
	private Order[] vehicleRoute;
	// private ArrayList<Order> vehicleRouteList;

	public Vehicle() {

	}

	public Vehicle(String vehicleId, String vehicleCapacity, String vehicleLoadedCapacity, String vehicleRouteDuration,
			String vehicleRouteDurationLimit, String vehicleCurrentLocation, Order[] vehicleRoute) {
		this.vehicleId = vehicleId;
		this.vehicleCapacity = vehicleCapacity;
		this.vehicleLoadedCapacity = vehicleLoadedCapacity;
		this.vehicleRouteDuration = vehicleRouteDuration;
		this.vehicleRouteDurationLimit = vehicleRouteDurationLimit;
		this.vehicleCurrentLocation = vehicleCurrentLocation;
		this.vehicleRoute = vehicleRoute;
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

	// List<Order> vehicleRouteList=Arrays.asList(this.vehicleRoute);
	public void addOrder(Order order)// Add Customer to Vehicle Route

	{
		List<Order> vehicleRoute;
		if(this.getVehicleRoute().length!=0)
		{vehicleRoute=new ArrayList<>(Arrays.asList(this.getVehicleRoute()));
		vehicleRoute.add(order);
		this.setVehicleRoute(vehicleRoute.toArray(new Order[vehicleRoute.size()]));}
		else {
			vehicleRoute=new ArrayList<>();
			vehicleRoute.add(order);
			this.setVehicleRoute(vehicleRoute.toArray(new Order[vehicleRoute.size()]));
		}
		// System.out.println("order in add order+++++"+order.toString());
		// vehicleRouteList = new ArrayList<>();
		// List<Order> vehicleRoute = new ArrayList<>();
		// Order orderObj;
		// //List<Order> vehicleRouteList = Arrays.asList(this.getVehicleRoute());
		//
		// System.out.println("Order: "+order);
		// vehicleRouteList.add(order);
		// if (this.getVehicleRoute() != null) {
		// vehicleRouteList = Arrays.asList(this.getVehicleRoute());
		// for (int i = 0; i < vehicleRouteList.size(); i++) {
		// orderObj = vehicleRouteList.get(i);
		// vehicleRoute.add(order);
		// }

		// int i=this.vehicleRoute.length-1;
		// this.vehicleRoute[i]=order;
		// //this.vehicleLoadedCapacity += order.getOrderVolume();
		int vehicleLoadedCapacity = Integer.parseInt(this.vehicleLoadedCapacity);
		vehicleLoadedCapacity += Integer.parseInt(order.getOrderVolume());
		// System.out.println("vehicle ++++"+vehicleLoadedCapacity);
		this.vehicleLoadedCapacity = Integer.toString(vehicleLoadedCapacity);
		this.vehicleCurrentLocation = order.getOrderId();

	}

	public Vehicle(int id, int cap) {
		this.vehicleId = String.valueOf(id);
		this.vehicleCapacity = String.valueOf(cap);
		this.vehicleLoadedCapacity = String.valueOf(0);
		this.vehicleCurrentLocation = String.valueOf(0);
		this.vehicleRoute = new Order[] {};
		// this.vehicleRoute = new vehicleRoute[];

	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleCapacity=" + vehicleCapacity + ", vehicleLoadedCapacity="
				+ vehicleLoadedCapacity + ", vehicleRouteDuration=" + vehicleRouteDuration
				+ ", vehicleRouteDurationLimit=" + vehicleRouteDurationLimit + ", vehicleCurrentLocation="
				+ vehicleCurrentLocation + ", vehicleRoute=" + Arrays.toString(vehicleRoute) + "]";
	}

	public boolean CheckIfFits(String demand) {
		int dem = Integer.parseInt(demand);
		int vehLoad = Integer.parseInt(vehicleLoadedCapacity);
		int vehCap = Integer.parseInt(vehicleCapacity);
		return ((vehLoad + dem <= vehCap));
	}

}
