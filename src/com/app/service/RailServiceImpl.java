package com.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RailServiceImpl implements IRailService {
	
	private static String apiKey = "daf87a2cad702f16301c77fb45e9fbff";	//what if we remove static, how will this affect the performance and utility 
	private URL url;
	private HttpURLConnection conn;
	
	@Override
	public String getLiveTrainStatus(String trainNo, String date) {
		
		String url = "https://indianrailapi.com/api/v2/livetrainstatus/apikey/" + apiKey +"/trainnumber/" + trainNo + "/date/" + date + "/";
		return getRESTResponse(url);
		
	}

	@Override
	public String getLiveStationInfo(String stationCode) {
		
		int hours = 4;		//can be switched between 2 and 4 hours
		String url = "https://indianrailapi.com/api/v2/LiveStation/apikey/"+ apiKey +"/StationCode/" + stationCode + "/hours/"+ hours +"/";
		return getRESTResponse(url);
		
	}

	@Override
	public String getTrainsBetweenStations(String fromStation, String toStation) {
		
		String url = "https://indianrailapi.com/api/v2/TrainBetweenStation/apikey/" + apiKey +"/From/" + fromStation +"/To/" + toStation;
		return getRESTResponse(url);
				
	}
	
	@Override
	public String getTrainSchedule(String trainNo) {
		
		String url = "https://indianrailapi.com/api/v2/TrainSchedule/apikey/" + apiKey + "/TrainNumber/" + trainNo;
		return getRESTResponse(url);
	}
	
	@Override
	public String getRescheduledTrains(String date) {
		
		String url = "https://indianrailapi.com/api/v2/RescheduledTrains/apikey/" + apiKey + "/Date/" + date;
		return getRESTResponse(url);
		
	}

	/*@Override
	public String getLiveSeatAvailability(String trainNo, String stationFrom, String stationTo, String date,
			String classCode) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public String getLiveTrainFare(String trainNo, String stationFrom, String stationTo, String quota) {
		String url = "https://indianrailapi.com/api/v2/TrainFare/apikey/"+ apiKey +"/TrainNumber/"+ trainNo +"/From/"+ stationFrom +"/To/"+ stationTo +"/Quota/"+quota;
		return getRESTResponse(url);
	}

	@Override
	public String getliveCancelledTrainsModel(String date) {
		String url = "https://indianrailapi.com/api/v2/CancelledTrains/apikey/"+ apiKey +"/Date/"+ date;
		return getRESTResponse(url);
	}
	
//------------------------------------------------------------------------------Centralized REST calling method--------------------------------------------------------------------------------

	@Override
	public String getRESTResponse(String preparedURL) {
		try {
					url = new URL(preparedURL);
					conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");	//test what happens if I comment this line.
					
					int responseCode;
					if((responseCode = conn.getResponseCode()) != 200) {
						throw new RuntimeException("Got response code " + responseCode + " at Service Layer getRESTResponse()");
						
					}
					
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					StringBuilder output = new StringBuilder();
					String temp = null;
					
					while((temp = br.readLine()) != null) {
						output.append(temp);
					}
					
					return output.toString();
					
				} catch (MalformedURLException e) {
					System.out.println("MalformedURLException in Service layer getRESTResponse()");
					e.printStackTrace();
				}
				catch (IOException e) {
					System.out.println("IOException in Service layer getRESTResponse()");
					e.printStackTrace();
				} 
				
				return null;	//null returned if some exception occurs
	}



	
	
	

}
