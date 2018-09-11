package com.stackroute.orderdetails.service;

import java.util.List;
import com.stackroute.orderdetails.domain.Orderdetails;
import com.stackroute.orderdetails.exceptions.MongoConnectionException;
import com.stackroute.orderdetails.exceptions.NoDetailsFoundException;

public interface OrderdetailsService {

	public Orderdetails saveDetails(Orderdetails orderdetails) throws MongoConnectionException;

	public List<Orderdetails> getAll() throws NoDetailsFoundException;

	public Orderdetails getByEmailId(String emailId) ;

}
