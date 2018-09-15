package com.stackroute.cvrp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.cvrp.domain.DateLogistics;
import com.stackroute.cvrp.domain.Route;
import com.stackroute.cvrp.domain.Slot;
import com.stackroute.cvrp.domain.Vehicle;
import com.stackroute.cvrp.service.CvrpService;
import com.stackroute.cvrp.service.RoutingService;

@RestController
@RequestMapping("api/v1/cvrp")
public class CvrpController {

	private RoutingService routingService;
	private CvrpService cvrpService;
	private Route newRoute;
	private DateLogistics dl;
	private Slot[] sl;

	@Autowired
	public CvrpController(RoutingService routingService, CvrpService cvrpService) {
		this.routingService = routingService;
		this.cvrpService = cvrpService;
	}

	@RequestMapping(value = "/slots", method = RequestMethod.POST)
	public ResponseEntity<?> giveSlots(@RequestBody Route route) {

		// System.out.println("controller-->"+route);
		cvrpService.getRoute(route);
		System.out.println("");
		// cvrpService.getJson(route);
		dl = route.getDateLogistics();
		sl = dl.getSlots();
		// System.out.println(sl[1].toString());
		// System.out.println(sl[2].toString());
		Vehicle v[] = sl[0].getSlotVehicle();
		// System.out.println("vehicle capacity "+v[0].getVehicleCapacity());

		newRoute = routingService.getNewOrderedRoute(route);
		return new ResponseEntity<Route>(newRoute, HttpStatus.OK);
	}

}
