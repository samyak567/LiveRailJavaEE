<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Live Train Fare</title>
</head>
<body>
<table border="1">
		<tr>
			<th>${requestScope.model.json.getString("TrainName")}</th>
		</tr>
		<tr>
			<th>Class</th><th>Fare</th>
		</tr>
		<c:forEach items="${requestScope.model.listOfFares}" var="fare" >
        	 <tr>
        	 	<td>${fare.getString("Name")}</td>
        	 	<td>${fare.getString("Fare")}</td>
        	 </tr>
    	</c:forEach>
    </table>
</body>
</html> --%>