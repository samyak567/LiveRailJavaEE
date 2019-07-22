<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />
</head>
<body>

<a href="rail/liveTrain">Train Running Status</h3>
<br/>
<a href="rail/liveStation">Live Station Info</h3>
<br/>
<a href="rail/trainsBetweenStations">Trains Between Stations</a>
<br/>
<a href="rail/trainSchedule">Train Schedule(Route)</a>
<br/>
<a href="rail/rescheduledTrains">Rescheduled Trains</a>
<!-- <a href="rail/liveSeatAvailability">Check Seat Availability</h3> -->
<br/>
<a href="rail/liveTrainFare">Live Train Fare</h3>
<br/>
<a href="rail/liveCancelledTrains">Cancelled Trains</h3>

</body>
</html>