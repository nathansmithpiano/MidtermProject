<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>

	<c:when test="${empty caseFileList }">
		<h4>No case files found</h4>
	</c:when>
	
	<c:otherwise>

		<table id="caseFileTable" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Case #</th>
					<th>Suspected Crime</th>
					<th>Description</th>
					<th>Flag</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="caseFile" items="${caseFileList }">
					<tr>
						<td>${caseFile.id }</td>
						<td>
							<a href="caseFile.do?id=${caseFile.id }">
								${caseFile.caseNumber }
							</a>
						</td>
						<td>${caseFile.suspectedCrime }</td>
						<td>${caseFile.description }</td>
						<td>${caseFile.flag }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</c:otherwise>

</c:choose>