<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Train Schedule for ${requestScope.model.trainNo}</title>
</head>
<body>

<h2 style="color:red;">Halts for train no. ${requestScope.model.trainNo} :-</h2>
	
	<table border="1">
		<tr>
			<th>HaltNo</th><th>Station Code</th><th>Station Name</th><th>ArrivalTime</th><th>Departure Time</th><th>Distance</th>
		</tr>
	 	<c:forEach items="${requestScope.model.listOfHalts}" var="train" >
        	 <tr>
        	 	<td>${train.getString("SerialNo")}</td>
        	 	<td>${train.getString("StationCode")}</td>
        	 	<td>${train.getString("StationName")}</td>
        	 	<td>${train.getString("ArrivalTime")}</td>
        	 	<td>${train.getString("DepartureTime")}</td>
        	 	<td>${train.getString("Distance")}</td>
        	 </tr>
    	</c:forEach>
    </table>
    
</body>
</html> --%>