<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>Title Goes Here</title>
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
							<h1 class="m-0">person_add.jsp</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Root</a></li>
								<li class="breadcrumb-item active">Child</li>
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
		    							Add New Person
		    						</h3>
    						
    							</div> <!-- end Card header -->
    					
	    						<div class="card-body">
	
	<form action="addNewPerson.do" method="post">
	
		<label for="firstName">First Name:</label>
		<input type="text" name="firstName">
		<br>
		<label for="middleName">Middle Name:</label>
		<input type="text" name="middleName">
		
		<br>
		<label for="lastName">Last Name:</label>
		<input type="text" name="lastName">
		
		<br>
		<label for="title">Title:</label>
		<select id="title" name="title">
  			<option value="" selected></option>
  			<option value="Jr">Jr.</option>
  			<option value="Sr">Sr.</option>
  			<option value="I">I.</option>
  			<option value="II">II.</option>
  			<option value="III">III.</option>
		</select>
		
		<br>
		
  	<label for="birth">Birth Date:</label>
 	 <input type="date" id="birth" name="birth">
 	 
 	 <br>
 	 
  	 		<label for="ethnicityName">Ethnicity:</label>
 	 		<input list="ethnicityName" name="ethnicityName">
		<datalist id="ethnicityName">
  			<option value="White"/>
  			<option value="Black"/>
  			<option value="Hispanic"/>
  			<option value="Asian"/>
  			<option value="American Indian"/>
  			<option value="Pacific Islander"/>
  			<option value="Other"/>
		</datalist>
		
		<br>
 	 
 	 		<label for="gender">Gender:</label>
		<input type="text" name="gender" required>
		<br>
 	 		<label for="description">Description:</label>
		<textarea name="description" rows="10" cols="30">
</textarea>
		
		<br>
		<input type="submit" value="Submit">
		
	</form>
	
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
		document.getElementById("nav-persons").className = "nav-item menu-open";
		document.getElementById("nav-persons-add").className = "nav-link active";
	</script>
	
	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
	
</body>
</html>