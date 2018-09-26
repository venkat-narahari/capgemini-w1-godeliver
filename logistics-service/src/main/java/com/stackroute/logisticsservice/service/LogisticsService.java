package com.stackroute.logisticsservice.service;

import com.stackroute.logisticsservice.domain.DateLogistics;
import com.stackroute.logisticsservice.domain.Location;
import com.stackroute.logisticsservice.domain.Order;
import com.stackroute.logisticsservice.domain.Route;
import com.stackroute.logisticsservice.exception.MongoConnectionException;


public interface LogisticsService {

	/*
	 * Service method to get details for specific date
	 */
	public DateLogistics getDateDetails(Order order) throws MongoConnectionException;

	/*
	 * Service method to save details of order by slot
	 */
	public boolean saveOrderDetails(Route selectedSlot) throws MongoConnectionException;

	/*
	 * Service method to get details for specific date
	 */
	public DateLogistics getDateLogistics(String date) throws MongoConnectionException;

	/*
	 * Service method to remove details for specific date
	 */
	public boolean removeOrderDetails(Order order) throws MongoConnectionException;
	
	/*
	 * Service method to remove details for specific date
	 */
	public Location[] getOrderLocation(String date, String slotId, String vehicleId) throws MongoConnectionException;

	
}
