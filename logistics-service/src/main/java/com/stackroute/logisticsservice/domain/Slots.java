package com.stackroute.logisticsservice.domain;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Domain Object for Specfic slots
 */
@Document
public class Slots {
	private boolean[] slotAvailability;
	private String[] slotCost;
	
	public Slots() {
		
	}
	public Slots(boolean[] slotAvailability, String[] slotCost) {
		super();
		this.slotAvailability = slotAvailability;
		this.slotCost = slotCost;
	}
	public boolean[] getSlotAvailability() {
		return slotAvailability;
	}
	public void setSlotAvailability(boolean[] slotAvailability) {
		this.slotAvailability = slotAvailability;
	}
	public String[] getSlotCost() {
		return slotCost;
	}
	public void setSlotCost(String[] slotCost) {
		this.slotCost = slotCost;
	}
	@Override
	public String toString() {
		return "Slots [slotAvailability=" + Arrays.toString(slotAvailability) + ", slotCost="
				+ Arrays.toString(slotCost) + "]";
	}
	
	
}
