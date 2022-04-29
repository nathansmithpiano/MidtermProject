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

								<!-- Card header -->
								<div class="card-header">
									<h3 class="card-title">Add New Incident</h3>

								</div>
								<!-- end Card header -->

								<div class="card-body">

									<form action="createIncident.do" method="post">
										<div class="form-group">

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
											
											<label for="selectedAddress">Selected Address:</label> 
											<input class="btn btn-success btn-block" type="hidden" id="selectedAddress">
											<br> 
											
											<div id="incident-person-form">
												<label for="selectedPerson">Selected Person:</label>
											
												<input class="btn btn-success btn-block" type="button" id="selectedPerson">
											</div>
											<br>
											
											<input type="hidden" id="addressId" name="addressId" placeholder="addressId" required />
											<input type="hidden" id="personIdHidden" name="personId" placeholder="personId" required />
											<input class="btn btn-dark col-2" type="button" onclick="clickNewPersonButton()" id="newPersonButton" value="Create New Person" />
											<input class="btn btn-dark col-2" type="button" onclick="clickPersonTableButton()" id="choosePersonFromList" value="Select Person From List" />
											<!-- <input type="button" onclick="clickNewAddressButton()" id="newAddressButton" value="Create New Address" /> -->
											<input class="btn btn-dark col-2" type="button" onclick="clickAddressTableButton()" id="chooseAddressFromList" value="Select Address From List" />

											<input type="hidden" id="addressId" name="addressId">
											<input type="hidden" id="personIdHidden" name="personId">

											<div id="incident-person-form">
												 <input type="hidden"
													id="firstNameHidden" name="firstName"> <input
													type="hidden" id="middleNameHidden" name="middleName">
												<input type="hidden" id="lastNameHidden" name="lastName">
												<input type="hidden" id="titleHidden" name="title">
												<input type="hidden" id="birthHidden" name="birth">
												<input type="hidden" id="genderHidden" name="gender">
												<input type="hidden" id="ethnicityNameHidden"
													name="ethnicityName"> <input type="hidden"
													id="personDescriptionHidden" name="personDescription">
												<label for="ageMinimum">Age Min:</label>
												<input type="number" id="ageMinimum" name="ageMinimum" min="1" max="125" value="0" required>
												<label for="ageMaximum">Age Max:</label>
												<input type="number" id="ageMaximum" name="ageMaximum" min="1" max="125" value="0" required>
												<label for="suspectedCrime">Suspected Crime:</label>
												<input type="text" id="suspectedCrime" name="suspectedCrime">
												<label for="incidentPersonDescription">Description:</label>
												<textarea name="incidentPersonDescription" rows="10" cols="30"></textarea>
											</div>
											<br>
											<br>
											<button type="button" class="btn btn-primary"
												id="verifyButton" onclick="verify()">Check before
												adding</button>
											<input type="hidden" id="submitButton" value="Submit">
										</div>
									</form>
								</div> <!-- end card-body -->
							</div> <!-- end card -->

							<!-- ADDRESS TABLE -->
							<div id="incident-add-address-table-div">
								<div class="card-header">
									<h3 class="card-title">Select address to attach to
										incident</h3>
								</div>

								<div class="card-body">
									<jsp:include page="tables/incident_add_address_table.jsp" />
								</div>
							</div>
							<!-- end ADDRESS TABLE -->

							<!-- PERSON TABLE -->
							<div id="incident-add-person-table-div">
								<div class="card-header">
									<h3 class="card-title">Select persons to add to incident</h3>
								</div>

								<div class="card-body">
									<jsp:include page="tables/incident_add_person_table.jsp" />
								</div>
							</div>
							<!-- end PERSON TABLE -->

							<!-- NEW PERSON -->
							<div id="incident-new-person">
								<div class="card-header">
									<h3 class="card-title">Add a new person</h3>
								</div>

								<div class="card-body">
									<form>
										<jsp:include page="generic/person_add_form.jsp" />
										<input type="button" onclick="clickNewPersonFormButton()"
											id="newPersonFormButton" value="Add Person">
									</form>
								</div>
							</div>
							<!-- end NEW PERSON -->
							<div id="incident-new-address">
								<div class="card-header">
									<h3 class="card-title">Create a new address</h3>
								</div>

								<div class="card-body">
									<p>Hello World</p>
								</div>
							</div>

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

						/* display personDiv once clicked */
						personDiv.style.display = "block";

						//set personId to personId in form
						document.getElementById('personIdHidden').value = id;

						//set ethnicityName to hidden field
						document.getElementById("ethnicityNameHidden").value = "${personList.get(id).getEthnicity().getName() }";

						//set ethnicityName to hidden field
						document.getElementById("birthHidden").value = "${personList.get(id).getBirthDate().toString() }";
					};
				};

				currentRow.onclick = createClickHandler(currentRow);
			}
		}

		function clickNewPersonFormButton() {

			if (document.getElementById("ethnicityName").value == "") {
				document.getElementById("ethnicityName").placeholder = "required";
			}

			if (document.getElementById("gender").value == "") {
				document.getElementById("gender").placeholder = "required";
			}

			if (document.getElementById("gender").value != ""
					&& document.getElementById("ethnicityName").value != "") {

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

				var personDiv = document.getElementById('incident-person-form');
				personDiv.style.display = "block";

				document.getElementById("personIdHidden").value = "0";
				if (document.getElementById("firstName").value != null) {
					document.getElementById("firstNameHidden").value = document
							.getElementById("firstName").value;
				}
				if (document.getElementById("middleName").value != null) {
					document.getElementById("middleNameHidden").value = document
							.getElementById("middleName").value;
				}
				if (document.getElementById("lastName").value != null) {
					document.getElementById("lastNameHidden").value = document
							.getElementById("lastName").value;
				}
				if (document.getElementById("title").value != null) {
					document.getElementById("titleHidden").value = document
							.getElementById("title").value;
				}
				if (document.getElementById("birth").value != null) {
					document.getElementById("birthHidden").value = document
							.getElementById("birth").value;
				}
				if (document.getElementById("ethnicityName").value != null) {
					document.getElementById("ethnicityNameHidden").value = document
							.getElementById("ethnicityName").value;
				}
				if (document.getElementById("gender").value != null) {
					document.getElementById("genderHidden").value = document
							.getElementById("gender").value;
				}
				if (document.getElementById("personDescription").value != null) {
					document.getElementById("personDescriptionHidden").value = document
							.getElementById("personDescription").value;
				}

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

		function clickNewAddressButton() {
			hideAll();
			document.getElementById('incident-new-address').style.display = "block";
		}

		function hideAll() {
			document.getElementById('incident-add-person-table-div').style.display = "none";
			document.getElementById('incident-add-address-table-div').style.display = "none";
			document.getElementById('incident-new-person').style.display = "none";
			document.getElementById('incident-new-address').style.display = "none";
		}

		/* var addressTableButton = document.getElementById("chooseAddressFromList");
		addressTableButton.onclick = clickAddressTableButton();
		var personTableButton = document.getElementById("choosePersonFromList");
		personTableButton.onclick = clickPersonTableButton(); */

		window.onload = addAddressRowHandlers();
		window.onload = addPersonRowHandlers();
	</script>

</body>
</html>