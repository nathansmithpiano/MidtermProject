<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="generic/head.jsp" />
	<title>ELEI Reporting App | Log in</title>
</head>

<body class="hold-transition login-page">

	<!-- Login Box -->
	<div class="login-box">
		<!-- Card -->
		<div class="card card-outline card-primary">
		
			<!-- Header -->
			<div class="card-header text-center">
				<b>ELEI</b>ReportingApp
			</div> <!-- end Header -->
			
			<!-- Card Body -->
			<div class="card-body">
				<p class="login-box-msg">You must login to access this resource</p>
				
				<!-- Login Form -->
				<form action="tlogin.do" method="post">
				
					<!-- Username -->
					<div class="input-group mb-3">
						<input type="text" name="username" class="form-control" placeholder="Username" />
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fa-solid fa-user"></span>
							</div>
						</div>
					</div> <!-- end Username -->
					
					<!-- Password -->
					<div class="input-group mb-3">
						<input type="password" name="password" class="form-control" placeholder="Password" />
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fa-solid fa-lock"></span>
							</div>
						</div>
					</div> <!-- end Password -->
					
					<!-- Button Row -->
					<div class="row">
						<div class="col-12">
							<span class="description-text">
								 <button type="submit" class="btn btn-primary btn-block">
								 	Login
							 	</button>
						 	</span>
						</div>
					</div> <!-- end Button Row -->
					
					<p class="login-box-msg"><br>To request access, please contact your administrator.</p>
					
				</form> <!-- end Login Form -->
				
			</div> <!-- end Card Body -->
			
		</div> <!-- end Card -->
		
	</div> <!-- end Login Box -->
	
</body>