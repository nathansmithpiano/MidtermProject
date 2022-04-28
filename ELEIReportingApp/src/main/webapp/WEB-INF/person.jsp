<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Person</title>
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
							<h1 class="m-0">${person.fullName }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="allPersons.do">
										Persons
									</a>
								</li>
								<li class="breadcrumb-item active">
									<a href="person.do?id=${person.id }">
										${person.fullName }
									</a>
								</li>
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
    						 <div class="card">
    				
		    					<!-- Card header -->
		    					<div class="card-header">
		    					
		    						<h3 class="card-title">
		    							Person Info
		    						</h3>
		    						
		    						<!-- Buttons -->
		    						<div class="float-right">
			    						<div class="btn-group">
			    							<button type="button" class="btn btn-warning">Add Note</button>
											<button type="button" class="btn btn-info">Update</button>
	                  						<button type="button" class="btn btn-danger">Delete</button>
										</div>
									</div>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
									<ul>
										<li>ID: ${person.id}</li>
										<li>First Name: ${person.firstName}</li>
										<li>Middle Name: ${person.middleName}</li>
										<li>Last Name: ${person.lastName}</li>
										<li>Full Name: ${person.fullName}</li>
										<li>Title: ${person.title}</li>
										<li>BirthDate: ${person.birthDate}</li>
										<li>Gender: ${person.gender}</li>
										<li>Description: ${person.description}</li>
										<li>Flag: ${person.flag}</li>
										<li>Ethnicity ID: ${person.ethnicity.id }</li>
										<li>Ethnicity: ${person.ethnicity.name }</li>
										<li>Incidents: ${person.incidents.size() }</li>
										<li>Addresses: ${person.addresses.size() }</li>
										<li>Notes: ${person.notes.size() }</li>
									</ul>
									
								</div> <!-- end Card Body -->
							</div> <!-- end Card -->
									
							<!-- Incidents Card -->
							<jsp:include page="tables/incidents_table.jsp" />
							
							<!-- Addresses Card -->
							<jsp:include page="tables/addresses_table.jsp" />
							
							<!-- Notes Card -->
							<jsp:include page="tables/notes_table.jsp" />
									
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
			$("#addressesTable").DataTable({
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
			$("#noteTable").DataTable({
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