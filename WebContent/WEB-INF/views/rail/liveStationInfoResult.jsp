<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Live Station Info</title>
</head>
<body>

	<h2 style="color:red;">The following trains will pass by this station in the next 4 hours</h2>
	
	<table border="1">
		<tr>
			<th>Train Number</th><th>Name</th><th>Source</th><th>Destination</th><th>Halt</th><th>ScheduleArrival</th><th>ScheduleDeparture</th><th>ActualArrival</th><th>ActualDeparture</th><th>DelayInArrival</th><th>DelayInDeparture</th>
		</tr>
		<c:forEach items="${requestScope.model.listOfTrains}" var="train" >
        	 <tr>
        	 	<td>${train.number}</td>
        	 	<td>${train.name}</td>
        	 	<td>${train.source}</td>
        	 	<td>${train.destination}</td>
        	 	<td>${train.halt}</td>
        	 	<td>${train.scheduleArrival}</td>
        	 	<td>${train.scheduleDeparture}</td>
        	 	<td>${train.expectedArrival}</td>
        	 	<td>${train.expectedDeparture}</td>
        	 	<td>${train.delayInArrival}</td>
        	 	<td>${train.delayInDeparture}</td>
        	 </tr>
    	</c:forEach>
    </table>
    
</body>
</html> --%>