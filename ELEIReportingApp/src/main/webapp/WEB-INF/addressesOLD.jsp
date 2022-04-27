<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Addresses</title>
</head>
<body>

	<h3>officers.jsp</h3>
	
	<%-- <c:choose>
		<c:when test="${not empty department }">
			<h3>${department.officers.size() } officers at ${department.name }</h3>
		</c:when>
	</c:choose> --%>
	
	<jsp:include page="tables/addresses_table.jsp" />

</body>
</html>