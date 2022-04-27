<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="generic/head.jsp" />
<title>Add Incident</title>
</head>
<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">

	<!-- main div wrapper -->
	<div class="wrapper">

		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__wobble" src="dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
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
							<h1 class="m-0">Create Incident</h1>
						</div>
						<!-- /.col -->

						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Incidents</a></li>
								<li class="breadcrumb-item active">Add Incident</li>
							</ol>
						</div>
						<!-- /.col -->

					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- end Content Header (Page header) -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">

								<!-- Card header -->
								<div class="card-header">
									<h3 class="card-title">Add New Incident</h3>

								</div>
								<!-- end Card header -->

								<div class="card-body">
								
									<form action="createIncident.do" method="post">
									
										<label for="departmentId">Department:</label>
										<select id="departmentId" name="departmentId" required>
											<c:forEach var="department" items="${sessionScope.userOfficer.departments }">
												<option value="${department.id }">${department.name }</option>
											</c:forEach>
										</select>
										<br>
										<label for="reasonForContact">Reason for contact:</label> 
										<input type="text" name="reasonForContact">
										<br> 
										<label for="location">Location:</label>
										<input type="text" name="location">
										<br>
										<label for="description">Description:</label>
										<br>
										<textarea name="description" rows="10" cols="30">
										</textarea>
										<br>
										<input class="btn btn-primary" type="hidden" id="selectedAddress">
										<input type="hidden" id="addressId" name="addressId">
										<br>
										<input type="submit" value="Submit">
									</form>

								</div>
								<!-- end card-body -->

								<!-- ADDRESS -->
								<div class="card-header">
									<h3 class="card-title">Select address to attach to incident</h3>
								</div>
								
								<div class="card-body">
									<jsp:include page="tables/incident_add_address_table.jsp" />
								</div>
								<!-- end ADDRESS -->
								
								<!-- PERSON -->
								<div class="card-header">
									<h3 class="card-title">Select persons to add to incident</h3>
								</div>
								
								<div class="card-body">
									<jsp:include page="tables/incident_add_person_table.jsp" />
								</div>
								<!-- end ADDRESS -->

							</div>
							<!-- end Card -->
						</div>
						<!-- end col-12 -->
					</div>
					<!-- end row -->
				</div>
				<!-- end container-fluid -->
			</section>


		</div>
		<!-- end Content Wrapper. Contains page content -->

		<jsp:include page="generic/footer.jsp" />
	</div>
	<!-- end main div wrapper -->

	<!-- Changing sidebar active page -->
	<script>
		document.getElementById("nav-incidents").className = "nav-item menu-open";
		document.getElementById("nav-incidents-add").className = "nav-link active";
	</script>

	<!-- REQUIRED SCRIPTS -->
	<jsp:include page="generic/required_scripts.jsp" />
	
	<!-- TABLE SCRIPTS -->
	<jsp:include page="tables/table_scripts.jsp" />
	
	<!-- SPECIFIC TABLE FEATURES -->
	<script>
		$(function () {
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
			
		});
	</script>
	
	<script>
	<!-- ADDRESS SELECTION STUFF -->
		function addAddressRowHandlers() {
		    var table = document.getElementById("addressesTable");
		    var rows = table.getElementsByTagName("tr");
		    for (i = 0; i < rows.length; i++) {
		        var currentRow = table.rows[i];
		        var createClickHandler = 
		            function(row) 
		            {
		                return function() { 
		                                        var cell = row.getElementsByTagName("td")[0];
		                                        var id = cell.innerHTML;
		                                        
		                                        var addressTextField = document.getElementById('selectedAddress');
		                                        /* put something into selectedAddress button */
		                                        addressTextField.value = row.getElementsByTagName("td")[2].innerHTML;
		                                        
		                                        /* hide/show toggle */
		                                        var inputType = addressTextField.type;
		                                        
	                                        	if (inputType == 'hidden') {
		                                        	addressTextField.type = 'button';
		                                        }
	                                        	
	                                        	//set addressId to addressId in form
	                                        	document.getElementById('addressId').value = id;
		                                        
		                                 };
		            };
		
		        currentRow.onclick = createClickHandler(currentRow);
		    }
		}
	<!-- PERSON SELECTION STUFF -->
		function addPersonRowHandlers() {
		    var table = document.getElementById("personsTable");
		    var rows = table.getElementsByTagName("tr");
		    for (i = 0; i < rows.length; i++) {
		        var currentRow = table.rows[i];
		        var createClickHandler = 
		            function(row) 
		            {
		                return function() { 
		                                        /* var cell = row.getElementsByTagName("td")[0];
		                                        var id = cell.innerHTML;
		                                        
		                                        var addressTextField = document.getElementById('selectedAddress');
		                                        put something into selectedAddress button
		                                        addressTextField.value = row.getElementsByTagName("td")[2].innerHTML;
		                                        
		                                        hide/show toggle
		                                        var inputType = addressTextField.type;
		                                        
	                                        	if (inputType == 'hidden') {
		                                        	addressTextField.type = 'button';
		                                        }
	                                        	
	                                        	set addressId to addressId in form
	                                        	document.getElementById('addressId').value = id; */
		                                        
		                                 };
		            };
		
		        currentRow.onclick = createClickHandler(currentRow);
		    }
		}
		window.onload = addAddressRowHandlers();
		window.onload = addPersonRowHandlers();
	</script>
	
</body>
</html>