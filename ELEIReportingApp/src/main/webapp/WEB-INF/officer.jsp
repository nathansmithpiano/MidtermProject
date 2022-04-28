<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>${officer.person.fullName } (Badge #${officer.badge })</title>
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
							<h1 class="m-0">Badge #${officer.badge }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item">
									<a href="departmentOfficers.do">
										Department Officers
									</a>
								</li>
								<li class="breadcrumb-item active">
									Badge #${officer.badge }
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
		    							${officer.person.fullName } (Badge #${officer.badge })
		    							
		    							<c:if test="${not empty officer.supervisor }">
		    								Supervisor: 
		    								<a href="officer.do?id=${officer.supervisor.id }">
		    									${officer.supervisor.person.fullName }
		   									</a>
		    							</c:if>
		    						</h3>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	    							
									<ul>
										<li>Name: 
											<a href="person.do?id=${officer.person.id }">
												${officer.person.fullName }
											</a>
										</li>
										<li>Supervisor: 
											<a href="person.do?id=${officer.supervisor.id }">
												${officer.supervisor.person.fullName }
											</a>
										</li>
										<li>Subordinates: 
											<ul>
												<c:forEach var="subordinate" items="${officer.subordinates }">
													<li>
														<a href="officer.do?id=${subordinate.id }">
															${subordinate.person.fullName } (Badge ${subordinate.badge })
														</a>
													</li>
												</c:forEach>
											</ul>
										</li>
										<li>Badge: ${officer.badge }</li>
										<li>Image URL: ${officer.imageUrl }</li>
									</ul>
									
								</div> <!-- end Card Body -->
							</div> <!-- end Card -->
									
							<!-- Departments Card -->
							<jsp:include page="tables/departments_table.jsp" />
									
							<!-- Incidents Card -->
							<jsp:include page="tables/incidents_table.jsp" />
	    						
	    				</div> <!-- end col-12 -->
	    			</div> <!-- end row -->
	   			</div> <!-- end container-fluid -->
    		</section>
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
	</div> <!-- end main div wrapper -->
	
	<!-- Changing sidebar active page -->
	<script>
		if (${officer.id } == ${sessionScope.userOfficer.id }) {
			document.getElementById("nav-quick-officer-profile").className = "nav-link active";
		}
		
	</script>
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
	
	<!-- TABLE SCRIPTS -->
	<jsp:include page="tables/table_scripts.jsp" />
	
	<!-- SPECIFIC TABLE FEATURES -->
	<script>
		$(function () {
			$("#departmentTable").DataTable({
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
	</script>
</body>
</html>