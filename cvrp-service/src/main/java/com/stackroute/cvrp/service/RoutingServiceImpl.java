package com.stackroute.cvrp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.cvrp.domain.DateLogistics;
import com.stackroute.cvrp.domain.Location;
import com.stackroute.cvrp.domain.Order;
import com.stackroute.cvrp.domain.Route;
import com.stackroute.cvrp.domain.Slot;
import com.stackroute.cvrp.domain.Vehicle;

@Service
public class RoutingServiceImpl implements RoutingService {

	private CvrpServiceImpl1 cvrpServiceImpl1;
	private Order newOrder;
	private DateLogistics dateLogistics;
	private Slot[] slots;
	private Vehicle[] vehicles;
	private Order[] orders;
	private List<Order> ordersList;
	private List<Location> locationList;
	private Location location;
	private Location newOrderLocation;
	private Location depoLocation = new Location("12.9353863", "77.6117461");
	private Order depoOrder=new Order(depoLocation);
	private Order[] orderArray;
	private double[][] distanceMatrix;
	private boolean checkIfFits;
	private int vehicleCap;
	private String slotid;

	@Autowired
	public RoutingServiceImpl(CvrpServiceImpl1 cvrpServiceImpl1) {
		this.cvrpServiceImpl1 = cvrpServiceImpl1;
	}

	public void convertToJson(Object obj) {

		ObjectMapper mapperObj = new ObjectMapper();
		try {
			// get Employee object as a json string
			String jsonStr = mapperObj.writeValueAsString(obj);
			System.out.println(jsonStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Route getNewOrderedRoute(Route route) {
		System.out.println("route is " + route);
		newOrder = route.getNewOrder();
		dateLogistics = route.getDateLogistics();
		slots = dateLogistics.getSlots();

		for (int i = 0; i < slots.length; i++) {
			ordersList = new ArrayList<>();
			locationList = new ArrayList<>();
//			locationList.add(depoLocation);
			ordersList.add(depoOrder);
			vehicles = slots[i].getSlotVehicle();
			// slotid=slots[i].getSlotId();
			for (int j = 0; j < vehicles.length; j++) {
				orders = vehicles[i].getVehicleRoute();
				vehicleCap = Integer.parseInt(vehicles[i].getVehicleCapacity());
				if (orders != null) {
					for (int k = 0; k < orders.length; k++) {
						ordersList.add(orders[k]);
					}
				}
			}

			// System.out.println("oroderList is " + ordersList);
			for (int l = 0; l < ordersList.size(); l++) {
				location = ordersList.get(l).getOrderLocation();
				locationList.add(location);
			}
			newOrderLocation = newOrder.getOrderLocation();
			ordersList.add(newOrder);
			System.out.println("ordersList in loop is" + ordersList.toString());
			locationList.add(newOrderLocation);
			System.out.println("locationList in loop is" + locationList.toString());
			distanceMatrix = cvrpServiceImpl1.getDistanceMatrix(locationList);
			// checkIfFits=cvrpServiceImpl1.checkIfFits(newOrder.getOrderVolume());
			for (int j = 1; j < ordersList.size(); j++) {
				ordersList.get(j).setRouted(false);
			}
			CvrpServiceImpl1 cvrp = new CvrpServiceImpl1(ordersList.size(), vehicles.length, vehicleCap);
			cvrp.getRoute(route);
			cvrp.greedySolution(ordersList, distanceMatrix);
			cvrp.greedySolution(ordersList, distanceMatrix);
			cvrp.TabuSearch(10, distanceMatrix);

		}
		for (int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix.length; j++)
				System.out.println("distance matrix is " + distanceMatrix[i][j]);
		}
		System.out.println("orderList fianl is " + ordersList);
		System.out.println("");
		System.out.println("LocationList " + locationList);
		convertToJson(ordersList);

		return null;
	}

}
