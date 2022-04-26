<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${address.getFormattedAddress() }</title>
</head>
<body>

	<h3>${address.getFormattedAddress() }</h3>
	<ul>
		<li>ID: ${address.id}</li>
		<li>Description: ${address.description}</li>
		<li>StreetOne: ${address.streetOne}</li>
		<li>StreetTwo: ${address.streetTwo}</li>
		<li>City: ${address.city}</li>
		<li>StateCode: ${address.stateCode}</li>
		<li>Zip: ${address.zip}</li>
		<li>Flag: ${address.flag}</li>
		<li>Incidents: ${address.incidents.size()}</li>
		<li>Persons: ${address.persons.size() }</li>
	</ul>
	
	<hr/>
	
	<h3>INCIDENT STUFF</h3>
	<c:if test="${not empty address.incidents }">
		<c:set var="incidentList" scope="request" value="${address.incidents }" />
		<jsp:include page="tables/incidents_table.jsp" />
	</c:if>
	<hr/>
	
	<h3>PERSONS STUFF</h3>
	<c:if test="${not empty address.persons }">
		<c:set var="personList" scope="request" value="${address.persons }" />
		<jsp:include page="tables/persons_table.jsp" />
	</c:if>
	<hr/>
	
	<h3>INCIDENTPERSON STUFF</h3>
	<hr/>
	
	<h3>CASEFILE STUFF</h3>
	<hr/>
	
</body>
</html>