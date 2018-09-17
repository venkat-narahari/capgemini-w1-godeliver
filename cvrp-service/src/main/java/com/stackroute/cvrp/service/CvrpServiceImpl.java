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
public class CvrpServiceImpl implements CvrpService {

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

	public CvrpServiceImpl() {

	}

	// @Override
	public void getRoute(Route route) {
		this.list = route;
	}

	public CvrpServiceImpl(int orderNum, int vehNum, int vehCap) {
		this.noOfVehicles = vehNum;
		this.noOfOrders = orderNum;
		this.distance = 0;
		vehicles = new Vehicle[noOfVehicles];
		vehiclesForBestSolution = new Vehicle[noOfVehicles];
		pastSolutions = new ArrayList<>();
		for (int i = 0; i < noOfVehicles; i++) {
			vehicles[i] = new Vehicle(i + 1, vehCap);
			vehiclesForBestSolution[i] = new Vehicle(i + 1, vehCap);
		}
	}

	@Override
	public double[][] getDistanceMatrix(List<Location> locationList) {
		System.out.println("Json is disatnceMatrix is " + list);
		String url1 = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?";
		String origins = "origins=";
		String origin = "";
		int count = 0;
		String destinations = "destinations=";
		String destination = "";
		String url2 = "travelMode=driving&key=AhT3nVgSlv14w5u2GLYkCrCJm1VWDkBeEGHpG4JFNb13vgktN7OIJEr-5KZZrZah";
		String inline = "";
		List<Location> locations;
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

	public boolean UnassignedOrderExists(Order[] orders) {
		for (int i = 1; i < orders.length; i++) {
			if (!orders[i].isRouted())
				return true;
		}
		return false;
	}

	public void greedySolution(List<Order> order, double[][] distanceMatrix) {
		orders = order.toArray(new Order[order.size()]);

		double candCost, endCost;
		int vehicleIndex = 0;
		while (UnassignedOrderExists(orders)) {
			int orderIndex = 0;
			Order orderObj = null;
			double minCost = (float) Double.MAX_VALUE;
			if (vehicles[vehicleIndex].getVehicleRoute().length == 0) {

				this.vehicles[vehicleIndex].addOrder(orders[0]);
			}
			int i;
			for (i = 1; i < noOfOrders; i++) {
				if (orders[i].isRouted() == false) {
					if (vehicles[vehicleIndex].CheckIfFits(orders[i].getOrderVolume())) {
						candCost = distanceMatrix[Integer
								.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation())][i];
						if (minCost > candCost) {
							minCost = candCost;
							orderIndex = i;
							orderObj = orders[i];

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
		endCost = distanceMatrix[Integer.parseInt(vehicles[vehicleIndex].getVehicleCurrentLocation())][0];
		this.vehicles[vehicleIndex].addOrder(orders[0]);
		this.distance += endCost;
	}

	
	public void TabuSearch(int TABU_Horizon, double[][] distanceMatrix) {
		List<Order> routeFrom;
		List<Order> routeTo;

		int movingNodeDemand = 0;

		int vehIndexFrom, vehIndexTo;
		double bestNCost, neighbourCost;

		int swapIndexA = -1, swapIndexB = -1, swapRouteFrom = -1, swapRouteTo = -1;

		int max_interations = 200;
		int iteration_number = 0;

		int dimensionOrder = distanceMatrix[1].length;
		int tabu_matrix[][] = new int[dimensionOrder + 1][dimensionOrder + 1];

		bestSolutionCost = this.distance; // Initial Solution Cost

		boolean termination = false;

		while (!termination) {
			iteration_number++;
			bestNCost = Double.MAX_VALUE;
			for (vehIndexFrom = 0; vehIndexFrom < this.vehicles.length; vehIndexFrom++) {
				routeFrom = Arrays.asList(this.vehicles[vehIndexFrom].getVehicleRoute());
				if (routeFrom != null) {
					int RoutFromLength = routeFrom.size();
					for (int i = 1; i < RoutFromLength - 1; i++) { // Not possible to move depot!

						for (vehIndexTo = 0; vehIndexTo < this.vehicles.length; vehIndexTo++) {
							routeTo = Arrays.asList(this.vehicles[vehIndexTo].getVehicleRoute());
							int routeTolength = routeTo.size();
							for (int j = 0; (j < routeTolength - 1); j++) {// Not possible to move after last Depot!

								movingNodeDemand = Integer.parseInt(routeFrom.get(i).getOrderVolume());

								if ((vehIndexFrom == vehIndexTo)
										|| this.vehicles[vehIndexTo].CheckIfFits(String.valueOf(movingNodeDemand))) {
									// If we assign to a different route check capacity constrains
									// if in the new route is the same no need to check for capacity

									if (((vehIndexFrom == vehIndexTo) && ((j == i) || (j == i - 1))) == false) {
										double MinusCost1 = distanceMatrix[Integer
												.parseInt(routeFrom.get(i - 1).getOrderId())][Integer
														.parseInt(routeFrom.get(i).getOrderId())];
										double MinusCost2 = distanceMatrix[Integer
												.parseInt(routeFrom.get(i).getOrderId())][Integer
														.parseInt(routeFrom.get(i + 1).getOrderId())];
										double MinusCost3 = distanceMatrix[Integer
												.parseInt(routeTo.get(j).getOrderId())][Integer
														.parseInt(routeTo.get(j + 1).getOrderId())];
										double AddedCost1 = distanceMatrix[Integer
												.parseInt(routeFrom.get(i - 1).getOrderId())][Integer
														.parseInt(routeFrom.get(i + 1).getOrderId())];
										double AddedCost2 = distanceMatrix[Integer
												.parseInt(routeTo.get(j).getOrderId())][Integer
														.parseInt(routeFrom.get(i).getOrderId())];

										double AddedCost3 = distanceMatrix[Integer
												.parseInt(routeFrom.get(i).getOrderId())][Integer
														.parseInt(routeTo.get(j + 1).getOrderId())];

										// Check if the move is a Tabu! - If it is Tabu break
										if ((tabu_matrix[Integer.parseInt(routeFrom.get(i - 1).getOrderId())][Integer
												.parseInt(routeFrom.get(i + 1).getOrderId())] != 0)
												|| (tabu_matrix[Integer.parseInt(routeTo.get(j).getOrderId())][Integer
														.parseInt(routeFrom.get(i).getOrderId())] != 0)
												|| (tabu_matrix[Integer.parseInt(routeFrom.get(i).getOrderId())][Integer
														.parseInt(routeTo.get(j + 1).getOrderId())] != 0)) {
											break;
										}

										neighbourCost = AddedCost1 + AddedCost2 + AddedCost3 - MinusCost1 - MinusCost2
												- MinusCost3;

										if (neighbourCost < bestNCost) {
											bestNCost = neighbourCost;
											swapIndexA = i;
											swapIndexB = j;
											swapRouteFrom = vehIndexFrom;
											swapRouteTo = vehIndexTo;
										}
									}
								}
							}
						}
					}
				}
			}

			for (int o = 0; o < tabu_matrix[0].length; o++) {
				for (int p = 0; p < tabu_matrix[0].length; p++) {
					if (tabu_matrix[o][p] > 0) {
						tabu_matrix[o][p]--;
					}
				}
			}

			routeFrom = new ArrayList<>(Arrays.asList(this.vehicles[swapRouteFrom].getVehicleRoute()));
			routeTo = new ArrayList<Order>(Arrays.asList(this.vehicles[swapRouteTo].getVehicleRoute()));
			this.vehicles[swapRouteFrom].setVehicleRoute(null);
			this.vehicles[swapRouteTo].setVehicleRoute(null);

			Order SwapNode = routeFrom.get(swapIndexA);

			int NodeIDBefore = Integer.parseInt(routeFrom.get(swapIndexA - 1).getOrderId());
			int NodeIDAfter = Integer.parseInt(routeFrom.get(swapIndexA + 1).getOrderId());
			int NodeID_F = Integer.parseInt(routeTo.get(swapIndexB).getOrderId());
			int NodeID_G = Integer.parseInt(routeTo.get(swapIndexB + 1).getOrderId());

			Random TabuRan = new Random();
			int randomDelay1 = TabuRan.nextInt(5);
			int randomDelay2 = TabuRan.nextInt(5);
			int randomDelay3 = TabuRan.nextInt(5);

			tabu_matrix[NodeIDBefore][Integer.parseInt(SwapNode.getOrderId())] = TABU_Horizon + randomDelay1;
			tabu_matrix[Integer.parseInt(SwapNode.getOrderId())][NodeIDAfter] = TABU_Horizon + randomDelay2;
			tabu_matrix[NodeID_F][NodeID_G] = TABU_Horizon + randomDelay3;

			routeFrom.remove(swapIndexA);
			routeTo.remove(swapIndexB);

			if (swapRouteFrom == swapRouteTo) {
				if (swapIndexA < swapIndexB) {

					routeTo.add(swapIndexB, SwapNode);
				} else {
					routeTo.add(swapIndexB + 1, SwapNode);
				}
			} else {
				routeTo.add(swapIndexB + 1, SwapNode);
			}

			this.vehicles[swapRouteFrom].setVehicleRoute(routeFrom.toArray(new Order[routeFrom.size()]));

			this.vehicles[swapRouteFrom].setVehicleLoadedCapacity(Integer.toString(
					Integer.parseInt(this.vehicles[swapRouteFrom].getVehicleLoadedCapacity()) - movingNodeDemand));
			this.vehicles[swapRouteTo].setVehicleRoute(routeTo.toArray(new Order[routeTo.size()]));

			this.vehicles[swapRouteTo].setVehicleLoadedCapacity(Integer.toString(
					Integer.parseInt(this.vehicles[swapRouteTo].getVehicleLoadedCapacity()) - movingNodeDemand));

			pastSolutions.add(this.distance);

			this.distance += bestNCost;

			if (this.distance < bestSolutionCost) {
				System.out.println("heyyyyyyyyyyyy");
				saveBestSolution();
			}
			if (iteration_number == max_interations) {
				termination = true;
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

	public Vehicle[] updatedVehicles() {
		return this.vehiclesForBestSolution;
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

	public void SolutionPrint(String Solution_Label)// Print Solution In console
	{
		System.out.println("=========================================================");
		System.out.println(Solution_Label + "\n");

		for (int j = 0; j < this.noOfVehicles; j++) {
			if (this.vehicles[j].getVehicleRoute().length != 0) {
				System.out.print("Vehicle " + j + ":");
				int RoutSize = this.vehicles[j].getVehicleRoute().length;
				for (int k = 0; k < RoutSize; k++) {
					if (k == RoutSize - 1) {
						System.out.print(this.vehicles[j].getVehicleRoute()[k].getOrderId());
					} else {
						System.out.print(this.vehicles[j].getVehicleRoute()[k].getOrderId() + "->");
					}
				}
				System.out.println();
			}
		}
		System.out.println("\nSolution Cost " + this.distance + "\n");
	}

}
