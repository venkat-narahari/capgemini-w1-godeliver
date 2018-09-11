package com.stackroute.orderdetails.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.stackroute.orderdetails.config.KafkaConfig;
import com.stackroute.orderdetails.domain.Orderdetails;
import com.stackroute.orderdetails.exceptions.MongoConnectionException;
import com.stackroute.orderdetails.exceptions.NoDetailsFoundException;
import com.stackroute.orderdetails.repository.OrderdetailsRepository;

@Service
public class OrderdetailsServiceImpl implements OrderdetailsService {
	private OrderdetailsRepository orderdetailsRepository;
	private KafkaConfig kafkaConfig;

	@SuppressWarnings("static-access")
	String topic = kafkaConfig.getTopic();

	@Autowired
	private KafkaTemplate<String, Orderdetails> kafkaTemplate;

	@Autowired
	public OrderdetailsServiceImpl(OrderdetailsRepository orderdetailsRepository, KafkaConfig kafkaConfig) {
		this.orderdetailsRepository = orderdetailsRepository;
		this.kafkaConfig = kafkaConfig;
	}

	/*
	 * Checks whether the details exists with the email id or not and if not exists,
	 * the user will be saved,else throws exception
	 */
	@Override
	public Orderdetails saveDetails(Orderdetails orderdetails) throws MongoConnectionException {
		if (orderdetailsRepository.getByEmailId(orderdetails.getEmailId()) == null) {
			Orderdetails orderdetailssave = orderdetailsRepository.save(orderdetails);
			kafkaTemplate.send(topic, orderdetails);
			return orderdetailssave;
		} else {
			return null;
		}

	}

	/*
	 * Checks whether the details exists or not and if exists,gets all the details
	 */

	@Override
	public List<Orderdetails> getAll() throws NoDetailsFoundException {
		if (((List<Orderdetails>) orderdetailsRepository.findAll()) != null) {
			List<Orderdetails> getalldetails = (List<Orderdetails>) orderdetailsRepository.findAll();
			return getalldetails;
		} else
			return null;
	}

	// method to get get the details based on email id

	@Override
	public Orderdetails getByEmailId(String emailId) {
		if (orderdetailsRepository.getByEmailId(emailId) != null) {
			Orderdetails getdetails = orderdetailsRepository.getByEmailId(emailId);
			return getdetails;
		} else {
			return null;
		}

	}

	
}
