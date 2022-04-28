<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Begin Card -->
<div class="card">

	<!-- Begin Card Body -->
	<div class="card-body">
	
		<h3 class="card-title">
			<c:choose>
				<c:when test="${empty noteList }">
					No notes found
				</c:when>
				<c:otherwise>
					Notes
				</c:otherwise>
			</c:choose>
		</h3>

		<c:if test="${not empty noteList }">
			
			<table id="noteTable" class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>User ID</th>
						<th>Created</th>
						<th>Updated</th>
						<th>CaseFiles</th>
						<th>Incidents</th>
						<th>Persons</th>
						<th>Content</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="note" items="${noteList }">
						<tr>
							<td>
								<a href="noteFromIncident.do?id=${note.id }&incidentId=${incident.id }">
									${note.id }
								</a>
							</td>
							<td>${note.userId }</td>
							<td>${note.created }</td>
							<td>${note.updated }</td>
							<td>${note.caseFiles.size() }</td>
							<td>${note.incidents.size() }</td>
							<td>${note.persons.size() }</td>
							<td>${note.content }</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
				
		</c:if>
		
	</div> <!-- end Card Body -->
	
</div> <!-- end Card -->
