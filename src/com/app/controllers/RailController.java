package com.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import javax.servlet.http.;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.pojos.TrainAtStation;
import com.app.service.IRailService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/rail")
public class RailController {
	
	@Autowired
	private IRailService railService;
	
	
//-----------------------------------------------------------------------------------Live Train status------------------------------------------------------------------------------------------------------

	
	@GetMapping("/liveTrain")
	ModelAndView goToLiveTrainForm() {
		return new ModelAndView("rail/liveTrainForm");
	}
	
	@PostMapping("/liveTrain")
	ModelAndView getLiveTrainStatus(@RequestParam String trainNo, @RequestParam String date, Model liveStatusModel) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy"); to add date parsing functionality
		//LocalDate formattedDate = LocalDate.parse(date, dtf);
		//dtf = DateTimeFormatter.
		
		String result = railService.getLiveTrainStatus(trainNo, date);	//calling service layer to fetch a REST response for live running status
		
		if(result == null) {
			//session.setAttribute("status", 0);
			//if there was a problem fetching the REST response, then result will be null.
			//In this case we set status as 0, so that view layer can display error message.
		}
		else {
		//	session.setAttribute("status", 1);
			//status = 1 means successful operation, now we have to parse the JSON data.
			
			char oldChar = '"';
			char newChar = '\'';
			result = result.replace(oldChar, newChar);
			//just replace "double quotes" by 'single quotes' so that there is no discrepancy in string representation of our JSON.
			//result = ""key" : "value"" --> result = "'key' : 'value'". THIS STEP IS TOTALLY OPTIONAL. JSON PARSER SUCCESSFULLY PARSES THE DATA WITHOUT ESCAPING THE DOUBLE QUOTES.
			
			//System.out.println("result -> \n" + result);		//for debug purposes
			
			JSONObject json = new JSONObject(result);
			JSONObject currentStatus = json.getJSONObject("CurrentStation");
			
			liveStatusModel.addAttribute("TrainNumber",  json.getString("TrainNumber"));
			liveStatusModel.addAttribute("StationName", currentStatus.getString("StationName"));
			liveStatusModel.addAttribute("ScheduleArrival", currentStatus.getString("ScheduleArrival"));
			liveStatusModel.addAttribute("ActualArrival", currentStatus.getString("ActualArrival"));
			liveStatusModel.addAttribute("DelayInArrival", currentStatus.getString("DelayInArrival"));
			
		}
		
		return new ModelAndView("rail/liveTrainStatusResult", "model", liveStatusModel);		//returning ModelAndView Object
	}
	
