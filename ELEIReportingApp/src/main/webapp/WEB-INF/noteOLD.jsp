<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Note</title>
</head>
<body>


	<h1>Note</h1>

	<ul>
		<li>ID: ${note.id}</li>
		<li>Created: ${note.created}</li>
		<li>Updated: ${note.updated}</li>
		<li>Content: ${note.content}</li>
		<li>User ID: ${note.userId}</li>
		<li>Incidents: ${note.incidents.size()}</li>
		<li>Case Files: ${note.caseFiles.size() }</li>
		<li>Persons: ${note.persons.size() }</li>
	</ul>

	<hr />
	<h3>CaseFile Stuff</h3>
	<hr />
	<h3>INCIDENT STUFF</h3>
	<c:if test="${not empty note.incidents }">
		<c:set var="incidentList" scope="request" value="${note.incidents }" />
		<jsp:include page="tables/incidents_table.jsp" />
	</c:if>
	<hr />

	<h3>PERSONS STUFF</h3>
	<c:if test="${not empty note.persons }">
		<c:set var="personList" scope="request" value="${note.persons }" />
		<jsp:include page="tables/persons_table.jsp" />
	</c:if>
	<hr />


</body>
</html>