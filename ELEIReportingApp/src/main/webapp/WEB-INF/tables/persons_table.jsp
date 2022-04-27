<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty personList }">
		<h4>No persons found</h4>
	</c:when>
	
	<c:otherwise>

		<table id="personsTable" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>First</th>
					<th>Middle</th>
					<th>Last</th>
					<th>Title</th>
					<th>Birthdate</th>
					<th>Gender</th>
					<th>Ethnicity</th>
					<th>Description</th>
					<th>Flag</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="person" items="${personList }">
					<tr>
						<td>
							<a href="person.do?id=${person.id }">
								${person.id }
							</a>
						</td>
						<td>${person.firstName }</td>
						<td>${person.middleName }</td>
						<td>${person.lastName }</td>
						<td>${person.title }</td>
						<td>${person.birthDate }</td>
						<td>${person.gender }</td>
						<td>${person.ethnicity.name }</td>
						<td>${person.description }</td>
						<td>${person.flag }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</c:otherwise>

</c:choose>