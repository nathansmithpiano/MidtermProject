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

	<ul>
		<h3>PERSON STUFF</h3>
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
	<jsp:include page="addresses_table.jsp" />
	<hr/>
	
	<h3>USER STUFF</h3>
	<jsp:include page="users_table.jsp" />
	<hr/>
	
	<h3>INCIDENT STUFF</h3>
	<jsp:include page="incidents_table.jsp" />
	<hr/>
	
	<h3>NOTE STUFF</h3>
	<jsp:include page="notes_table.jsp" />
	<hr/>

</body>
</html>