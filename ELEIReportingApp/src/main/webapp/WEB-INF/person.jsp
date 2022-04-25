<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${person.fullName } (id ${person.id })</title>
</head>
<body>

	<h3>PERSON STUFF</h3>
	<ul>
		<li>ID: ${person.id}</li>
		<li>First Name: ${person.firstName}</li>
		<li>Middle Name: ${person.middleName}</li>
		<li>Last Name: ${person.lastName}</li>
		<li>Title: ${person.title}</li>
		<li>BirthDate: ${person.birthDate}</li>
		<li>Gender: ${person.gender}</li>
		<li>Description: ${person.description}</li>
		<li>Flag: ${person.flag}</li>
		<li>Ethnicity ID: ${person.ethnicity.id }</li>
		<li>Ethnicity: ${person.ethnicity.name }"</li>
	</ul>
	
	<hr/>
	
	<h3>ADDRESS STUFF</h3>
	<c:set var="addressList" scope="request" value="${person.addresses }" />
	<jsp:include page="tables/addresses_table.jsp" />
	<hr/>
	
	<h3>USER STUFF</h3>
	<c:set var="userList" scope="request" value="${person.users }" />
	<jsp:include page="tables/users_table.jsp" />
	<hr/>
	
	<h3>INCIDENT STUFF</h3>
	<c:set var="incidentList" scope="request" value="${person.incidents }" />
	<jsp:include page="tables/incidents_table.jsp" />
	<hr/>
	
	<h3>NOTE STUFF</h3>
	<c:set var="noteList" scope="request" value="${person.notes }" />
	<jsp:include page="tables/notes_table.jsp" />
	<hr/>

</body>
</html>