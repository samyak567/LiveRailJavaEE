package com.app.service;

public interface IRailService {
	
	String getLiveTrainStatus(String trainNo, String date);
	String getLiveStationInfo(String stationCode);
	String getTrainsBetweenStations(String fromStation, String toStation);
	String getRESTResponse(String preparedURL);
	String getTrainSchedule(String trainNo);
	String getRescheduledTrains(String date);
//	String getLiveSeatAvailability(String trainNo, String stationFrom, String stationTo, String date, String classCode);
	String getLiveTrainFare(String trainNo, String stationFrom, String stationTo, String quota);
	String getliveCancelledTrainsModel(String date);
	
}
