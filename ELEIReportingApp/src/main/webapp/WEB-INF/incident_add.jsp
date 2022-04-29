<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="generic/head.jsp" />
<title>Add Incident</title>
<link rel="stylesheet" href="main.css">
</head>
<body
	class="hold-transition dark-mode sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">

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

								<div class="card-body">

									<form action="createIncident.do" method="post">
										<div class="form-group">
										
											<h3>Incident Details</h3>
											<br>

											<label for="departmentId">Department:</label>
											<select class="form-control" id="departmentId" name="departmentId" required>
												<c:forEach var="department" items="${sessionScope.userOfficer.departments }">
													<option value="${department.id }">${department.name }</option>
												</c:forEach>
											</select> <br> <label for="reasonForContact">Reason for contact:</label>
											
											<input class="form-control" type="text" name="reasonForContact">
											<br>
											
											<label for="location">Location:</label>
											<input class="form-control" type="text" name="location">
											<br>
											
											<label for="description">Description:</label>
											<br> 
											
											<textarea class="form-control" name="description" rows="10" cols="30"></textarea>
											<br> 
											
											<input type="hidden" id="addressId" name="addressId">
											<input type="hidden" id="personIdHidden" name="personId">

											<!-- New Person Stuff -->
											<input type="hidden" id="firstNameHidden" name="firstName">
											<input type="hidden" id="middleNameHidden" name="middleName">
											<input type="hidden" id="lastNameHidden" name="lastName">
											<input type="hidden" id="titleHidden" name="title">
											<input type="hidden" id="birthHidden" name="birth">
											<input type="hidden" id="genderHidden" name="gender">
											<input type="hidden" id="ethnicityNameHidden" name="ethnicityName">
											<input type="hidden" id="personDescriptionHidden" name="personDescription">
											
											<input type="hidden" id="ageMinimumHidden" name="ageMinimum">
											<input type="hidden" id="ageMaximumHidden" name="ageMaximum">
											<input type="hidden" id="suspectedCrimeHidden" name="suspectedCrime">
											<input type="hidden" id="incidentPersonDescriptionHidden" name="incidentPersonDescription">
											<hr />
											<div class="form-group">
												<h3>Person Details</h3>
												<br>
												<label for="ageMinimumForm">Person Age Min:</label>
												<input class="form-control" type="number" id="ageMinimumForm" name="ageMinimumForm" min="1" max="125" required>
												<div id="ageMinRequired"><code>Required, between 1-125</code></div>
											</div>
											<div class="form-group">
												<label for="ageMaximumForm">Person Age Max:</label>
												<input class="form-control" type="number" id="ageMaximumForm" name="ageMaximumForm" min="1" max="125" required>
												<div id="ageMaxRequired"><code>Required, between 1-125</code></div>
											</div>
												<label for="suspectedCrimeForm">Person Suspected Crime:</label>
												<input class="form-control" type="text" id="suspectedCrimeForm" name="suspectedCrimeForm">
											<div class="form-group">
												<br>
												<label for="incidentPersonDescriptionForm">Description of Person:</label>
												<textarea class="form-control" name="incidentPersonDescriptionForm" id="incidentPersonDescriptionForm" rows="10" cols="30"></textarea>
											</div>
											<!-- IncidentPerson stuff -->
											<div id="selectedPersonDiv">
												<label id="selectedPersonLabel" for="selectedPerson">Selected Person:</label>
												<input class="btn btn-success btn-block" type="button" id="selectedPerson">
											</div>
											<br>
											<div id="selectedAddressDiv">
												<label id="selectedAddressLabel" for="selectedAddress">Selected Address:</label> 
												<input class="btn btn-success btn-block" type="button" id="selectedAddress">
											</div>
											<br>
											<div class="container-fluid">
												<div class="row">
													<input class="btn btn-dark btn-lg col-lg" type="button" onclick="clickNewPersonButton()" id="newPersonButton" value="Create New Person" />
													<input class="btn btn-dark btn-lg col-lg" type="button" onclick="clickPersonTableButton()" id="choosePersonFromList" value="Select Person From List" />
													<input class="btn btn-dark btn-lg col-lg" type="button" onclick="clickAddressTableButton()" id="chooseAddressFromList" value="Select Address From List" />
												</div>
											</div>
											
											<br>
											<button type="button" class="btn btn-primary" id="verifyButton" onclick="verify()">Check before adding</button>
											<input class="btn btn-primary btn-lg" type="hidden" id="submitButton" value="Submit">
										</div>
									</form>
									
									
									<!-- ADDRESS TABLE -->
									<div id="incident-add-address-table-div">
										<h3>Select address to attach to incident</h3>
										<jsp:include page="tables/incident_add_address_table.jsp" />
									</div> <!-- end ADDRESS TABLE -->
									
									<!-- PERSON TABLE -->
									<div id="incident-add-person-table-div">
										<h3>Select persons to add to incident</h3>
										<jsp:include page="tables/incident_add_person_table.jsp" />
									</div> <!-- end PERSON TABLE -->
									
									<!-- NEW PERSON -->
									<div id="incident-new-person">
										<h3>Add a new person</h3>
										<form id="incident-new-person-form">
											<jsp:include page="generic/person_add_form.jsp" />
											<br>
											<input class="btn btn-primary btn-lg" type="button" onclick="clickNewPersonFormButton()" id="newPersonFormButton" value="Add Person">
										</form>
									</div> <!-- end NEW PERSON -->
									
									
								</div> <!-- end card-body -->
							</div> <!-- end card -->

					</div> <!-- end col-12 -->
				</div> <!-- end row -->
			</div> <!-- end container-fluid -->
		</section>

	</div>
	<!-- end Content Wrapper. Contains page content -->

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
		$(function() {
			$("#addressesTable").DataTable({
				"paging" : true,
				"searching" : true,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
				"lengthChange" : false,
				"autoWidth" : false
			});

		});
		$(function() {
			$("#personsTable").DataTable({
				"paging" : true,
				"searching" : true,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
				"lengthChange" : false,
				"autoWidth" : false
			});

		});
	</script>

	<!--  Verify before allowing submissions -->
	<script>
		function verify() {
			if (document.getElementById("personIdHidden").value == ""
					|| document.getElementById("personIdHidden").value == null
					|| document.getElementById("addressId").value == ""
					|| document.getElementById("addressId").value == null) {
				alert("person and address are required");
			} else {
				document.getElementById("verifyButton").style.display = "none";
				document.getElementById("submitButton").type = "submit";
			}
		}
	</script>

	<!-- ADDRESS SELECTION STUFF -->
	<script>
		function addAddressRowHandlers() {
			var table = document.getElementById("addressesTable");
			var rows = table.getElementsByTagName("tr");
			for (i = 0; i < rows.length; i++) {
				var currentRow = table.rows[i];
				var createClickHandler = function(row) {
					return function() {
						var cell = row.getElementsByTagName("td")[0];
						var id = cell.innerHTML;

						var addressButton = document
								.getElementById('selectedAddress');
						/* put something into selectedAddress button */
						addressButton.value = row.getElementsByTagName("td")[2].innerHTML;

						/* hide/show toggle */
						var inputType = addressButton.type;

						if (inputType == 'hidden') {
							addressButton.type = 'button';
						}

						//set addressId to addressId in form
						document.getElementById('addressId').value = id;
						
						//click on address row, show button and label
						var addressDiv = document.getElementById('selectedAddressDiv');
						addressDiv.style.display = "block";
						
					};
				};

				currentRow.onclick = createClickHandler(currentRow);
			}
		}
		function addPersonRowHandlers() {
			var table = document.getElementById("personsTable");
			var rows = table.getElementsByTagName("tr");
			for (i = 0; i < rows.length; i++) {
				var currentRow = table.rows[i];
				var createClickHandler = function(row) {
					return function() {
						var cell = row.getElementsByTagName("td")[0];
						var id = cell.innerHTML;

						var personDiv = document
								.getElementById('incident-person-form');
						/* put something into selectedPerson button */

						//format full name for display only
						var fullName = "";
						if (row.getElementsByTagName("td")[1].innerHTML != "") {
							fullName += row.getElementsByTagName("td")[1].innerHTML;
						}
						if (row.getElementsByTagName("td")[2].innerHTML != "") {
							if (fullName != "") {
								fullName += " ";
							}
							fullName += row.getElementsByTagName("td")[2].innerHTML;
						}
						if (row.getElementsByTagName("td")[3].innerHTML != "") {
							if (fullName != "") {
								fullName += " ";
							}
							fullName += row.getElementsByTagName("td")[3].innerHTML;
						}
						var personButton = document
								.getElementById('selectedPerson');
						personButton.value = fullName;

						
						
						//set personId to personId in form
						document.getElementById('personIdHidden').value = id;

						//set ethnicityName to hidden field
						document.getElementById("ethnicityNameHidden").value = "${personList.get(id).getEthnicity().getName() }";

						//set ethnicityName to hidden field
						document.getElementById("birthHidden").value = "${personList.get(id).getBirthDate().toString() }";
						
						//ageMin ageMax stuff
						if (row.getElementsByTagName("td")[5].innerHTML != "") {
							var birthYearString = row.getElementsByTagName("td")[5].innerHTML.substring(0, 4);
							var birthYear = parseInt(birthYearString);
							var currentYear = new Date().getFullYear();
							var age = currentYear - birthYear;
							document.getElementById("ageMinimumHidden").value = age;
							document.getElementById("ageMinimumForm").value = age;
							document.getElementById("ageMaximumHidden").value = age;
							document.getElementById("ageMaximumForm").value = age;
						}
						
						/* display once clicked */
						var personDiv = document.getElementById('selectedPersonDiv');
						personDiv.style.display = "block";
					};
				};

				currentRow.onclick = createClickHandler(currentRow);
			}
		}

		function clickNewPersonFormButton() {

			if (document.getElementById("ethnicityName").value == "") {
				document.getElementById('ethnicityNameRequired').style.display = "block";
			} else {
				document.getElementById('ethnicityNameRequired').style.display = "none";
			}

			if (document.getElementById("gender").value == "") {
				document.getElementById('genderRequired').style.display = "block";
			} else {
				document.getElementById('genderRequired').style.display = "none";
			}
			
			if (document.getElementById("ageMinimumForm").value == "" || 
					document.getElementById("ageMinimumForm").value < 1 ||
					document.getElementById("ageMinimumForm").value > 125) {
				document.getElementById('ageMinRequired').style.display = "block";
			} else {
				document.getElementById('ageMinRequired').style.display = "none";
			}
			
			if (document.getElementById("ageMaximumForm").value == "" ||
					document.getElementById("ageMaximumForm").value < 1 ||
					document.getElementById("ageMaximumForm").value > 125) {
				document.getElementById('ageMaxRequired').style.display = "block";
			} else {
				document.getElementById('ageMaxRequired').style.display = "none";
			}
			
			if (document.getElementById("gender").value != ""
					&& document.getElementById("ethnicityName").value != ""
					&& document.getElementById("ageMinimumForm").value != ""
					&& document.getElementById("ageMinimumForm").value > 0
					&& document.getElementById("ageMinimumForm").value <= 125
					&& document.getElementById("ageMaximumForm").value > 0
					&& document.getElementById("ageMaximumForm").value <= 125
					&& document.getElementById("ageMaximumForm").value != "") {

				// set full name and add to top
				var fullName = "";
				if (document.getElementById("firstName").value != "") {
					fullName += document.getElementById("firstName").value;
				}
				if (document.getElementById("middleName").value != "") {
					if (fullName != "") {
						fullName += " ";
					}
					fullName += document.getElementById("middleName").value;
				}
				if (document.getElementById("lastName").value != "") {
					if (fullName != "") {
						fullName += " ";
					}
					fullName += document.getElementById("lastName").value;
				}
				var personButton = document.getElementById('selectedPerson');

				if (fullName == "") {
					personButton.value = "No name provided";
				} else {
					personButton.value = fullName;
				}
				
				document.getElementById("personIdHidden").value = "0";
				if (document.getElementById("firstName").value != null) {
					document.getElementById("firstNameHidden").value = document.getElementById("firstName").value;
				}
				if (document.getElementById("middleName").value != null) {
					document.getElementById("middleNameHidden").value = document.getElementById("middleName").value;
				}
				if (document.getElementById("lastName").value != null) {
					document.getElementById("lastNameHidden").value = document.getElementById("lastName").value;
				}
				if (document.getElementById("title").value != null) {
					document.getElementById("titleHidden").value = document	.getElementById("title").value;
				}
				if (document.getElementById("birth").value != null) {
					document.getElementById("birthHidden").value = document.getElementById("birth").value;
				}
				if (document.getElementById("ethnicityName").value != null) {
					document.getElementById("ethnicityNameHidden").value = document.getElementById("ethnicityName").value;
				}
				if (document.getElementById("gender").value != null) {
					document.getElementById("genderHidden").value = document.getElementById("gender").value;
				}
				if (document.getElementById("personDescription").value != null) {
					document.getElementById("personDescriptionHidden").value = document.getElementById("personDescription").value;
				}
				if (document.getElementById("ageMinimumForm").value != null) {
					document.getElementById("ageMinimumHidden").value = document.getElementById("ageMinimumForm").value;
				}
				if (document.getElementById("ageMaximumForm").value != null) {
					document.getElementById("ageMaximumHidden").value = document.getElementById("ageMaximumForm").value;
				}
				if (document.getElementById("suspectedCrimeForm").value != null) {
					document.getElementById("suspectedCrimeHidden").value = document.getElementById("suspectedCrimeForm").value;
				}
				if (document.getElementById("incidentPersonDescriptionForm").value != null) {
					document.getElementById("incidentPersonDescriptionHidden").value = document.getElementById("incidentPersonDescriptionForm").value;
				}
				
				/* display once clicked */
				var personDiv = document.getElementById('selectedPersonDiv')
				personDiv.style.display = "block";

				hideAll();
			}
		}

		function clickPersonTableButton() {
			hideAll();
			document.getElementById('incident-add-person-table-div').style.display = "block";
		}

		function clickAddressTableButton() {
			hideAll();
			document.getElementById('incident-add-address-table-div').style.display = "block";
		}

		function clickNewPersonButton() {
			hideAll();
			document.getElementById('incident-new-person').style.display = "block";
		}

		function hideAll() {
			document.getElementById('ageMinRequired').style.display = "none";
			document.getElementById('ageMaxRequired').style.display = "none";
			document.getElementById('ethnicityNameRequired').style.display = "none";
			document.getElementById('genderRequired').style.display = "none";
			document.getElementById('incident-add-person-table-div').style.display = "none";
			document.getElementById('incident-add-address-table-div').style.display = "none";
			document.getElementById('incident-new-person').style.display = "none";
		}
		
		var addressDiv = document.getElementById('selectedAddressDiv');
		addressDiv.style.display = "none";
		var personDiv = document.getElementById('selectedPersonDiv');
		personDiv.style.display = "none";

		window.onload = addAddressRowHandlers();
		window.onload = addPersonRowHandlers();
	</script>

</body>
</html>