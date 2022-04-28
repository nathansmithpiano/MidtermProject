<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Incidents</title>
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
							<h1 class="m-0">
								<c:choose>
									<c:when test="${level == 1}">
										My Incidents
									</c:when>
									<c:when test="${level == 2 }">
										Incidents at ${sessionScope.userOfficer.departments.size() } Departments
									</c:when>
									<c:otherwise>
										Officer Incidents
									</c:otherwise>
								</c:choose>
							</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="home.do">
										Home
									</a>
								</li>
								<li class="breadcrumb-item active">
									<c:choose>
										<c:when test="${level == 1}">
											My Incidents
										</c:when>
										<c:when test="${level == 2 }">
											All incidents
										</c:when>
										<c:otherwise>
											Officer Incidents
										</c:otherwise>
									</c:choose>
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
    				
	    						<div class="card-body">
	    							<jsp:include page="tables/incidents_table.jsp" />
	    						</div> <!-- end card-body -->
								
	    					</div> <!-- end Card -->
	    				</div> <!-- end col-12 -->
	    			</div> <!-- end row -->
	   			</div> <!-- end container-fluid -->
    		</section>
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
	</div> <!-- end main div wrapper -->
	
	<!-- Changing sidebar active page -->
	<script>
		document.getElementById("nav-incidents").className = "nav-item menu-open";
		if(${level} == 1) {
			document.getElementById("nav-incidents-my").className = "nav-link active";
		}
		if(${level} == 2) {
			document.getElementById("nav-incidents-all").className = "nav-link active";
		}
		
	</script>
	
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
	</script>
	
</body>
</html>