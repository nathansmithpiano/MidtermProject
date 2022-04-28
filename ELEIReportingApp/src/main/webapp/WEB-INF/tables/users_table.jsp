<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Begin Card -->
<div class="card">

	<!-- Begin Card Body -->
	<div class="card-body">
	
		<h3 class="card-title">
			Users
		</h3>

		<c:choose>
		
			<c:when test="${empty userList }">
				<h4>No users found</h4>
			</c:when>
			
			<c:otherwise>
			
				<table>
				
					<thead>
						<th>ID</th>
						<th>Permission Level</th>
						<th>Username</th>
						<th>Password</th>
						<th>Active</th>
						<th>Person</th>
					</thead>
					
					<tbody>
						<c:forEach var="user" items="${userList }">
						
							<tr>
								<td>${user.id }</td>
								<td>${user.permissionLevel }</td>
								<td>${user.username }</td>
								<td>${user.password }</td>
								<td>${user.active }</td>
								<td>
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
		
	</div> <!-- end Card Body -->
	
</div> <!-- end Card -->
		