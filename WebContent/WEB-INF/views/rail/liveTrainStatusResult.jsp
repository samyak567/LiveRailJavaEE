<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Live Status</title>
</head>
<body>
<% if(session.getAttribute("status").equals(1)) { %>
	<p>Train Number : ${requestScope.model.TrainNumber}</p>
	<p>Station Name : ${requestScope.model.StationName}</p>
	<p>Scheduled Arrival : ${requestScope.model.ScheduleArrival}</p>
	<p>Actual Arrival : ${requestScope.model.ActualArrival}</p>
	<p>Delay In Arrival : ${requestScope.model.DelayInArrival}</p>
	
	<p>${sessionScope.result}</p>
	
<%}
else {
%>
"<p> No response </p>"
<%
}%>

</body>
</html> --%>