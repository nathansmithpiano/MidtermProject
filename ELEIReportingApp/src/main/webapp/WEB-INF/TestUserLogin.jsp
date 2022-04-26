<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="home.do">Go Home</a>
	
	<c:if test="${not empty loggedInUser}">
		<h1>Hello, ${loggedInUser.username }</h1>
	</c:if>
	
	<h1> ${loggedInUser} </h1>

</body>
</html>