<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
    <%@include file="../../../bootstrap/bootstrap.css" %>
    
</style>
<meta charset="UTF-8">
<title>Cancelled Trains</title>
</head>
<body>
<h2 style="color:red;">Total Number of Cancelled Trains : ${requestScope.model.json.getString("TotalTrain")}</h2>

<table class="table table-hover">
		
		<tr>
			<th scope="col" >TrainNumber</th>
			<th scope="col" >TrainName</th>
			<th scope="col" >StartDate</th>
			<th scope="col" >TrainType</th>
			<th scope="col" >Source</th>
			<th scope="col" >Destination</th>
		</tr>
		<c:forEach items="${requestScope.model.listOfTrains}" var="train" >
        	 <tr>
        	 	<td>${train.getString("TrainNumber")}</td>
        	 	<td>${train.getString("TrainName")}</td>
        	 	<td>${train.getString("StartDate")}</td>
        	 	<td>${train.getString("TrainType")}</td>
        	 	<td>${train.getString("Source")}</td>
        	 	<td>${train.getString("Destination")}</td>
        	 </tr>
    	</c:forEach>
    </table>
</body>
</html> --%>