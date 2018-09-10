package com.stackroute.orderdetails.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.stackroute.orderdetails.domain.Orderdetails;

@Repository
public interface OrderdetailsRepository extends CrudRepository<Orderdetails, Integer> {

	public Orderdetails getByEmailId(String emailId);

}
