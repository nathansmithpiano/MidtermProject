<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty addressList }">
		<h4>No addresses found</h4>
	</c:when>
	
	<c:otherwise>
	
		<table>
			<thead>
				<th>Address ID</th>
				<th>Description</th>
				<th>Street 1</th>
				<th>Street 2</th>
				<th>City</th>
				<th>State</th>
				<th>Zip</th>
				<th>Flag</th>
			</thead>
			<tbody>
				<c:forEach var="address" items="${addressList }">
					<tr>
						<td>${address.id }</td>
						<td>${address.description }</td>
						<td>${address.streetOne }</td>
						<td>${address.streetTwo }</td>
						<td>${address.city }</td>
						<td>${address.stateCode }</td>
						<td>${address.zip }</td>
						<td>${address.flag }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</c:otherwise>
	
</c:choose>
