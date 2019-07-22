package com.app.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrainAtStation {
	
	private String name, number, source, destination, scheduleArrival, scheduleDeparture, halt, expectedArrival, delayInArrival, expectedDeparture, delayInDeparture;
	
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Number")
	public String getNumber() {
		return number;
	}
	@JsonProperty("Number")
	public void setNumber(String number) {
		this.number = number;
	}

	@JsonProperty("Source")
	public String getSource() {
		return source;
	}
	@JsonProperty("Source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("Destination")
	public String getDestination() {
		return destination;
	}
	@JsonProperty("Destination")
	public void setDestination(String destination) {
		this.destination = destination;
	}

	@JsonProperty("ScheduleArrival")
	public String getScheduleArrival() {
		return scheduleArrival;
	}
	@JsonProperty("ScheduleArrival")
	public void setScheduleArrival(String scheduleArrival) {
		this.scheduleArrival = scheduleArrival;
	}

	@JsonProperty("ScheduleDeparture")
	public String getScheduleDeparture() {
		return scheduleDeparture;
	}
	@JsonProperty("ScheduleDeparture")
	public void setScheduleDeparture(String scheduleDeparture) {
		this.scheduleDeparture = scheduleDeparture;
	}

	@JsonProperty("Halt")
	public String getHalt() {
		return halt;
	}
	@JsonProperty("Halt")
	public void setHalt(String halt) {
		this.halt = halt;
	}

	@JsonProperty("ExpectedArrival")
	public String getExpectedArrival() {
		return expectedArrival;
	}
	@JsonProperty("ExpectedArrival")
	public void setExpectedArrival(String expectedArrival) {
		this.expectedArrival = expectedArrival;
	}

	@JsonProperty("DelayInArrival")
	public String getDelayInArrival() {
		return delayInArrival;
	}
	@JsonProperty("DelayInArrival")
	public void setDelayInArrival(String delayInArrival) {
		this.delayInArrival = delayInArrival;
	}

	@JsonProperty("ExpectedDeparture")
	public String getExpectedDeparture() {
		return expectedDeparture;
	}
	@JsonProperty("ExpectedDeparture")
	public void setExpectedDeparture(String expectedDeparture) {
		this.expectedDeparture = expectedDeparture;
	}
	
	@JsonProperty("DelayInDeparture")
	public String getDelayInDeparture() {
		return delayInDeparture;
	}
	@JsonProperty("DelayInDeparture")
	public void setDelayInDeparture(String delayInDeparture) {
		this.delayInDeparture = delayInDeparture;
	}

	@Override
	public String toString() {
		return "TrainAtStation [name=" + name + ", number=" + number + ", source=" + source + ", Destination="
				+ destination + ", ScheduleArrival=" + scheduleArrival + ", ScheduleDeparture=" + scheduleDeparture
				+ ", Halt=" + halt + ", ExpectedArrival=" + expectedArrival + ", DelayInArrival=" + delayInArrival
				+ ", ExpectedDeparture=" + expectedDeparture + ", DelayInDeparture=" + delayInDeparture + "]\n";
	}
	
	

}
