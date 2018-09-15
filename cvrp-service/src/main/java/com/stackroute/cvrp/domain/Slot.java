package com.stackroute.cvrp.domain;

import java.util.Arrays;

public class Slot {
	public String slotId;
	public String slotDuration;
	public String slotStartTime;
	public String slotEndTime;
	public String slotNoOfVehicles;
	private String slotCost;
	public Vehicle[] slotVehicle;

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

	public String getSlotEndTIme() {
		return slotEndTime;
	}

	public void setSlotEndTIme(String slotEndTIme) {
		this.slotEndTime = slotEndTIme;
	}

	public String getSlotNoOfVehicle() {
		return slotNoOfVehicles;
	}

	public void setSlotNoOfVehicle(String slotNoOfVehicle) {
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
