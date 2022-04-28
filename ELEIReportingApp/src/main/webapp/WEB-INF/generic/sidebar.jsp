<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">

	<!-- Brand Logo -->
	<a href="home.do" class="brand-link">
		<img src="images/SDProjectLogo.jpg" alt="SD Project Logo" class="brand-image img-circle" style="opacity: 1">
		<span class="brand-text font-weight-light">ELEIReportingApp</span>
	</a>
	
	<!-- Sidebar -->
	<div class="sidebar">
	
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="../images/police-cartoon-160x160.jpg" class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="officer.do?id=${sessionScope.userOfficer.id }" class="d-block">
					${sessionScope.userOfficer.person.fullName }
				</a>
			</div>
		</div> <!-- end Sidebar user panel (optional) -->
		
		<!-- SidebarSearch Form -->
		<div class="form-inline">
			<div class="input-group" data-widget="sidebar-search">
				<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-sidebar">
						<i class="fa-solid fa-search fa-fw"></i>
					</button>
				</div>
			</div>
		</div> <!-- end SidebarSearch Form -->
		
		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<!-- Sidebar main ul -->
			<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->
				
				<!-- Incident Options -->
				<li class="nav-item" id="nav-incidents">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-person-burst nav-icon"></i>
						<p>
							INCIDENTS
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officerIncidents.do?id=${sessionScope.userOfficer.id }" class="nav-link" id="nav-incidents-my">
								<i class="far fa-solid fa-file nav-icon"></i>
								<p>
									My Incidents
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="departmentIncidents.do?type=USER" class="nav-link" id="nav-incidents-all">
								<i class="far fa-solid fa-file-shield nav-icon"></i>
								<p>
									All Incidents
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="addIncident.do" class="nav-link" id="nav-incidents-add">
								<i class="far fa-solid fa-file-circle-plus nav-icon"></i>
								<p>
									Add Incident
									<i class="right fa-solid fa-plus"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Incident Options -->
				
				<!-- CaseFile Options -->
				<li class="nav-item" id="nav-casefiles">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-briefcase nav-icon"></i>
						<p>
							CASEFILES
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officerCaseFiles.do?officerId=${sessionScope.userOfficer.id }" class="nav-link" id="nav-casefiles-my">
								<i class="far fa-solid fa-location-dot nav-icon"></i>
								<p>
									My Casefiles
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="departmentCaseFiles.do" class="nav-link" id="nav-casefiles-all">
								<i class="far fa-solid fa-location-dot nav-icon"></i>
								<p>
									All Casefiles
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
					</ul>
					
				</li> <!-- end CaseFile Options -->
				
				<!-- Address Options -->
				<li class="nav-item" id="nav-addresses">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-map-location-dot nav-icon"></i>
						<p>
							ADDRESSES
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officerAddresses.do" class="nav-link" id="nav-addresses-my">
								<i class="far fa-solid fa-location-dot nav-icon"></i>
								<p>
									My Addresses
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="allAddresses.do" class="nav-link" id="nav-addresses-all">
								<i class="far fa-solid fa-map nav-icon"></i>
								<p>
									All Addresses
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Address Options -->
				
				<!-- Person Options -->
				<li class="nav-item" id="nav-persons">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-people-group nav-icon"></i>
						<p>
							PERSONS
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officerPersons.do" class="nav-link" id="nav-persons-my">
								<i class="far fa-solid fa-person-military-to-person nav-icon"></i>
								<p>
									My Persons
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="allPersons.do" class="nav-link" id="nav-persons-all">
								<i class="far fa-solid fa-users-between-lines nav-icon"></i>
								<p>
									All Persons
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="addPerson.do" class="nav-link" id="nav-persons-add">
								<i class="far fa-solid fa-person-circle-plus nav-icon"></i>
								<p>
									Add Person
									<i class="right fa-solid fa-plus"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Person Options -->
				
				<!-- Departments Options -->
				<li class="nav-item" id="nav-departments">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-building-shield nav-icon"></i>
						<p>
							DEPARTMENTS
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officerDepartments.do?type=USER" class="nav-link" id="nav-departments-my">
								<i class="far fa-solid fa-building-circle-arrow-right nav-icon"></i>
								<p>
									My Departments
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="departmentOfficers.do" class="nav-link" id="nav-departments-all-officers">
								<i class="far fa-solid fa-building-user nav-icon"></i>
								<p>
									All Officers
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Department Options -->
				
					
				<!-- Quick Links -->
				<li class="nav-header">QUICK LINKS</li>
				<li class="nav-item">
					<a href="addIncident.do" class="nav-link"  id="nav-quick-add-incident">
						<i class="far fa-solid fa-file-circle-plus  nav-icon"></i>
						<p>
							Add Incident
							<i class="right fa-solid fa-plus"></i>
						</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="addPerson.do" class="nav-link" id="nav-quick-add-person">
						<i class="far fa-solid fa-person-circle-plus nav-icon"></i>
						<p>
							Add Person
							<i class="right fa-solid fa-plus"></i>
						</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="officer.do?id=${sessionScope.userOfficer.id }" class="nav-link" id="nav-quick-officer-profile">
						<i class="far fa-solid fa-address-card nav-icon"></i>
						<p>
							Officer Profile
							<i class="right fa-solid fa-person-military-pointing"></i>
						</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="tlogout.do" class="nav-link" id="nav-quick-logout">
						<i class="far fa-solid fa-right-from-bracket nav-icon"></i>
						<p>
							Logout
						</p>
					</a>
				</li> <!-- end Quick Links -->
				
				
			</ul> <!-- end Sidebar main ul -->
		</nav>	
	</div> <!-- end Sidebar -->
	
</aside> <!-- end Main Sidebar Container -->