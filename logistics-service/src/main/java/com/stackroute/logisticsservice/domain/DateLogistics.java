package com.stackroute.logisticsservice.domain;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Domain Object for Specfic date
 */

@Document
public class DateLogistics {
	@Id
	private String date;
	private Slot[] slots;
	
	
	public DateLogistics() {
		
	}
	public DateLogistics(String date, Slot[] slots) {
		super();
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
	@Override
	public String toString() {
		return "DateLogistics [date=" + date + ", slots=" + Arrays.toString(slots) + "]";
	}
	
}
