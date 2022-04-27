<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Officer Profile</title>
</head>
<body class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
	
	<!-- main div wrapper -->
	<div class="wrapper">
	
		<!-- Preloader -->
		<div class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__wobble" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
		</div>
		
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
							<h1 class="m-0">${sessionScope.userOfficer.person.fullName }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Officers</a></li>
								<li class="breadcrumb-item active">${sessionScope.userOfficer.person.fullName }</li>
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
		    							Badge #${sessionScope.userOfficer.badge }
		    							<c:if test="${not empty sessionScope.userOfficer.supervisor }">
		    								Supervisor: 
		    								<a href="officer.do?id=${sessionScope.userOfficer.supervisor.id }">
		    									${sessionScope.userOfficer.supervisor.person.fullName }
		   									</a>
		    							</c:if>
		    						</h3>
    						
	    						<!-- <div class="card-tools">
	    								<button type="button" class="btn btn-tool" data-card-widget="collapse">
					                		<i class="fas fa-minus"></i>
					              		</button>
	    							</div> -->
    							
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							<table id="table1" class="table table-bordered table-hover">
	    								<thead>
	    									<tr>
	    										<th>Head</th>
	    										<th>Head</th>
	    										<th>Head</th>
	    										<th>Head</th>
	    										<th>Head</th>
	    										<th>Head</th>
	   										</tr>
	 									</thead>
	 									<tbody>
	 										<tr>
	 											<td>Entry</td>
	 											<td>Entry</td>
	 											<td>Entry</td>
	 											<td>Entry</td>
	 											<td>Entry</td>
	 											<td>Entry</td>
	 										</tr>
	 									</tbody>
									</table>
									
	    						
	    						</div> <!-- end card-body -->
	    					</div> <!-- end Card -->
	    				</div> <!-- end col-12 -->
	    			</div> <!-- end row -->
	   			</div> <!-- end container-fluid -->
    		</section>
			
			
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
	</div> <!-- end main div wrapper -->
	
	<!-- TABLE SCRIPTS -->
	<jsp:include page="tables/table_scripts.jsp" />
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
</body>
</html>