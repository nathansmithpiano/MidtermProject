<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Case File</title>
</head>
<body>

	<ul>
		<li>ID: ${caseFile.id}</li>
		<li>Case #: ${caseFile.caseNumber}</li>
		<li>Suspected Crime: ${caseFile.suspectedCrime}</li>
		<li>Description: ${caseFile.description}</li>
		<li>Flag: ${caseFile.flag}</li>
	</ul>
	
	<hr/>
	
	<h3>INCIDENT STUFF</h3>
	<c:set var="incidentList" scope="request" value="${caseFile.incidents }" />
	<jsp:include page="tables/incidents_table.jsp" />
	<hr/>
	
	<h3>NOTE STUFF</h3>
	<c:set var="noteList" scope="request" value="${caseFile.notes }" />
	<jsp:include page="tables/notes_table.jsp" />
	<hr/>

</body>
</html>