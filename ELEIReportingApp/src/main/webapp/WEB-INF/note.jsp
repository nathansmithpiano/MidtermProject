<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Note ${note.id }</title>
</head>
<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	
	<!-- main div wrapper -->
	<div class="wrapper">
	
		<!-- Generics -->
		<jsp:include page="generic/navbar.jsp" />
		<jsp:include page="generic/sidebar.jsp" />
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
		
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
					
						<!-- Left Title -->
						<div class="col-sm-6">
							<h1 class="m-0">Note ${note.id }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Notes</a></li>
								<li class="breadcrumb-item active">Note ${note.id }</li>
							</ol>
						</div><!-- /.col -->
						
					</div><!-- /.row -->
				</div><!-- /.container-fluid -->
			</div> <!-- end Content Header (Page header) -->
			
			<!-- Main content -->
    		<section class="content">
    			<div class="container-fluid">
    				<div class="row">
    					<div class="col-12">
    					
    						<!-- Begin Note Card -->
    						 <div class="card">
    				
		    					<!-- Card header -->
		    					<div class="card-header">
		    						<h3 class="card-title">
		    							Note ${note.id }
		    						</h3>
		    						
		    						<!-- Buttons -->
		    						<div class="float-right">
			    						<div class="btn-group">
			    						
			    						<a href="goToUpdateNote.do?id=${note.id }&incidentId=${incident.id }">
											<button type="button" class="btn btn-info">Update</button>
										</a>
										<c:choose>
											<c:when test="${not empty incident }">
												<a href="deleteNote.do?id=${note.id }&incidentId=${incident.id }">
													<button type="button" class="btn btn-danger">Delete</button>
												</a>
											</c:when>
											<c:otherwise>
												<a href="deleteNote.do?id=${note.id }">
													<button type="button" class="btn btn-danger">Delete</button>
												</a>
											</c:otherwise>
										</c:choose>
			    						
										</div>
									</div>
    						
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
	    							<!-- Note Stuff -->
									<ul>
										<li>Id: ${note.id }</li>
										<li>User ID: ${note.userId }</li>
										<li>Created: ${note.created }</li>
										<li>Updated: ${note.updated }</li>
										<li>Content: ${note.content }</li>
										<li>CaseFiles: ${note.caseFiles.size() }</li>
										<li>Incidents: ${note.incidents.size() }</li>
										<li>Persons: ${note.persons.size() }</li>
									</ul>
									
								</div> <!-- end Card Body -->
							</div> <!-- end Card -->
								
							<!-- CaseFiles Card -->
							<jsp:include page="tables/casefiles_table.jsp" />
									
							<!-- Incidents Card -->
							<jsp:include page="tables/incidents_table.jsp" />
								
							<!-- Persons Card -->
							<jsp:include page="tables/persons_table.jsp" />
								
	    				</div> <!-- end col-12 -->
	    			</div> <!-- end row -->
	   			</div> <!-- end container-fluid -->
    		</section>
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
		
	</div> <!-- end main div wrapper -->
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
	
	<!-- TABLE SCRIPTS -->
	<jsp:include page="tables/table_scripts.jsp" />
	
	<!-- SPECIFIC TABLE FEATURES -->
	<script>
		$(function () {
			$("#caseFileTable").DataTable({
			"paging": true,
			"searching": true,
			"ordering": true,
			"info": true,
			"autoWidth": false,
			"responsive": true,
			"lengthChange": false,
			"autoWidth": false
			});
		});
		$(function () {
			$("#incidentTable").DataTable({
			"paging": true,
			"searching": true,
			"ordering": true,
			"info": true,
			"autoWidth": false,
			"responsive": true,
			"lengthChange": false,
			"autoWidth": false
			});
		});
		$(function () {
			$("#personsTable").DataTable({
			"paging": true,
			"searching": true,
			"ordering": true,
			"info": true,
			"autoWidth": false,
			"responsive": true,
			"lengthChange": false,
			"autoWidth": false
			});
		});
	</script>
	
</body>
</html>