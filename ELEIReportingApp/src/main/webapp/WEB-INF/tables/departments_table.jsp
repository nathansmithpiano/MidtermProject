<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty departmentList }">
		<h4>No department found</h4>
	</c:when>
	
	<c:otherwise>
	
		<table>
		
			<thead>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>Officers</th>
				<th>IncidentPersons</th>
				<th>Incidents</th>
				<th>CaseFiles</th>
			</thead>
			
			<tbody>
			
				<c:forEach var="department" items="${departmentList }">
				
					<tr>
						<td>${department.id }</td>
						<td>
							<a href="department.do?id=${department.id }">
								${department.name }
							</a>
						</td>
						<td>
							<a href="address.do?id=${department.address.id }">
								${department.address.getFormattedAddress() }
							</a>
						</td>
						<td>
							<a href="departmentofficers.do?id=${department.id }">
								${department.officers.size() }
							</a>
						</td>
						<td>incidentpersons</td>
						<td>incidents</td>
						<td>casefiles!</td>
					</tr>
					
				</c:forEach>
				
			</tbody>
			
		</table>
		
	</c:otherwise>
	
</c:choose>
		