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

	private CvrpServiceImpl cvrpServiceImpl;
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
	private double finalDistance;
	private boolean checkIfFits;
	private int vehicleCap;
	private String slotid;
	private Vehicle[] greedyUpdatedVehicles;
	private Vehicle[] tabuUpdatedVehicles;
	private int slotCapacity;
	private double greedyUpdateDistance;
	private double tabuUpdateDistance;
	private Slots slotsAvailabilty = new Slots();

	@Autowired
	public RoutingServiceImpl(CvrpServiceImpl cvrpServiceImpl1) {
		this.cvrpServiceImpl = cvrpServiceImpl1;
	}

	public void convertToJson(Object obj) {

		ObjectMapper mapperObj = new ObjectMapper();
		try {
			// get Employee object as a json string
			String jsonStr = mapperObj.writeValueAsString(obj);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Route getNewOrderedRoute(Route route) {
		this.routeObj = route;
		newOrder = route.getNewOrder();
		dateLogistics = route.getDateLogistics();
		slots = dateLogistics.getSlots();
		String[] slotCost = new String[slots.length];
		boolean[] slotAvailable = new boolean[slots.length];
		

		for (int i = 0; i < slots.length; i++) {
			ordersList = new ArrayList<>();
			locationList = new ArrayList<>();
			if (checkSlotAvailability(slots[i].getSlotId(), route)) {
				slotAvailable[i] = true;
				ordersList.add(depoOrder);
				vehicles = slots[i].getSlotVehicle();
				for (int j = 0; j < vehicles.length; j++) {
					orders = vehicles[i].getVehicleRoute();
					vehicleCap = Integer.parseInt(vehicles[i].getVehicleCapacity());
					if (orders != null) {
						if (orders.length != 0) {
							for (int k = 0; k < orders.length; k++) {
								ordersList.add(orders[k]);
								
							}
						}
					}
				}

				for (int l = 0; l < ordersList.size(); l++) {
					location = ordersList.get(l).getOrderLocation();
					locationList.add(location);
				}
				newOrderLocation = newOrder.getOrderLocation();
				ordersList.add(newOrder);
				System.out.println("orderlist is "+ordersList.toString());
				String[] orderId = new String[ordersList.size()];
				for(int a=0;a<ordersList.size();a++) {
					System.out.println("slot is "+ordersList.get(a).getOrderId());
					//orderId=new String[orders.length];
					orderId[a]=ordersList.get(a).getOrderId();
					ordersList.get(a).setOrderId(String.valueOf(a));
				}
				for(int b=0;b<orderId.length;b++) {
					System.out.println("order is "+orderId[b]);
				}
				locationList.add(newOrderLocation);
				distanceMatrix = cvrpServiceImpl.getDistanceMatrix(locationList);
				for (int j = 1; j < ordersList.size(); j++) {
					ordersList.get(j).setRouted(false);
				}
				CvrpServiceImpl cvrp = new CvrpServiceImpl(ordersList.size(), vehicles.length, vehicleCap);
				cvrp.getRoute(route);
				cvrp.greedySolution(ordersList, distanceMatrix);
				cvrp.greedySolution(ordersList, distanceMatrix);

				greedyUpdateDistance = cvrp.updatedDistance();
				greedyUpdatedVehicles = cvrp.solutionPrint("Solution after greedy solution");
				cvrp.tabuSearch(10, distanceMatrix);
				tabuUpdatedVehicles = cvrp.solutionPrint("Solution after tabu search");
				tabuUpdateDistance = cvrp.updatedDistance();
				for(int c=0;c<greedyUpdatedVehicles.length;c++) {
					for(int d=0;d<greedyUpdatedVehicles[c].getVehicleRoute().length;d++) {
						if(greedyUpdatedVehicles[c].getVehicleRoute()[d].getOrderId().equals(String.valueOf(d+1)))
							greedyUpdatedVehicles[c].getVehicleRoute()[d].setOrderId(orderId[d+1]);
					}
				}

				
				for(int c=0;c<tabuUpdatedVehicles.length;c++) {
					for(int d=0;d<tabuUpdatedVehicles[c].getVehicleRoute().length;d++) {
						if(tabuUpdatedVehicles[c].getVehicleRoute()[d].getOrderId().equals(String.valueOf(d+1)))
							tabuUpdatedVehicles[c].getVehicleRoute()[d].setOrderId(orderId[d+1]);
					}
				}

			} else {
				slotAvailable[i] = false;
			}
			if (greedyUpdateDistance != tabuUpdateDistance) {
				slotCost[i] = Double.toString(tabuUpdateDistance);
				slots[i].setSlotVehicle(tabuUpdatedVehicles);
			} else {

				slotCost[i] = Double.toString(greedyUpdateDistance);

				slots[i].setSlotVehicle(greedyUpdatedVehicles);
			}

		}

		this.slotsAvailabilty.setSlotAvailability(slotAvailable);

		this.slotsAvailabilty.setSlotCost(slotCost);

		route.getNewOrder().setAvailableSlots(slotsAvailabilty);
		route.getDateLogistics().setSlots(slots);

		return route;

	}

	public boolean checkSlotAvailability(String slotId, Route route) {

		boolean res = false;
		String dem = route.getNewOrder().getOrderVolume();
		for (int i = 0; i < route.getDateLogistics().getSlots().length; i++) {

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
