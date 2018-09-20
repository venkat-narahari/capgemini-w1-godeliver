package com.stackroute.orderdetails.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Orderdetails {

	@Id
	private String emailId;
	private String orderId;
	private String orderConsumerName;
	private String orderConsumerAddress;
	private String orderConsumerNumber;
	private String orderVolume;
	private String orderDate;
	private String orderQuantity;
	private Location orderLocation;
	private List<Book> books;

	public Orderdetails() {

	}

	public Orderdetails(String emailId, String orderId, String orderConsumerName, String orderConsumerAddress,
			String orderConsumerNumber, String orderVolume, String orderDate, String orderQuantity,
			Location orderLocation, List<Book> books) {
		super();
		this.emailId = emailId;
		this.orderId = orderId;
		this.orderConsumerName = orderConsumerName;
		this.orderConsumerAddress = orderConsumerAddress;
		this.orderConsumerNumber = orderConsumerNumber;
		this.orderVolume = orderVolume;
		this.orderDate = orderDate;
		this.orderQuantity = orderQuantity;
	
		this.orderLocation = orderLocation;
		this.books = books;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderConsumerName() {
		return orderConsumerName;
	}

	public void setOrderConsumerName(String orderConsumerName) {
		this.orderConsumerName = orderConsumerName;
	}

	public String getOrderConsumerAddress() {
		return orderConsumerAddress;
	}

	public void setOrderConsumerAddress(String orderConsumerAddress) {
		this.orderConsumerAddress = orderConsumerAddress;
	}

	public String getOrderConsumerNumber() {
		return orderConsumerNumber;
	}

	public void setOrderConsumerNumber(String orderConsumerNumber) {
		this.orderConsumerNumber = orderConsumerNumber;
	}

	public String getOrderVolume() {
		return orderVolume;
	}

	public void setOrderVolume(String orderVolume) {
		this.orderVolume = orderVolume;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	
	public Location getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(Location orderLocation) {
		this.orderLocation = orderLocation;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	
}