//-----------------------------------------------------------------------------------Live Station Info------------------------------------------------------------------------------------------------------
	
	@GetMapping("/liveStation")
	ModelAndView goToLiveStationForm() {
		return new ModelAndView("rail/liveStationForm");
	}
	
	@PostMapping("/liveStation")
	ModelAndView getLiveStationInfo(@RequestParam String stationCode, Model liveStationInfoModel) {
		
		String result = railService.getLiveStationInfo(stationCode);
		//liveStationInfo.addAttribute("result", result);						//JSON parsing is pending
		
		if(result == null) {
			//session.setAttribute("status", 0);
		}
		else {
			
			//session.setAttribute("status", 0);
			
			JSONObject json = new JSONObject(result);
			String trainArrayJSON = json.getJSONArray("Trains").toString();
			
			ObjectMapper mapper = new ObjectMapper();	//class of Jackson package to convert JSON<-->Java
			
			try {
				
				TrainAtStation[] trainArrayJava = mapper.readValue(trainArrayJSON, TrainAtStation[].class);
				
				List<TrainAtStation> listOfTrains = Arrays.asList(trainArrayJava);
				listOfTrains.stream().forEach(train-> System.out.println(train));
				
				liveStationInfoModel.addAttribute("listOfTrains", listOfTrains);	
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	

		}
		
		return new ModelAndView("rail/liveStationInfoResult", "model", liveStationInfoModel);
	}
	
	
//-----------------------------------------------------------------------------------Trains Between Stations------------------------------------------------------------------------------------------------------

	
	@GetMapping("/trainsBetweenStations")
	ModelAndView goToTrainsBetweenStationsForm() {
		return new ModelAndView("rail/trainsBetweenStationsForm");
	}
	
	@PostMapping("/trainsBetweenStations")
	ModelAndView getTrainsBetweenStations(@RequestParam String fromStation, @RequestParam String toStation, Model trainsBetweenStationsModel) {
		
		String result = railService.getTrainsBetweenStations(fromStation, toStation);
		trainsBetweenStationsModel.addAttribute("toStation", toStation);
		trainsBetweenStationsModel.addAttribute("fromStation", fromStation);
		
		if(result == null) {
			//session.setAttribute("status", 0);
		}
		else {
			
			JSONObject json = new JSONObject(result);
			JSONArray trainArray = json.getJSONArray("Trains");		//array of trains between stations
			String numOfTrains = json.getString("TotalTrains");		//total number of trains between toStation and fromStation
			List<JSONObject> listOfTrains = new ArrayList<>();
			
			//session.setAttribute("status", 1);
			trainsBetweenStationsModel.addAttribute("result", result);
			trainsBetweenStationsModel.addAttribute("numOfTrains", numOfTrains);

			for(int i = 0; i<trainArray.length(); i++) {
				JSONObject train = ((JSONObject)trainArray.get(i));
				listOfTrains.add(train);
				System.out.println(train);
			}
			
			trainsBetweenStationsModel.addAttribute("listOfTrains", listOfTrains);
		}
		
		return new ModelAndView("rail/trainsBetweenStationsResult", "model", trainsBetweenStationsModel);
		
	}

//------------------------------------------------------------------------------Train Schedule------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/trainSchedule")
	ModelAndView goToTrainScheduleForm() {
		return new ModelAndView("rail/trainScheduleForm");
	}
	
	@PostMapping("/trainSchedule")
	ModelAndView getTrainSchedule(@RequestParam String trainNo,   Model trainScheduleModel) {
		
		String result = railService.getTrainSchedule(trainNo);
		trainScheduleModel.addAttribute("trainNo", trainNo);
		
		if(result == null) {
			//session.setAttribute("status", 0);
		}
		else {
			
			//session.setAttribute("status", 1);
			
			JSONObject json = new JSONObject(result);
			JSONArray routeArray = json.getJSONArray("Route");
			List<JSONObject> listOfHalts = new ArrayList<>();
			
			for(int i=0; i<routeArray.length(); i++) {
				JSONObject halt = ((JSONObject)routeArray.get(i));
				listOfHalts.add(halt);
			}
			
			trainScheduleModel.addAttribute("listOfHalts", listOfHalts);
			
		}
		
		return new ModelAndView("rail/trainScheduleResult", "model", trainScheduleModel);
		
	}
	
	
//---------------------------------------------------------------------------------Rescheduled Trains-------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/rescheduledTrains")
	ModelAndView goToRescheduledTrainsForm() {
		return new ModelAndView("rail/rescheduledTrainsForm");
	}
	
	@PostMapping("rescheduledTrains")
	ModelAndView getRescheduledTrains(@RequestParam String date, Model rescheduledTrainsModel) {
		
		String result = railService.getRescheduledTrains(date);
		
		if(result == null) {
			//session.setAttribute("status", 0);
		}
		else {
			
			//session.setAttribute("status", 1);
			
			JSONObject json = new JSONObject(result);
			JSONArray trainArray = json.getJSONArray("Trains");
			String numOfRescheduledTrains = json.getString("TotalTrain");
			List<JSONObject> listOfRescheduledTrains = new ArrayList<>();
			
			for(Object j : trainArray) {
				listOfRescheduledTrains.add((JSONObject)j);
			}
			
			rescheduledTrainsModel.addAttribute("numOfRescheduledTrains", numOfRescheduledTrains);
			rescheduledTrainsModel.addAttribute("listOfRescheduledTrains", listOfRescheduledTrains);
			
		}
		
		return new ModelAndView("rail/rescheduledTrainsResult", "model", rescheduledTrainsModel);
	}
	
	/*	@GetMapping("/liveSeatAvailability")
	String goToSeatAvailabilityForm() {
		return "rail/liveSeatAvailabilityForm";
	}
	
	@PostMapping("/liveSeatAvailability")
	ModelAndView getLiveSeatAvailability(@RequestParam String trainNo,@RequestParam String stationFrom,@RequestParam String stationTo, @RequestParam String date, @RequestParam String classCode, session, Model liveSeatAvailabilityModel) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy"); to add date parsing functionality
		//LocalDate formattedDate = LocalDate.parse(date, dtf);
		//dtf = DateTimeFormatter.
		
		System.out.println(trainNo+stationFrom+stationTo+date+classCode);
		
		String result = railService.getLiveSeatAvailability(trainNo, stationFrom, stationTo, date,classCode);	//calling service layer to fetch a REST response for live seat availability
		
		if(result == null) {
			session.setAttribute("status", 0);
			//if there was a problem fetching the REST response, then result will be null.
			//In this case we set status as 0, so that view layer can display error message.
		}
		else {
			session.setAttribute("status", 1);
			//status = 1 means successful operation, now we have to parse the JSON data.
			
			
			JSONObject json = new JSONObject(result);
			List<String> list = new ArrayList<String>();  
			System.out.println(result);
			JSONArray jsonList = json.getJSONArray("Availability");
			 for (int i = 0; i < jsonList.length(); i++) {
			          list.add(jsonList.getString(i));
			   }
			
			
			liveSeatAvailabilityModel.addAttribute("listOfAvailability",  list);
			
	}
		
		return new ModelAndView("rail/liveSeatAvailabilityResult", "model", liveSeatAvailabilityModel);		//returning ModelAndView Object
	}*/
	
	@GetMapping("/liveTrainFare")
	String goToLiveTrainFareForm() {
		return "rail/liveTrainFareForm";
	}
	
	@PostMapping("/liveTrainFare")
	ModelAndView getLiveTrainFare(@RequestParam String trainNo,@RequestParam String stationFrom,@RequestParam String stationTo, @RequestParam String quota, Model liveTrainFareModel) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy"); to add date parsing functionality
		//LocalDate formattedDate = LocalDate.parse(date, dtf);
		//dtf = DateTimeFormatter.
		
				
		String result = railService.getLiveTrainFare(trainNo, stationFrom, stationTo, quota);	//calling service layer to fetch a REST response for live seat availability
		
		if(result == null) {
			//session.setAttribute("status", 0);
			//if there was a problem fetching the REST response, then result will be null.
			//In this case we set status as 0, so that view layer can display error message.
		}
		else {
			//session.setAttribute("status", 1);
			//status = 1 means successful operation, now we have to parse the JSON data.
			
			
			JSONObject json = new JSONObject(result);
			List<JSONObject> list = new ArrayList<JSONObject>();  
			System.out.println(result);
			JSONArray jsonList = json.getJSONArray("Fares");
			 for (int i = 0; i < jsonList.length(); i++) {
			          list.add((JSONObject)jsonList.get(i));
			   }
		
			
			 liveTrainFareModel.addAttribute("listOfFares",  list);
			 liveTrainFareModel.addAttribute("json",  json);
			
	}
		
		return new ModelAndView("rail/liveTrainFareResult", "model", liveTrainFareModel);		//returning ModelAndView Object
	}

	@GetMapping("/liveCancelledTrains")
	String goToLiveCancelledTrainsForm() {
		return "rail/liveCancelledTrainsForm";
	}
	
	@PostMapping("/liveCancelledTrains")
	ModelAndView getLiveCancelledTrains(@RequestParam String date, Model liveCancelledTrainsModel) {
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy"); to add date parsing functionality
		//LocalDate formattedDate = LocalDate.parse(date, dtf);
		//dtf = DateTimeFormatter.
		
				
		String result = railService.getliveCancelledTrainsModel(date);	//calling service layer to fetch a REST response for live seat availability
		
		if(result == null) {
			//session.setAttribute("status", 0);
			//if there was a problem fetching the REST response, then result will be null.
			//In this case we set status as 0, so that view layer can display error message.
		}
		else {
			//session.setAttribute("status", 1);
			//status = 1 means successful operation, now we have to parse the JSON data.
			
			
			JSONObject json = new JSONObject(result);
			List<JSONObject> list = new ArrayList<JSONObject>();  
			System.out.println(result);
			JSONArray jsonList = json.getJSONArray("Trains");
			 for (int i = 0; i < jsonList.length(); i++) {
			          list.add((JSONObject)jsonList.get(i));
			   }
		
			
			 liveCancelledTrainsModel.addAttribute("listOfTrains",  list);
			 liveCancelledTrainsModel.addAttribute("json",  json);
			
	}
		
		return new ModelAndView("rail/liveCancelledTrainsResult", "model", liveCancelledTrainsModel);		//returning ModelAndView Object
	}
	

	
	@GetMapping("/api")
	public ResponseEntity<String> api() {
		return ResponseEntity.ok().body("{\"JSON\":\"OK\"}");
	}
	
	
	
}

