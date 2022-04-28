<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Begin Card -->
<div class="card">

	<!-- Begin Card Body -->
	<div class="card-body">
	
		<c:choose>
		
			<c:when test="${empty addressList }">
				<h4>No addresses found</h4>
			</c:when>
			
			<c:otherwise>
			
				<h3 class="card-title">
					Addresses
				</h3>
		
				<table id="addressesTable" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Description</th>
							<th>Street 1</th>
							<th>Street 2</th>
							<th>City</th>
							<th>State</th>
							<th>Zip</th>
							<th>Flag</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="address" items="${addressList }">
							<tr>
								<td>
									<a href="address.do?id=${address.id }">
										${address.id }
									</a>
								</td>
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
		
	</div> <!-- end Card Body -->
	
</div> <!-- end Card -->
