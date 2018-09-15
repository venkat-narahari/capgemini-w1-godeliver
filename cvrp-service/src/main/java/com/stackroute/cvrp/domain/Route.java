package com.stackroute.cvrp.domain;

public class Route {
	private DateLogistics dateLogistics;
	private Order newOrder;

	public Route() {

	}

	public Route(DateLogistics dataLogistics, Order newOrder) {
		this.dateLogistics = dataLogistics;
		this.newOrder = newOrder;
	}

	public DateLogistics getDateLogistics() {
		return dateLogistics;
	}

	public void setDateLogistics(DateLogistics dateLogistics) {
		this.dateLogistics = dateLogistics;
	}

	@Override
	public String toString() {
		return "Route [dateLogistics=" + dateLogistics + ", newOrder=" + newOrder + "]";
	}

	public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}

}
