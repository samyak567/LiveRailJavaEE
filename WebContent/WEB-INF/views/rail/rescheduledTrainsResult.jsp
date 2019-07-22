<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rescheduled Trains</title>
</head>
<body>
<h3 style="color:red;">There are ${requestScope.model.numOfRescheduledTrains} rescheduled trains on the given date</h3>
<c:if test="${requestScope.model.numOfRescheduledTrains gt 0}">
	<table border="1">
		<thead><tr><th>Train Number</th><th>Train Name</th><th>Start Date</th><th>Start Time</th><th>Train Type</th><th>Source</th><th>Destination</th><th>Rescheduled By(minutes)</th><th>Rescheduled Date</th><th>Rescheduled Time</th></tr></thead>
		<tbody>
			<c:forEach items="${requestScope.model.listOfRescheduledTrains}" var="train">
				<tr>
					<td>${train.getString("TrainNumber")}</td>
					<td>${train.getString("TrainName")}</td>
					<td>${train.getString("StartDate")}</td>
					<td>${train.getString("StartTime")}</td>
					<td>${train.getString("TrainType")}</td>
					<td>${train.getString("Source")}</td>
					<td>${train.getString("Destination")}</td>
					<td>${train.getString("RescheduledBy")}</td>
					<td>${train.getString("RescheduledDate")}</td>
					<td>${train.getString("RescheduledTime")}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
</body>
</html> --%>