<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Officers</title>
</head>
<body>

	<h3>Officers</h3>
	
	<c:choose>
		<c:when test="${not empty department }">
			<h3>${department.officers.size() } officers at ${department.name }</h3>
			<c:set var="officerList" scope="request" value="${department.officers }" />
		</c:when>
	</c:choose>
	
	<jsp:include page="tables/officers_table.jsp" />

</body>
</html>