package com.stackroute.cvrp.service;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.stackroute.cvrp.domain.DateLogistics;
import com.stackroute.cvrp.domain.Location;
import com.stackroute.cvrp.domain.Order;
import com.stackroute.cvrp.domain.Route;
import com.stackroute.cvrp.domain.Slot;
import com.stackroute.cvrp.domain.Vehicle;
import com.stackroute.cvrp.exceptions.IllegalLocationMatrixException;

@Service
public class CvrpServiceImpl1 implements CvrpService {

	private Route list;
	private Slot[] slots;
	private Vehicle[] vehicles;
	private Vehicle[] vehiclesForBestSolution;
	private double bestSolutionCost;
	private ArrayList<Double> pastSolutions;
	private int noOfVehicles;
	private int noOfOrders;
	private double distance;
	private float filledSlotCapacity = 0;
	private float totalSlotCapacity = 0;
	private float newFilledCapacity;
	private float vehicleFilledCapacity;
	private float vehicleTotalCapacity;
	private DateLogistics dateLogs = new DateLogistics();
	private Slot[] slot;
	private Order[] orders;

	public CvrpServiceImpl1() {

	}

	// @Override
	public void getRoute(Route route) {
		this.list = route;
	}

	public CvrpServiceImpl1(int orderNum, int vehNum, int vehCap) {
		this.noOfVehicles = vehNum;
		this.noOfOrders = orderNum;
		this.distance = 0;
		vehicles = new Vehicle[noOfVehicles];
		vehiclesForBestSolution = new Vehicle[noOfVehicles];
		pastSolutions = new ArrayList<>();
		for (int i = 0; i < noOfVehicles; i++) {
			vehicles[i] = new Vehicle(i + 1, vehCap);
			// System.out.println("vehicles" + vehicles[i]);
			vehiclesForBestSolution[i] = new Vehicle(i + 1, vehCap);
		}
	}

