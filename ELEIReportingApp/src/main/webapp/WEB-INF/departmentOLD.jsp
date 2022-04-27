<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${department.name }</title>
</head>
<body>

	<h3>${department.name }</h3>
	
	<h3>Department Stuff</h3>
	<ul>
		<li>ID: ${department.id }</li>
		<li>Name: ${department.name }</li>
		<li>Address: 
			<a href="address.do?id=${department.address.id }">
				${department.address.getFormattedAddress() }
			</a>
		</li>
	</ul>
	<hr />
	
	<c:if test="${not empty department.officers }">
		<h3>Officer Stuff</h3>
		<c:set var="officerList" scope="request" value="${department.officers }" />
		<jsp:include page="tables/officers_table.jsp" />
		<hr />
	</c:if>
	
	<h3>Incident Stuff</h3>
	<hr />
	
	<h3>IncidentPerson Stuff</h3>
	<hr />
		
	<h3>CaseFile Stuff</h3>
	<hr />
	
	
	

</body>
</html>