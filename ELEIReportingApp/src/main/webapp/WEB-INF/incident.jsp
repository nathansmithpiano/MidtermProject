<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Incident</title>
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
							<h1 class="m-0">Incident #${incident.id }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active"><a href="#">Incident #${incident.id }</a></li>
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
		    							Incident #${incident.id }
		    						</h3>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
	    							<h1>Incident</h1>

									<ul>
										<li>ID: ${incident.id}</li>
										<li>Department: 
											<a href="department.do?id=${incident.department.id }">
												${incident.department.name }
											</a>
										</li>
										<li>Officer: 
											<a href="officer.do?id=${incident.officer.id }">
												${incident.officer.person.fullName } (Badge ${incident.officer.badge })
											</a>
										</li>
										<li>Reason for contact: ${incident.reasonForContact}</li>
										<li>Location: ${incident.location}</li>
										<li>Description: ${incident.description}</li>
										<li>Flag: ${incident.flag}</li>
										<li>Incident Date: ${incident.incidentDate}</li>
										<li>Address: 
											<a href="address.do?id=${incident.address.id }">
												${incident.address.getFormattedAddress() }
											</a>
										</li>
										<li>CaseFile:
											<c:choose>
												<c:when test="${empty incident.caseFile }">
													No CaseFile Assigned
												</c:when>
												<c:otherwise>
													<a href="casefile.do?id=${incident.caseFile.id }">
														Case #${incident.caseFile.caseNumber }
													</a>
												</c:otherwise>
											</c:choose>
										</li>
										<li>Persons: ${incident.persons.size() }</li>
										<li>Notes: ${incident.notes.size()}</li>
									</ul>
									
									<!-- Person Stuff -->
									<p>${incident.persons }</p>
									<br />
									<hr />
									<!-- Note Stuff -->
									<p>${incident.notes }</p>
									
	    						
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