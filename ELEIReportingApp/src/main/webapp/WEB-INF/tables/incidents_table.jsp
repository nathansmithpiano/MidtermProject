<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty incidentList }">
		<h4>No incidents found</h4>
	</c:when>
	
	<c:otherwise>
	
		<table>
			<thead>
				<th>Incident ID</th>
				<th>Address</th>
				<th>Case ID</th>
				<th>Reason For Contact</th>
				<th>Description</th>
				<th>Flag</th>
			</thead>
			<tbody>
				<c:forEach var="incident" items="${incidentList }">
					<tr>
						<td>${incident.id }</td>
						<td>${incident.address }</td>
						<td>${incident.caseId }</td>
						<td>${incident.reasonForContact }</td>
						<td>${incident.description }</td>
						<td>${incident.flag }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</c:otherwise>
	
</c:choose>
