<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Live Seat Availability</title>
</head>
<body>
<table border="1">
		<tr>
			<th>JourneyDate</th><th>Availability</th><th>Confirmation Possibility</th>
		</tr>
		<c:forEach items="${requestScope.model.listOfAvailability}" var="Avail" >
        	 <tr>
        	 	<td>${Avail.JourneyDate}</td>
        	 	<td>${Avail.Availability}</td>
        	 	<td>${Avail.Confirm}</td>
        	 </tr>
    	</c:forEach>
    </table>
</body>
</html> --%>