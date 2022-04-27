<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>${department.name }</title>
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
							<h1 class="m-0">${department.name }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">${department.name }</li>
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
		    							${department.name }
		    						</h3>
    						
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
	    							<ul>
										<li>ID: ${department.id }</li>
										<li>Name: ${department.name }</li>
										<li>Address: 
											<a href="address.do?id=${department.address.id }">
												${department.address.getFormattedAddress() }
											</a>
										</li>
									</ul>
									
									<!-- Incident Stuff -->
									<br />
									<hr />
									<h3>Incidents</h3>
									${department.incidents }
									
									<!-- Officer Stuff -->
									<br />
									<hr />
									<h3>Officers</h3>
									${department.officers }
	
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
		document.getElementById("nav-departments").className = "nav-item active";
	</script>
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
	
</body>
</html>