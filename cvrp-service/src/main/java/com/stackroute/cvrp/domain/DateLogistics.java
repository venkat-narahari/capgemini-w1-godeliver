package com.stackroute.cvrp.domain;

import java.util.Arrays;

public class DateLogistics {
	private String date;
	private Slot[] slots;

	public DateLogistics() {

	}

	@Override
	public String toString() {
		return "DateLogistics [date=" + date + ", slots=" + Arrays.toString(slots) + "]";
	}

	public DateLogistics(String date, Slot[] slots) {

		this.date = date;
		this.slots = slots;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}

}
