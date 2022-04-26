<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">

	<!-- Brand Logo -->
	<a href="thome.do" class="brand-link">
		<img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
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
					${sessionScope.loggedInUser.username }
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
				
				<!-- Dashboard Options -->
				<!-- <li class="nav-item menu-open"> -->
				<li class="nav-item">
					<a href="#" class="nav-link active">
              			<i class="nav-icon fa-solid fa-tachometer-alt"></i>
              			<p>
                			Dashboard
                			<i class="right fa-solid fa-angle-left"></i>
              			</p>
            		</a>
            		<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="./index.html" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
							<p>Dashboard v1</p>
						</a>
						</li>
						<li class="nav-item">
							<a href="./index2.html" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Dashboard v2</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="./index3.html" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Dashboard v3</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Dashboard Options -->
				
				<!-- Incident Options -->
				<li class="nav-item">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-person-burst nav-icon"></i>
						<p>
							INCIDENTS
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="pages/forms/general.html" class="nav-link">
								<i class="far fa-solid fa-file nav-icon"></i>
								<p>
									My Incidents
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="pages/forms/advanced.html" class="nav-link">
								<i class="far fa-solid fa-file-shield nav-icon"></i>
								<p>
									All Incidents
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="addIncident.do" class="nav-link">
								<i class="far fa-solid fa-file-circle-plus  nav-icon"></i>
								<p>
									Add Incident
									<i class="right fa-solid fa-plus"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Incident Options -->
				
				<!-- CaseFile Options -->
				<li class="nav-item">
					<a href="caseFiles.do?id=TODO" class="nav-link">
						<i class="far fa-solid fa-briefcase nav-icon"></i>
						<p>
							CASEFILES
						</p>
					</a>
				</li> <!-- end CaseFile Options -->
				
				
				
				<!-- Address Options -->
				<li class="nav-item">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-map-location-dot nav-icon"></i>
						<p>
							ADDRESSES
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="pages/forms/general.html" class="nav-link">
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
							<a href="pages/forms/general.html" class="nav-link">
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
				<li class="nav-item">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-people-group nav-icon"></i>
						<p>
							PERSONS
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="pages/forms/general.html" class="nav-link">
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
							<a href="pages/forms/general.html" class="nav-link">
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
							<a href="addPerson.do" class="nav-link">
								<i class="far fa-solid fa-person-circle-plus nav-icon"></i>
								<p>
									Add Person
									<i class="right fa-solid fa-plus"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Person Options -->
				
				<!-- Department Options -->
				<li class="nav-item">
					<a href="#" class="nav-link">
						<i class="far fa-solid fa-building-shield nav-icon"></i>
						<p>
							DEPARTMENT
							<i class="right fa-solid fa-angle-left"></i>
						</p>
					</a>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="department.do?id=TODO" class="nav-link">
								<i class="far fa-solid fa-building-circle-arrow-right nav-icon"></i>
								<p>
									My Department
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="departmentOfficers.do" class="nav-link">
								<i class="far fa-solid fa-building-user nav-icon"></i>
								<p>
									All Officers
									<i class="right fa-solid fa-building-shield"></i>
								</p>
							</a>
						</li>
					</ul>
					<ul class="nav nav-treeview">
						<li class="nav-item">
							<a href="officer.do?id=TODO" class="nav-link">
								<i class="far fa-solid fa-address-card nav-icon"></i>
								<p>
									Officer Profile
									<i class="right fa-solid fa-person-military-pointing"></i>
								</p>
							</a>
						</li>
					</ul>
				</li> <!-- end Department Options -->
				
					
				<!-- Quick Links -->
				<li class="nav-header">QUICK LINKS</li>
				<li class="nav-item">
					<a href="addIncident.do" class="nav-link">
						<i class="far fa-solid fa-file-circle-plus  nav-icon"></i>
						<p>
							Add Incident
							<i class="right fa-solid fa-plus"></i>
						</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="addPerson.do" class="nav-link">
						<i class="far fa-solid fa-person-circle-plus nav-icon"></i>
						<p>
							Add Person
							<i class="right fa-solid fa-plus"></i>
						</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="officer.do?id=TODO" class="nav-link">
						<i class="far fa-solid fa-address-card nav-icon"></i>
						<p>
							Officer Profile
							<i class="right fa-solid fa-person-military-pointing"></i>
						</p>
					</a>
				</li> <!-- end Quick Links -->
				
				
			</ul> <!-- end Sidebar main ul -->
		</nav>	
	</div> <!-- end Sidebar -->
	
</aside> <!-- end Main Sidebar Container -->