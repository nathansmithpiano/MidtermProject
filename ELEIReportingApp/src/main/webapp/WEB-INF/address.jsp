<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>${address.getFormattedAddress() }</title>
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
							<h1 class="m-0">${address.getFormattedAddress() }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="allAddresses.do">
										All Addresses
									</a>
								</li>
								<li class="breadcrumb-item active">
									${address.getFormattedAddress() }
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
		    							Address Info
		    						</h3>
		    						
		    						<!-- Buttons -->
		    						<div class="float-right">
			    						<div class="btn-group">
			    							<button type="button" class="btn btn-warning">Add Note</button>
										</div>
									</div>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
									<ul>
										<li>ID: ${address.id}</li>
										<li>Description: ${address.description}</li>
										<li>StreetOne: ${address.streetOne}</li>
										<li>StreetTwo: ${address.streetTwo}</li>
										<li>City: ${address.city}</li>
										<li>StateCode: ${address.stateCode}</li>
										<li>Zip: ${address.zip}</li>
										<li>Flag: ${address.flag}</li>
										<li>Incidents: ${address.incidents.size()}</li>
										<li>Persons: ${address.persons.size() }</li>
									</ul>
								</div> <!-- end Card Body -->
							</div> <!-- end Card -->
							
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
		/* $(function () {
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
		}); */
		/* $(function () {
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
		}); */
		/* $(function () {
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
		}); */
		/* $(function () {
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
		}); */
		/* $(function () {
			$("#officerTable").DataTable({
			"paging": true,
			"searching": true,
			"ordering": true,
			"info": true,
			"autoWidth": false,
			"responsive": true,
			"lengthChange": false,
			"autoWidth": false
			});
		}); */
	</script>
</body>
</html>