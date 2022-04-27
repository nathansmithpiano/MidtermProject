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
							<h1 class="m-0">${person.fullName }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active"><a href="#">${person.fullName }</a></li>
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
		    							${person.fullName }
		    						</h3>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
	    							<h1>Person</h1>

									<ul>
										<li>ID: ${person.id}</li>
										<li>First Name: ${person.firstName}</li>
										<li>Middle Name: ${person.middleName}</li>
										<li>Last Name: ${person.lastName}</li>
										<li>Title: ${person.title}</li>
										<li>BirthDate: ${person.birthDate}</li>
										<li>Gender: ${person.gender}</li>
										<li>Description: ${person.description}</li>
										<li>Flag: ${person.flag}</li>
										<li>Ethnicity ID: ${person.ethnicity.id }</li>
										<li>Ethnicity: ${person.ethnicity.name }</li>
									</ul>
									
									<!-- Address Stuff -->
									<br />
									<hr />
									<h3>Addresses</h3>
									${person.addresses }
									
									<!-- Incident Stuff -->
									
									<!-- Note Stuff -->
									<br />
									<hr />
									<h3>Notes</h3>
									${person.notes }
									
	    						
	    						</div> <!-- end card-body -->
	    					</div> <!-- end Card -->
	    				</div> <!-- end col-12 -->
	    			</div> <!-- end row -->
	   			</div> <!-- end container-fluid -->
    		</section>
			
			
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
		
	</div> <!-- end main div wrapper -->
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
</body>
</html>