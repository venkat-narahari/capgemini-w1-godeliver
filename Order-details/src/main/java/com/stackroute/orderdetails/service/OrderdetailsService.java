package com.stackroute.orderdetails.service;

import java.util.List;

import com.stackroute.orderdetails.domain.Orderdetails;

public interface OrderdetailsService {

	public Orderdetails saveDetails(Orderdetails orderdetails);

	public List<Orderdetails> getAll();

}
