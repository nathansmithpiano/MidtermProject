<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty noteList }">
		<h4>No notes found</h4>
	</c:when>
	
	<c:otherwise>
	
		<table>
		
			<thead>
				<th>Note ID</th>
				<th>User ID</th>
				<th>Created</th>
				<th>Updated</th>
				<th>Content</th>
			</thead>
			
			<tbody>
			
				<c:forEach var="note" items="${noteList }">
					<tr>
						<td>${note.id }</td>
						<td>${note.userId }</td>
						<td>${note.created }</td>
						<td>${note.updated }</td>
						<td>${note.content }</td>
					</tr>
				</c:forEach>
				
			</tbody>
			
		</table>
		
	</c:otherwise>
	
</c:choose>
		