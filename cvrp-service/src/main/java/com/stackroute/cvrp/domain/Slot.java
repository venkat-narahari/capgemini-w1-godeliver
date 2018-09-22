package com.stackroute.cvrp.domain;

import java.util.Arrays;


public class Slot {
	private String slotId;
	private String slotDuration;
	private String slotStartTime;
	private String slotEndTime;
	private String slotNoOfVehicles;
	private String slotCost;
	private Vehicle[] slotVehicle;

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

	public void setSlotEndTime(String slotEndTIme) {
		this.slotEndTime = slotEndTIme;
	}

	public String getSlotNoOfVehicles() {
		return slotNoOfVehicles;
	}

	public void setSlotNoOfVehicles(String slotNoOfVehicle) {
		this.slotNoOfVehicles = slotNoOfVehicle;
	}

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", slotDuration=" + slotDuration + ", slotStartTime=" + slotStartTime
				+ ", slotEndTIme=" + slotEndTime + ", slotNoOfVehicle=" + slotNoOfVehicles + ", slotCost=" + slotCost
				+ ", slotVehicle=" + Arrays.toString(slotVehicle) + "]";
	}

	public Slot() {
		// super();
		// TODO Auto-generated constructor stub
	}

	public Slot(String slotId, String slotDuration, String slotStartTime, String slotEndTIme, String slotNoOfVehicle,
			Vehicle[] slotVehicle) {
		super();
		this.slotId = slotId;
		this.slotDuration = slotDuration;
		this.slotStartTime = slotStartTime;
		this.slotEndTime = slotEndTIme;
		this.slotNoOfVehicles = slotNoOfVehicle;
		;
		this.slotVehicle = slotVehicle;
	}

	public Vehicle[] getSlotVehicle() {
		return slotVehicle;
	}

	public void setSlotVehicle(Vehicle[] slotVehicle) {
		this.slotVehicle = slotVehicle;
	}

}
