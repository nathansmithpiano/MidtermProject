<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty noteList }">
		<h4>No notes found</h4>
	</c:when>
	
	<c:otherwise>

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
							<a href="note.do?id=${note.id }">
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
		
	</c:otherwise>

</c:choose>