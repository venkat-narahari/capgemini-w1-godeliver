package com.stackroute.cvrp.service;

import java.util.List;

import com.stackroute.cvrp.domain.Location;
import com.stackroute.cvrp.domain.Route;
import com.stackroute.cvrp.exceptions.IllegalLocationMatrixException;

public interface CvrpService {

	public void getRoute(Route route);
	//
	// public void setJson(Route route);

	// public List<Location> getAllLocationsBySlot(String slotId);

	public double[][] getDistanceMatrix(List<Location> locationList) throws IllegalLocationMatrixException;
	// public Route getOrderedRoute(Route route);

}
