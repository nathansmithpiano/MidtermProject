<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Officer ${officer.id }</title>
</head>
<body>

	<h3>Officer ${officer.id }</h3>
	
	<h3>Officer Stuff</h3>
	<ul>
		<li>Name: 
			<a href="person.do?id=${officer.person.id }">
				${officer.person.fullName }
			</a>
		</li>
		<li>Supervisor: 
			<a href="person.do?id=${officer.supervisor.id }">
				${officer.supervisor.person.fullName }
			</a>
		</li>
		<li>Subordinates: 
			<ul>
				<c:forEach var="subordinate" items="${officer.subordinates }">
					<li>
						<a href="officer.do?id=${subordinate.id }">
							${subordinate.person.fullName } (Badge ${subordinate.badge })
						</a>
					</li>
				</c:forEach>
			</ul>
		</li>
		<li>Badge: ${officer.badge }</li>
		<li>Image URL: ${officer.imageUrl }</li>
	</ul>
	<hr/>
	
	<h3>Person Stuff</h3>
	<hr />
	
	<h3>Incident Stuff</h3>
	<hr />
	
	<h3>IncidentPerson Stuff</h3>
	<hr />
	
	<h3>CaseFile Stuff</h3>
	<hr />
	
	


</body>
</html>