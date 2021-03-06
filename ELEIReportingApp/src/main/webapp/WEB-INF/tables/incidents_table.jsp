<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Begin Card -->
<div class="card">
	
	<!-- Begin Card Body -->
	<div class="card-body">
	
			<c:choose>
			
				<c:when test="${empty incidentList }">
					<h4>No incidents found</h4>
				</c:when>
				
				<c:otherwise>
				
					<h3 class="card-title">
						Incidents
					</h3>

					<table id="incidentTable" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Address</th>
								<th>Case #</th>
								<th>Reason For Contact</th>
								<th>Description</th>
								<th>Flag</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="incident" items="${incidentList }">
								<tr>
									<td>
										<a href="incident.do?id=${incident.id }">
											${incident.id }
										</a>
									</td>
									<td>
										<a href="address.do?id=${incident.address.id }">
											${incident.address.getFormattedAddress() }
										</a>
									</td>
									<td>
										<a href="caseFile.do?id=${incident.caseFile.id }">
											${incident.caseFile.caseNumber }
										</a>
									</td>
									<td>${incident.reasonForContact }</td>
									<td>${incident.description }</td>
									<td>${incident.flag }</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					
				</c:otherwise>
				
			</c:choose>
		
	</div> <!-- end Card Body -->
	
</div> <!-- end Card -->