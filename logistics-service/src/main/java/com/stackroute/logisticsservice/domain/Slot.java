package com.stackroute.logisticsservice.domain;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Domain Object for Specfic slot
 */

@Document
public class Slot {
	private String slotId;
	private String slotDuration;
	private String slotStartTime;
	private String slotEndTime;
	private String slotNoOfVehicles;
	private String slotCost;
	private Vehicle[] slotVehicle;

	public Slot() {

	}

	public Slot(String slotId, String slotDuration, String slotStartTime, String slotEndTime, String slotNoOfVehicles,
			String slotCost, Vehicle[] slotVehicle) {
		super();
		this.slotId = slotId;
		this.slotDuration = slotDuration;
		this.slotStartTime = slotStartTime;
		this.slotEndTime = slotEndTime;
		this.slotNoOfVehicles = slotNoOfVehicles;
		this.slotCost = slotCost;
		this.slotVehicle = slotVehicle;
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public String getSlotDuration() {
		return slotDuration;
	}

	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}

	public String getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(String slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public String getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(String slotEndTime) {
		this.slotEndTime = slotEndTime;
	}

	public String getSlotNoOfVehicles() {
		return slotNoOfVehicles;
	}

	public void setSlotNoOfVehicles(String slotNoOfVehicles) {
		this.slotNoOfVehicles = slotNoOfVehicles;
	}

	public Vehicle[] getSlotVehicle() {
		return slotVehicle;
	}

	public void setSlotVehicle(Vehicle[] slotVehicle) {
		this.slotVehicle = slotVehicle;
	}

	public String getSlotCost() {
		return slotCost;
	}

	public void setSlotCost(String slotCost) {
		this.slotCost = slotCost;
	}

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", slotDuration=" + slotDuration + ", slotStartTime=" + slotStartTime
				+ ", slotEndTime=" + slotEndTime + ", slotNoOfVehicles=" + slotNoOfVehicles + ", slotCost=" + slotCost
				+ ", slotVehicle=" + Arrays.toString(slotVehicle) + "]";
	}

	
}
