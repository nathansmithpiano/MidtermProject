<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty officerList }">
		<h4>No officers found</h4>
	</c:when>
	
	<c:otherwise>
	
		<table>
		
			<thead>
				<th>ID</th>
				<th>Badge</th>
				<th>Name</th>
				<th>Supervisor</th>
				<th>Subordinates</th>
				<th>Image</th>
			</thead>
			
			<tbody>
			
				<c:forEach var="officer" items="${officerList }">
				
					<tr>
						<td>${officer.id }</td>
						<td>
							${officer.badge }
						</td>
						<td>
							<a href="officer.do?id=${officer.person.id }">
								${officer.person.fullName }
							</a>
						</td>
						<td>
							<c:if test="${not empty officer.supervisor }">
								<a href="officer.do?id=${officer.supervisor.id }">
									${officer.supervisor.person.fullName } (Badge ${officer.supervisor.badge })
								</a>
							</c:if>
						</td>
						<td>${officer.subordinates.size() }<td>
						<td>
							<c:choose>
								<c:when test="${empty officer.imageUrl }">
								</c:when>
								<c:otherwise>
									<img src="${officer.imageUrl }" />
								</c:otherwise>
							</c:choose>
							<a href="person.do?id=${user.person.id }">
								${user.person.fullName }
							</a>
						</td>
					</tr>
					
				</c:forEach>
				
			</tbody>
			
		</table>
		
	</c:otherwise>
	
</c:choose>
		