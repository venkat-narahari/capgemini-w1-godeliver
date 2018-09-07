package com.stackroute.orderdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.orderdetails.domain.Orderdetails;
import com.stackroute.orderdetails.service.OrderdetailsService;

@RestController
@RequestMapping(value = "/api/v1/")
public class OrderdetailsController {

	OrderdetailsService orderdetailsService;

	@Autowired
	public OrderdetailsController(OrderdetailsService orderdetailsService) {
		this.orderdetailsService = orderdetailsService;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ResponseEntity<?> saveDetails(@RequestBody Orderdetails orderdetails) {
		Orderdetails savedetails = orderdetailsService.saveDetails(orderdetails);
		return new ResponseEntity<Orderdetails>(savedetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<?> getDetails() {
		List<Orderdetails> getdetails = orderdetailsService.getAll();
		return new ResponseEntity<List<Orderdetails>>(getdetails, HttpStatus.OK);
	}

}
