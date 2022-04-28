<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>User Profile</title>
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
							<h1 class="m-0">${sessionScope.loggedInUser.username }</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item"><a href="#">Users</a></li>
								<li class="breadcrumb-item active">${sessionScope.loggedInUser.username }</li>
							</ol>
						</div><!-- /.col -->
						
					</div><!-- /.row -->
				</div><!-- /.container-fluid -->
			</div> <!-- end Content Header (Page header) -->
			
			<h1>${sessionScope.loggedInUser }</h1>
			<hr />
			<h1>${sessionScope.loggedInUser.person }</h1>
			
		</div> <!-- end Content Wrapper. Contains page content -->
		
		<jsp:include page="generic/footer.jsp" />
	</div> <!-- end main div wrapper -->

	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
</body>
</html>