package com.stackroute.orderdetails.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stackroute.orderdetails.domain.Orderdetails;
import com.stackroute.orderdetails.exceptions.DetailsAlreadyExistsException;
import com.stackroute.orderdetails.exceptions.MongoConnectionException;
import com.stackroute.orderdetails.exceptions.NoDetailsFoundException;
import com.stackroute.orderdetails.service.OrderdetailsService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/")
public class OrderdetailsController {

	OrderdetailsService orderdetailsService;

	@Autowired
	public OrderdetailsController(OrderdetailsService orderdetailsService) {
		this.orderdetailsService = orderdetailsService;
	}

	// controller mapping to save the order details

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ResponseEntity<?> saveDetails(@RequestBody Orderdetails orderdetails) throws DetailsAlreadyExistsException {

		Orderdetails savedetails;
		try {
			if ((savedetails = orderdetailsService.saveDetails(orderdetails)) != null) {
				return new ResponseEntity<Orderdetails>(savedetails, HttpStatus.ACCEPTED);
			} else {
				throw new DetailsAlreadyExistsException("details already exists");
			}
		} catch (MongoConnectionException e) {

			return new ResponseEntity<String>("ConnectionFailed", HttpStatus.GATEWAY_TIMEOUT);
		} catch (DetailsAlreadyExistsException e) {

			return new ResponseEntity<String>(e.toString(), HttpStatus.OK);

		}
	}

	// controller mapping to get all details

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<?> getDetails() throws MongoConnectionException {
		try {
			List<Orderdetails> getdetails;
			getdetails = orderdetailsService.getAll();
			if (getdetails.size() != 0) {
				return new ResponseEntity<List<Orderdetails>>(getdetails, HttpStatus.ACCEPTED);
			} else {
				throw new NoDetailsFoundException("Details doesn't exists");
			}
		} catch (NoDetailsFoundException e) {

			return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
		}
	}

	// controller mapping to get details of particular user by giving emailid

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<?> getdetailsByEmail(@RequestParam String emailId) throws MongoConnectionException {
		try {
			Orderdetails getdetails;
			getdetails = orderdetailsService.getByEmailId(emailId);
			if (getdetails != null) {
				return new ResponseEntity<Orderdetails>(getdetails, HttpStatus.FOUND);
			} else {
				throw new NoDetailsFoundException("Details doesn't exists with this email!");
			}
		} catch (NoDetailsFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.NOT_FOUND);
		}
	}
}
