package com.stackroute.logisticsservice.domain;

public class Route {
	
	private DateLogistics dateLogistics;
	private Order newOrder;
	
	public Route() {
		
	}
	
	public Route(DateLogistics dateLogistics, Order newOrder) {
		super();
		this.dateLogistics = dateLogistics;
		this.newOrder = newOrder;
	}
	public DateLogistics getDateLogistics() {
		return dateLogistics;
	}
	public void setDateLogistics(DateLogistics dateLogistics) {
		this.dateLogistics = dateLogistics;
	}
	public Order getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}

	@Override
	public String toString() {
		return "Route [dateLogistics=" + dateLogistics + ", newOrder=" + newOrder + "]";
	}
	
	
}
