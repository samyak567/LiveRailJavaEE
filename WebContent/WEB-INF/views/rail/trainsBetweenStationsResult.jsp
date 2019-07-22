<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trains Between ${requestScope.model.fromStation} and ${requestScope.model.toStation}</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" /><!-- figure out how to implement bootstrap -->



</head>
<body>

<h2 style="color:red;">We found ${requestScope.model.numOfTrains} trains between ${requestScope.model.fromStation} and ${requestScope.model.toStation}</h2>
	
	<table border="1">
		<thead>
			<tr>
				<th>Train Number</th><th>Train Name</th><th>Source</th><th>Destination</th><th>Departure Time From Source</th><th>Arrival Time At Destination</th><th>Travel Time</th><th>Train Type</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.model.listOfTrains}" var="train" >
	        	 <tr>
	        	 	<td>${train.getString("TrainNo")}</td>
	        	 	<td>${train.getString("TrainName")}</td>
	        	 	<td>${train.getString("Source")}</td>
	        	 	<td>${train.getString("Destination")}</td>
	        	 	<td>${train.getString("DepartureTime")}</td>
	        	 	<td>${train.getString("ArrivalTime")}</td>
	        	 	<td>${train.getString("TravelTime")}</td>
	        	 	<td>${train.getString("TrainType")}</td>
	        	 </tr>
	   	 </c:forEach>
   	 </tbody>
    </table>
    
</body>
</html> --%>