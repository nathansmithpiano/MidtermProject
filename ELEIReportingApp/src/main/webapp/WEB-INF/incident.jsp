<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Incident</title>
</head>
<body>


	<h1>Incident</h1>

	<ul>
		<li>ID: ${incident.id}</li>
		<li>Reason for contact: ${incident.reasonForContact}</li>
		<li>Location: ${incident.location}</li>
		<li>Description: ${incident.description}</li>
		<li>Flag: ${incident.flag}</li>
		<li>Incident Date: ${incident.incidentDate}</li>
		<li>Address: ${incident.address }</li>
		<li>Case File: ${incident.caseFile }</li>
		<li>Persons: ${incident.persons.size() }</li>
		<li>Notes: ${incident.notes.size()}</li>
	</ul>

	<h3>PERSONS STUFF</h3>
	<c:if test="${not empty note.persons }">
		<c:set var="personList" scope="request" value="${note.persons }" />
		<jsp:include page="tables/persons_table.jsp" />
	</c:if>
	<hr />
	<h3>NOTE STUFF</h3>
	<c:set var="noteList" scope="request" value="${person.notes }" />
	<jsp:include page="tables/notes_table.jsp" />
	<hr />
	
	<!-- <input type="button" onclick="goToAddNewNote.do" value="Add Note">
	<input type="button" onclick="goToAddNewAddress.do" value="Add Note"> -->


</body>
</html>