package com.stackroute.cvrp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.ChangedCharSetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.cvrp.domain.DateLogistics;
import com.stackroute.cvrp.domain.Location;
import com.stackroute.cvrp.domain.Order;
import com.stackroute.cvrp.domain.Route;
import com.stackroute.cvrp.domain.Slot;
import com.stackroute.cvrp.domain.Slots;
import com.stackroute.cvrp.domain.Vehicle;

@Service
public class RoutingServiceImpl implements RoutingService {

	private CvrpServiceImpl cvrpServiceImpl1;
	private Route routeObj;
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
	private Order depoOrder = new Order(depoLocation);
	private Order[] orderArray;
	private double[][] distanceMatrix;
	private double distance;
	private boolean checkIfFits;
	private int vehicleCap;
	private String slotid;
	private Vehicle[] updatedVehicles;

	@Autowired
	public RoutingServiceImpl(CvrpServiceImpl cvrpServiceImpl1) {
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
		this.routeObj = route;
		System.out.println("route is " + route);
		newOrder = route.getNewOrder();
		dateLogistics = route.getDateLogistics();
		slots = dateLogistics.getSlots();
		boolean[] slotAvailable = new boolean[slots.length];

		for (int i = 0; i < slots.length; i++) {
			ordersList = new ArrayList<>();
			locationList = new ArrayList<>();
			// locationList.add(depoLocation);
			if (checkSlotAvailability(slots[i].getSlotId(), route)) {
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
				CvrpServiceImpl cvrp = new CvrpServiceImpl(ordersList.size(), vehicles.length, vehicleCap);
				cvrp.getRoute(route);
				cvrp.greedySolution(ordersList, distanceMatrix);
				cvrp.greedySolution(ordersList, distanceMatrix);

				updatedVehicles = cvrp.SolutionPrint("Solution after greedy solution");

				for (int e = 0; e < updatedVehicles.length; e++) {
					// for(int s=0;s<updatedVehicles[e].getVehicleRoute().length;s++) {
					// updatedVehicles[e].getVehicleRoute()[s].getAvailableSlots().setSlotAvailability(slotAvailability);;
					// }

				}

				// for(int k=0;k<vehicles.length;k++) {
				// System.out.println("vehicles of routing
				// service"+vehicles[i].getVehicleRoute().length);
				// }
				distance = cvrp.TabuSearch(10, distanceMatrix);
				cvrp.SolutionPrint("Solution after tabu");
				System.out.println("distance in routing" + distance);
				// newOrder.setAvailableSlots(availableSlots);
				System.out.println("route is " + route);
				slotAvailable[i] = true;

			} else {
				slotAvailable[i] = false;
			}
		}

		route.getNewOrder().getAvailableSlots().setSlotAvailability(slotAvailable);

		// for (int i = 0; i < distanceMatrix.length; i++) {
		// for (int j = 0; j < distanceMatrix.length; j++)
		// System.out.println("distance matrix is " + distanceMatrix[i][j]);
		// }
		// System.out.println("orderList fianl is " + ordersList);
		// System.out.println("");
		// System.out.println("LocationList " + locationList);
		// convertToJson(ordersList);

		return null;
	}

	public boolean checkSlotAvailability(String slotId, Route route) {
		// Route route;
		System.out.println("route in check method"+route);
		boolean res = false;
		String dem = route.getNewOrder().getOrderVolume();
		for (int i = 0; i < route.getDateLogistics().getSlots().length; i++) {
			// for(int j=0;j<)
			if (route.getDateLogistics().getSlots()[i].getSlotId().matches(slotId)) {
				for (int j = 0; j < route.getDateLogistics().getSlots()[i].getSlotVehicle().length; j++) {
					if (route.getDateLogistics().getSlots()[i].getSlotVehicle()[j].CheckIfFits(dem)) {
						res = true;
						break;
					}
				}
			}
		}
		return res;
	}

}
