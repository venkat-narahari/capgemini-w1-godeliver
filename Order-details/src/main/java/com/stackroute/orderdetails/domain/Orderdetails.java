package com.stackroute.orderdetails.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Orderdetails {

	@Id
	private String id;
	private int orderId;
	private String orderConsumerName;
	private String[] orderConsumerAddress;
	private String orderConsumerNumber;
	private Location[] orderLocation;
	private String orderVolume;
	private String orderDate;
	private String availableSlots;
	private String selectedSlots;
	private String orderQuantity;
	private String emailId;
	private String price;

	public Orderdetails() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderConsumerName() {
		return orderConsumerName;
	}

	public void setOrderConsumerName(String orderConsumerName) {
		this.orderConsumerName = orderConsumerName;
	}

	public String[] getOrderConsumerAddress() {
		return orderConsumerAddress;
	}

	public void setOrderConsumerAddress(String[] orderConsumerAddress) {
		this.orderConsumerAddress = orderConsumerAddress;
	}

	public String getOrderConsumerNumber() {
		return orderConsumerNumber;
	}

	public void setOrderConsumerNumber(String orderConsumerNumber) {
		this.orderConsumerNumber = orderConsumerNumber;
	}

	public Location[] getOrderLocation() {
		return orderLocation;
	}

	public void setOrderLocation(Location[] orderLocation) {
		this.orderLocation = orderLocation;
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

	public String getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(String availableSlots) {
		this.availableSlots = availableSlots;
	}

	public String getSelectedSlots() {
		return selectedSlots;
	}

	public void setSelectedSlots(String selectedSlots) {
		this.selectedSlots = selectedSlots;
	}

	public String getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Orderdetails(String id, int orderId, String orderConsumerName, String[] orderConsumerAddress,
			String orderConsumerNumber, Location[] orderLocation, String orderVolume, String orderDate,
			String availableSlots, String selectedSlots, String orderQuantity, String emailId, String price) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.orderConsumerName = orderConsumerName;
		this.orderConsumerAddress = orderConsumerAddress;
		this.orderConsumerNumber = orderConsumerNumber;
		this.orderLocation = orderLocation;
		this.orderVolume = orderVolume;
		this.orderDate = orderDate;
		this.availableSlots = availableSlots;
		this.selectedSlots = selectedSlots;
		this.orderQuantity = orderQuantity;
		this.emailId = emailId;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Orderdetails [id=" + id + ", orderId=" + orderId + ", orderConsumerName=" + orderConsumerName
				+ ", orderConsumerAddress=" + orderConsumerAddress + ", orderConsumerNumber=" + orderConsumerNumber
				+ ", orderLocation=" + orderLocation + ", orderVolume=" + orderVolume + ", orderDate=" + orderDate
				+ ", availableSlots=" + availableSlots + ", selectedSlots=" + selectedSlots + ", orderQuantity="
				+ orderQuantity + ", emailId=" + emailId + ", price=" + price + "]";
	}

}
