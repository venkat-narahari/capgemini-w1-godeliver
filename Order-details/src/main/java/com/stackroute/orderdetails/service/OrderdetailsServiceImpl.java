package com.stackroute.orderdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.stackroute.orderdetails.config.KafkaConfig;
import com.stackroute.orderdetails.domain.Orderdetails;
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
	public OrderdetailsServiceImpl(OrderdetailsRepository orderdetailsRepository) {
		this.orderdetailsRepository = orderdetailsRepository;
	}

	@Override
	public Orderdetails saveDetails(Orderdetails orderdetails) {
		List<Orderdetails> orderdetailsList = (List<Orderdetails>) orderdetailsRepository.findAll();
		if (orderdetailsList.contains(orderdetails))
			return null;
		else {
			Orderdetails orderdetailssave = orderdetailsRepository.save(orderdetails);
			kafkaTemplate.send(topic, orderdetails);
			return orderdetailssave;

		}
	}

	@Override
	public List<Orderdetails> getAll() {
		return (List<Orderdetails>) orderdetailsRepository.findAll();
	}

}
