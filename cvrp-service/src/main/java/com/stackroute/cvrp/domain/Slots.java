package com.stackroute.cvrp.domain;

import java.util.Arrays;

public class Slots {

	private boolean[] slotAvailability;
	private String[] slotCost;

	public Slots() {

	}

	public Slots(boolean[] slotAvailability, String[] slotCost) {

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

	@Override
	public String toString() {
		return "Slots [slotAvailability=" + Arrays.toString(slotAvailability) + ", slotCost="
				+ Arrays.toString(slotCost) + "]";
	}

	public void setSlotCost(String[] slotCost) {
		this.slotCost = slotCost;
	}

}