	@Override
	public double[][] getDistanceMatrix(List<Location> locationList) {
		System.out.println("Json is disatnceMatrix is " + list);
		// ArrayList<Order>
		// routeList=list.getDateLogistics().getSlots()[0].getSlotVehicle()[0].getVehicleRouteList();
		// System.out.println("array is "+routeList);
		String url1 = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?";
		String origins = "origins=";
		String origin = "";
		int count = 0;
		String destinations = "destinations=";
		String destination = "";
		String url2 = "travelMode=driving&key=AhT3nVgSlv14w5u2GLYkCrCJm1VWDkBeEGHpG4JFNb13vgktN7OIJEr-5KZZrZah";
		String inline = "";
		List<Location> locations;
		// locations = getAllLocationsBySlot(slotId);
		double[][] distanceMatrix = new double[locationList.size()][locationList.size()];
		while (!(locationList.isEmpty())) {
			if (count < 1) {
				for (int i = 0; i < locationList.size(); i++) {
					for (int j = 0; j < 1; j++) {
						String str1 = locationList.get(i).getOrderLatitude();
						String str2 = locationList.get(i).getOrderLongitude();
						origins = origins + str1 + "," + str2 + ";";
						destinations = destinations + str1 + "," + str2 + ";";
					}
				}
				origin = origins.substring(0, origins.length() - 1);
				destination = destinations.substring(0, destinations.length() - 1);
				String url = url1 + origin + "&" + destination + "&" + url2;
				System.out.println("Url is " + url);

				try {
					count++;
					URL url3 = new URL(url);
					HttpURLConnection conn = (HttpURLConnection) url3.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					int responsecode = conn.getResponseCode();
					if (responsecode != 200)
						throw new IllegalLocationMatrixException("HttpResponseCode: " + responsecode);
					else {
						Scanner sc = new Scanner(url3.openStream());
						while (sc.hasNext()) {
							inline += sc.nextLine();
						}
						System.out.println("\nJSON Response in String format");
						System.out.println(inline);
						sc.close();
					}

					JSONParser parse = new JSONParser();
					JSONObject jobj = (JSONObject) parse.parse(inline);
					JSONArray jsonarr_1 = (JSONArray) jobj.get("resourceSets");
					JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(0);
					JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("resources");
					JSONObject jsonobj_3 = (JSONObject) jsonarr_2.get(0);
					JSONArray jsonarr_3 = (JSONArray) jsonobj_3.get("results");
					for (int j = 1; j < jsonarr_3.size(); j++) {
						JSONObject jsonobj_2 = (JSONObject) jsonarr_3.get(j);
						int str_data1 = ((Long) jsonobj_2.get("destinationIndex")).intValue();
						System.out.println(str_data1);
						int str_data2 = ((Long) jsonobj_2.get("originIndex")).intValue();
						System.out.println(str_data2);
						try {
							Double str_data4 = (Double) jsonobj_2.get("travelDistance");
							System.out.println(str_data4);
							if (str_data1 != str_data2) {
								distanceMatrix[str_data1][str_data2] = str_data4.doubleValue();
								distanceMatrix[str_data2][str_data1] = str_data4.doubleValue();
							} else {
								distanceMatrix[str_data1][str_data1] = 0;
							}
						} catch (Exception e) {

						}
						System.out.println("\n");
					}
					conn.disconnect();
					return distanceMatrix;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return distanceMatrix;
	}

	// public boolean checkIfFits(Vehicle vehicle, String demand) {
	// boolean result = false;
	// totalSlotCapacity += Float.parseFloat(vehicle.getVehicleCapacity());
	// if (vehicle.getVehicleLoadedCapacity() == null) {
	// filledSlotCapacity = 0;
	// vehicleFilledCapacity = 0;
	// } else {
	// filledSlotCapacity += Float.parseFloat(vehicle.getVehicleLoadedCapacity());
	// vehicleFilledCapacity = Float.parseFloat(vehicle.getVehicleLoadedCapacity());
	// }
	// vehicleTotalCapacity = Float.parseFloat(vehicle.getVehicleCapacity());
	//
	// newFilledCapacity = filledSlotCapacity + Float.parseFloat(demand);
	//
	// if (newFilledCapacity <= totalSlotCapacity) {
	// if (vehicleFilledCapacity + Float.parseFloat(demand) <= vehicleTotalCapacity)
	// {
	// result = true;
	// }
	// }
	//
	// return result;
	// }

	public boolean UnassignedOrderExists(Order[] orders) {
		for (int i = 1; i < orders.length; i++) {
			if (!orders[i].isRouted())
				return true;
		}
		return false;
	}

	public void greedySolution(List<Order> order, double[][] distanceMatrix) {
		orders = order.toArray(new Order[order.size()]);
		System.out.println("order in greedy" + orders[0]);
		System.out.println("order in greedy" + orders[1]);

		double candCost, endCost;
		int vehicleIndex = 0;
		while (UnassignedOrderExists(orders)) {
			System.out.println("orders array in greedy" + orders[0]);
			int orderIndex = 0;
			Order orderObj = null;
			double minCost = (float) Double.MAX_VALUE;
			if (vehicles[vehicleIndex].getVehicleRoute().length == 0) {
				System.out.println("in condition");
				this.vehicles[vehicleIndex].addOrder(orders[0]);
			}
			System.out.println("vehicles[vehicleIndex] ...>>>>>>>>>>" + vehicles[vehicleIndex]);
			System.out.println("vehicles[vehicleIndex]" + vehicles[vehicleIndex].getVehicleCurrentLocation());
			System.out.println("integer 11" + vehicles[vehicleIndex]);
			int i;
			for (i = 1; i < noOfOrders; i++) {
				if (orders[i].isRouted() == false) {
					if (vehicles[vehicleIndex].CheckIfFits(orders[i].getOrderVolume())) {

						System.out.println("integer " + vehicles[vehicleIndex]);

						candCost = distanceMatrix[Integer
								.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation())][i];
						System.out.println("candidate cost" + candCost);
						if (minCost > candCost) {
							minCost = candCost;
							orderIndex = i;
							orderObj = orders[i];
							System.out.println("minimum cost" + minCost);
						}
					}
				}
			}
			if (orderObj == null) {
				if (vehicleIndex + 1 < vehicles.length) {
					if (Integer.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation()) != 0) {
						endCost = distanceMatrix[Integer
								.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation())][0];
						vehicles[vehicleIndex].addOrder(orders[0]);
						this.distance += endCost;
					}
					vehicleIndex = vehicleIndex + 1;
				} else {
					System.exit(0);
				}

			} else {
				this.vehicles[vehicleIndex].addOrder(orderObj);
				orders[orderIndex].setRouted(true);
				this.distance += minCost;
			}

		}
		System.out.println("vehicles in greedy sol >>>>>>>>>>>>>>>>"+Integer.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation()));
		System.out.println("*****************"+Integer.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation()));
		endCost = distanceMatrix[Integer.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation())][0];
		this.vehicles[vehicleIndex].addOrder(orders[0]);
		//vehicles[vehicleIndex].setVehicleRoute(orders);
		this.distance += endCost;
		System.out.println("endcost in greedy" + endCost);
		System.out.println("vehicles[veh]" + vehicles[vehicleIndex]);
		System.out.println("distance in greedy solution" + distance);
	}

	//
	public void TabuSearch(int TABU_Horizon, double[][] distanceMatrix) {
		System.out.println("inside tabu search");
		List<Order> RouteFrom;
		List<Order> RouteTo;

		int movingNodeDemand = 0;

		int vehIndexFrom, VehIndexTo;
		double BestNCost, NeigthboorCost;

		int SwapIndexA = -1, SwapIndexB = -1, SwapRouteFrom = -1, SwapRouteTo = -1;

		int MAX_ITERATIONS = 200;
		int iteration_number = 0;

		int DimensionCustomer = distanceMatrix[1].length;
		int TABU_Matrix[][] = new int[DimensionCustomer + 1][DimensionCustomer + 1];

		bestSolutionCost = this.distance; // Initial Solution Cost
		// System.out.println("Best SOlution cost:"+BestSolutionCost);

		boolean Termination = false;

		while (!Termination) {
			iteration_number++;
			BestNCost = Double.MAX_VALUE;
			// System.out.println("Best cost"+BestNCost);
			for (vehIndexFrom = 0; vehIndexFrom < this.vehicles.length; vehIndexFrom++) {
				{
					RouteFrom = Arrays.asList(this.vehicles[vehIndexFrom].getVehicleRoute());

					int RoutFromLength = RouteFrom.size();
					 System.out.println("route from length"+RoutFromLength);
					for (int i = 1; i < RoutFromLength - 1; i++) { // Not possible to move depot!

						for (VehIndexTo = 0; VehIndexTo < this.vehicles.length; VehIndexTo++) {
							RouteTo = Arrays.asList(this.vehicles[VehIndexTo].getVehicleRoute());
							int RouteTolength = RouteTo.size();
							 System.out.println("hey"+RouteTolength);
							for (int j = 0; (j < RouteTolength - 1); j++) {// Not possible to move after last Depot!

								movingNodeDemand = Integer.parseInt(RouteFrom.get(i).getOrderVolume());

								if ((vehIndexFrom == VehIndexTo)
										|| this.vehicles[VehIndexTo].CheckIfFits(String.valueOf(movingNodeDemand))) {
									// If we assign to a different route check capacity constrains
									// if in the new route is the same no need to check for capacity

									if (((vehIndexFrom == VehIndexTo) && ((j == i) || (j == i - 1))) == false) // Not a
																												// move
																												// that
																												// Changes
																												// solution
																												// cost
									{ 
										 System.out.println("i*&^%^&*^&^&^%&$^&$&$$%&%");
										double MinusCost1 = distanceMatrix[Integer
												.parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
														.parseInt(RouteFrom.get(i).getOrderId())];
										// System.out.println("Minus Cost 1 "+i+" "+ MinusCost1);
										double MinusCost2 = distanceMatrix[Integer
												.parseInt(RouteFrom.get(i).getOrderId())][Integer
														.parseInt(RouteFrom.get(i + 1).getOrderId())];
										// System.out.println("Minus Cost 2 "+i+" "+ MinusCost2);
										double MinusCost3 = distanceMatrix[Integer
												.parseInt(RouteTo.get(j).getOrderId())][Integer
														.parseInt(RouteTo.get(j + 1).getOrderId())];
										// System.out.println("Minus Cost 3 "+i+" "+ MinusCost3);

										double AddedCost1 = distanceMatrix[Integer
												.parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
														.parseInt(RouteFrom.get(i + 1).getOrderId())];
										// System.out.println("Added Cost 1 "+i+" "+" "+j+ AddedCost1);
										double AddedCost2 = distanceMatrix[Integer
												.parseInt(RouteTo.get(j).getOrderId())][Integer
														.parseInt(RouteFrom.get(i).getOrderId())];

										// System.out.println("Added Cost 2 "+i+" "+" "+j+ AddedCost2);
										double AddedCost3 = distanceMatrix[Integer
												.parseInt(RouteFrom.get(i).getOrderId())][Integer
														.parseInt(RouteTo.get(j + 1).getOrderId())];
										// System.out.println("Added Cost 3 "+i+" "+" "+j+ AddedCost3);

										// Check if the move is a Tabu! - If it is Tabu break
										if ((TABU_Matrix[Integer.parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
												.parseInt(RouteFrom.get(i + 1).getOrderId())] != 0)
												|| (TABU_Matrix[Integer.parseInt(RouteTo.get(j).getOrderId())][Integer
														.parseInt(RouteFrom.get(i).getOrderId())] != 0)
												|| (TABU_Matrix[Integer.parseInt(RouteFrom.get(i).getOrderId())][Integer
														.parseInt(RouteTo.get(j + 1).getOrderId())] != 0)) {
//											System.out.println("ifififififif@@@@@@@@@");
											break;
										}

										NeigthboorCost = AddedCost1 + AddedCost2 + AddedCost3 - MinusCost1 - MinusCost2
												- MinusCost3;
										// System.out.println(NeigthboorCost);
//										System.out.println("inside if*&*&*&**"+NeigthboorCost);

										if (NeigthboorCost < BestNCost) {
											BestNCost = NeigthboorCost;
											SwapIndexA = i;
											SwapIndexB = j;
											SwapRouteFrom = vehIndexFrom;
											SwapRouteTo = VehIndexTo;
										}
									}
								}
							}
						}
					}
				}

				for (int o = 0; o < TABU_Matrix[0].length; o++) {
					for (int p = 0; p < TABU_Matrix[0].length; p++) {
						if (TABU_Matrix[o][p] > 0) {
							TABU_Matrix[o][p]--;
						}
					}
				}

				RouteFrom = Arrays.asList(this.vehicles[SwapRouteFrom].getVehicleRoute());
				RouteTo = Arrays.asList(this.vehicles[SwapRouteTo].getVehicleRoute());
				this.vehicles[SwapRouteFrom].setVehicleRoute(null);
				this.vehicles[SwapRouteTo].setVehicleRoute(null);

				Order SwapNode = RouteFrom.get(SwapIndexA);

				int NodeIDBefore = Integer.parseInt(RouteFrom.get(SwapIndexA - 1).getOrderId());
				int NodeIDAfter = Integer.parseInt(RouteFrom.get(SwapIndexA + 1).getOrderId());
				int NodeID_F = Integer.parseInt(RouteTo.get(SwapIndexB).getOrderId());
				int NodeID_G = Integer.parseInt(RouteTo.get(SwapIndexB + 1).getOrderId());

				Random TabuRan = new Random();
				int RendomDelay1 = TabuRan.nextInt(5);
				int RendomDelay2 = TabuRan.nextInt(5);
				int RendomDelay3 = TabuRan.nextInt(5);

				TABU_Matrix[NodeIDBefore][Integer.parseInt(SwapNode.getOrderId())] = TABU_Horizon + RendomDelay1;
				TABU_Matrix[Integer.parseInt(SwapNode.getOrderId())][NodeIDAfter] = TABU_Horizon + RendomDelay2;
				TABU_Matrix[NodeID_F][NodeID_G] = TABU_Horizon + RendomDelay3;

				RouteFrom.remove(SwapIndexA);

				if (SwapRouteFrom == SwapRouteTo) {
					if (SwapIndexA < SwapIndexB) {
						RouteTo.add(SwapIndexB, SwapNode);
					} else {
						RouteTo.add(SwapIndexB + 1, SwapNode);
					}
				} else {
					RouteTo.add(SwapIndexB + 1, SwapNode);
				}

				this.vehicles[SwapRouteFrom].setVehicleRoute(RouteFrom.toArray(new Order[RouteFrom.size()]));

				this.vehicles[SwapRouteFrom].setVehicleLoadedCapacity(Integer.toString(
						Integer.parseInt(this.vehicles[SwapRouteFrom].getVehicleLoadedCapacity()) - movingNodeDemand));
				this.vehicles[SwapRouteTo].setVehicleRoute(RouteTo.toArray(new Order[RouteTo.size()]));

				this.vehicles[SwapRouteTo].setVehicleLoadedCapacity(Integer.toString(
						Integer.parseInt(this.vehicles[SwapRouteTo].getVehicleLoadedCapacity()) - movingNodeDemand));

				pastSolutions.add(this.distance);

				this.distance += BestNCost;

				if (this.distance < bestSolutionCost) {
					saveBestSolution();
				}
				//
				if (iteration_number == MAX_ITERATIONS) {
					Termination = true;
				}
			}

			this.vehicles = vehiclesForBestSolution;
			this.distance = bestSolutionCost;
			System.out.println("this is vehicles" + this.vehicles);

			try {
				PrintWriter writer = new PrintWriter("PastSolutionsTabu.txt", "UTF-8");
				writer.println("Solutions" + "\t");
				for (int i = 0; i < pastSolutions.size(); i++) {
					writer.println(pastSolutions.get(i) + "\t");
				}
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	// We use 1-0 exchange move
	// List<Order> RouteFrom = new ArrayList<Order>();
	// List<Order> RouteTo = new ArrayList<Order>();
	//
	// String movingNodeDemand = null;
	//
	// int vehIndexFrom, VehIndexTo;
	// double BestNCost, NeigthboorCost;
	//
	// int SwapIndexA = -1, SwapIndexB = -1, SwapRouteFrom = -1, SwapRouteTo = -1;
	//
	// int MAX_ITERATIONS = 200;
	// int iteration_number = 0;
	//
	// int DimensionCustomer = distanceMatrix[1].length;
	// int TABU_Matrix[][] = new int[DimensionCustomer + 1][DimensionCustomer + 1];
	//
	// String slotId = null;
	// bestSolutionCost = this.distance; // Initial Solution Cost
	// // System.out.println("Best SOlution cost:"+BestSolutionCost);
	//
	// boolean Termination = false;
	//
	// while (!Termination) {
	// iteration_number++;
	// BestNCost = Double.MAX_VALUE;
	// // System.out.println("Best cost"+BestNCost);
	// for (vehIndexFrom = 0; vehIndexFrom < this.vehicles.length; vehIndexFrom++) {
	//
	// // for(int a=1;a<this.vehicles[vehIndexFrom].getVehicleRoute().length;a++) {
	// if (this.vehicles[vehIndexFrom].getVehicleRoute() != null) {
	// RouteFrom = Arrays.asList(this.vehicles[vehIndexFrom].getVehicleRoute());
	//
	// int RoutFromLength = RouteFrom.size();
	// System.out.println("RoutFromLength"+RoutFromLength);
	// for (int i = 1; i < (RoutFromLength - 1); i++) { // Not possible to move
	// depot!
	//
	// System.out.println("hi"+this.vehicles.length);
	// for (VehIndexTo = 0; VehIndexTo < this.vehicles.length; VehIndexTo++) {
	//
	// RouteTo = Arrays.asList(this.vehicles[vehIndexFrom].getVehicleRoute());
	// int RouteTolength = RouteTo.size();
	// // System.out.println("hey"+RouteTolength);
	// for (int j = 0; (j < RouteTolength - 1); j++) {// Not possible to move after
	// // last Depot!
	//
	// movingNodeDemand = RouteFrom.get(i).getOrderVolume();
	//
	// if ((vehIndexFrom == VehIndexTo)
	// || this.vehicles[vehIndexFrom].CheckIfFits(movingNodeDemand)) {
	// // If we assign to a different route check capacity constrains
	// // if in the new route is the same no need to check for capacity
	//
	// if (((vehIndexFrom == VehIndexTo) && ((j == i) || (j == i - 1))) == false) //
	// // Not a
	// // move
	// // that
	// // Changes
	// // solution
	// // cost
	// {
	// // System.out.println("i "+i);
	// // System.out.println("j "+j);
	// double MinusCost1 = distanceMatrix[Integer
	// .parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i).getOrderId())];
	// // System.out.println("Minus Cost 1 " + i + " " + MinusCost1);
	// double MinusCost2 = distanceMatrix[Integer
	// .parseInt(RouteFrom.get(i).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i + 1).getOrderId())];
	// // System.out.println("Minus Cost 2 " + i + " " + MinusCost2);
	// double MinusCost3 = distanceMatrix[Integer
	// .parseInt(RouteTo.get(j).getOrderId())][Integer
	// .parseInt(RouteTo.get(j + 1).getOrderId())];
	// // System.out.println("Minus Cost 3 " + i + " " + MinusCost3);
	//
	// double AddedCost1 = distanceMatrix[Integer
	// .parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i + 1).getOrderId())];
	// // System.out.println("Added Cost 1 " + i + " " + " " + j + AddedCost1);
	// double AddedCost2 = distanceMatrix[Integer
	// .parseInt(RouteTo.get(j).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i).getOrderId())];
	// // System.out.println("Added Cost 2 " + i + " " + " " + j + AddedCost2);
	// double AddedCost3 = distanceMatrix[Integer
	// .parseInt(RouteFrom.get(i).getOrderId())][Integer
	// .parseInt(RouteTo.get(j + 1).getOrderId())];
	// // System.out.println("Added Cost 3 " + i + " " + " " + j + AddedCost3);
	//
	// // Check if the move is a Tabu! - If it is Tabu break
	// if ((TABU_Matrix[Integer.parseInt(RouteFrom.get(i - 1).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i + 1).getOrderId())] != 0)
	// || (TABU_Matrix[Integer.parseInt(RouteTo.get(j).getOrderId())][Integer
	// .parseInt(RouteFrom.get(i).getOrderId())] != 0)
	// || (TABU_Matrix[Integer.parseInt(RouteFrom.get(i).getOrderId())][Integer
	// .parseInt(RouteTo.get(j + 1).getOrderId())] != 0)) {
	// break;
	// }
	//
	// NeigthboorCost = AddedCost1 + AddedCost2 + AddedCost3 - MinusCost1 -
	// MinusCost2
	// - MinusCost3;
	// System.out.println("NeigthboorCost" + NeigthboorCost);
	//
	// if (NeigthboorCost < BestNCost) {
	// BestNCost = NeigthboorCost;
	// SwapIndexA = i;
	// SwapIndexB = j;
	// SwapRouteFrom = vehIndexFrom;
	// SwapRouteTo = VehIndexTo;
	// }
	// }
	// }
	// }
	// System.out.println("route to length" + RouteTolength);
	//
	// }
	// //
	// }
	//
	// // }
	// // else {
	// // break;
	// // }
	//
	// }
	//
	// for (int o = 0; o < TABU_Matrix[0].length; o++) {
	// for (int p = 0; p < TABU_Matrix[0].length; p++) {
	// if (TABU_Matrix[o][p] > 0) {
	// TABU_Matrix[o][p]--;
	// }
	// }
	// }
	//
	// System.out.println("vehIndexFrom" + vehIndexFrom);
	// System.out.println("swaproute" + SwapRouteFrom);
	// RouteFrom = Arrays.asList(this.vehicles[SwapRouteFrom].getVehicleRoute());
	// RouteTo = (ArrayList<Order>)
	// Arrays.asList(this.vehicles[SwapRouteTo].getVehicleRoute());
	// this.vehicles[SwapRouteFrom].setVehicleRoute(null);
	// this.vehicles[SwapRouteTo].setVehicleRoute(null);
	//
	// Order SwapNode = RouteFrom.get(SwapIndexA);
	//
	// int NodeIDBefore = Integer.parseInt(RouteFrom.get(SwapIndexA -
	// 1).getOrderId());
	// int NodeIDAfter = Integer.parseInt(RouteFrom.get(SwapIndexA +
	// 1).getOrderId());
	// int NodeID_F = Integer.parseInt(RouteTo.get(SwapIndexB).getOrderId());
	// int NodeID_G = Integer.parseInt(RouteTo.get(SwapIndexB + 1).getOrderId());
	//
	// Random TabuRan = new Random();
	// int RendomDelay1 = TabuRan.nextInt(5);
	// int RendomDelay2 = TabuRan.nextInt(5);
	// int RendomDelay3 = TabuRan.nextInt(5);
	//
	// TABU_Matrix[NodeIDBefore][Integer.parseInt(SwapNode.getOrderId())] =
	// TABU_Horizon + RendomDelay1;
	// TABU_Matrix[Integer.parseInt(SwapNode.getOrderId())][NodeIDAfter] =
	// TABU_Horizon + RendomDelay2;
	// TABU_Matrix[NodeID_F][NodeID_G] = TABU_Horizon + RendomDelay3;
	//
	// RouteFrom.remove(SwapIndexA);
	//
	// if (SwapRouteFrom == SwapRouteTo) {
	// if (SwapIndexA < SwapIndexB) {
	// RouteTo.add(SwapIndexB, SwapNode);
	// } else {
	// RouteTo.add(SwapIndexB + 1, SwapNode);
	// }
	// } else {
	// RouteTo.add(SwapIndexB + 1, SwapNode);
	// }
	//
	// this.vehicles[SwapRouteFrom].setVehicleRoute(RouteFrom.toArray(new
	// Order[RouteFrom.size()]));
	//
	// this.vehicles[SwapRouteFrom].setVehicleLoadedCapacity(
	// Integer.toString(Integer.parseInt(this.vehicles[SwapRouteFrom].getVehicleLoadedCapacity())
	// - Integer.parseInt(movingNodeDemand)));
	// this.vehicles[SwapRouteTo].setVehicleRoute(RouteTo.toArray(new
	// Order[RouteTo.size()]));
	//
	// this.vehicles[SwapRouteTo].setVehicleLoadedCapacity(
	// Integer.toString(Integer.parseInt(this.vehicles[SwapRouteTo].getVehicleLoadedCapacity())
	// - Integer.parseInt(movingNodeDemand)));
	//
	// pastSolutions.add(this.distance);
	//
	// this.distance += BestNCost;
	//
	// if (this.distance < bestSolutionCost) {
	// saveBestSolution();
	// }
	//
	// if (iteration_number == MAX_ITERATIONS) {
	// Termination = true;
	// }
	// }
	//
	// this.vehicles = vehiclesForBestSolution;
	// this.distance = bestSolutionCost;
	// System.out.println("this is vehicles" + this.vehicles);
	//
	// try {
	// PrintWriter writer = new PrintWriter("PastSolutionsTabu.txt", "UTF-8");
	// writer.println("Solutions" + "\t");
	// for (int i = 0; i < pastSolutions.size(); i++) {
	// writer.println(pastSolutions.get(i) + "\t");
	// }
	// writer.close();
	// } catch (Exception e) {
	// }
	// }

	public Vehicle[] updatedVehicles() {
		return this.vehicles;
	}

	public double updatedDistance() {
		return this.distance;
	}

	public void saveBestSolution() {
		bestSolutionCost = distance;
		for (int j = 0; j < noOfVehicles; j++) {
			Arrays.asList(vehiclesForBestSolution[j].getVehicleRoute()).clear();
			if (!Arrays.asList(vehicles[j].getVehicleRoute()).isEmpty()) {
				int RoutSize = Arrays.asList(vehicles[j].getVehicleRoute()).size();
				for (int k = 0; k < RoutSize; k++) {
					Order orderObj = Arrays.asList(vehicles[j].getVehicleRoute()).get(k);
					Arrays.asList(vehiclesForBestSolution[j].getVehicleRoute()).add(orderObj);
				}
			}

		}

	}

}
