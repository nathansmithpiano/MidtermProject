<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="generic/head.jsp" />
<title>THome</title>
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
							<h1 class="m-0">Dashboard v2</h1>
						</div><!-- /.col -->
						
						<!-- Breadcrumbs Title -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Dashboard v2</li>
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
		    							
		    						</h3>
    							
    							</div> <!-- end Card header -->
    					
	    						<div class="content px-2">
	    							
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed efficitur ante sed urna porta viverra. Nam laoreet magna erat, vitae rutrum arcu varius ut. Nunc mattis accumsan tempus. Pellentesque nec blandit purus, nec porta libero. Suspendisse eu luctus nunc, a viverra eros. Vivamus efficitur mi non pharetra malesuada. Maecenas lacus elit, maximus ac sollicitudin ut, tristique nec ante. Proin in posuere diam. Ut at massa eget massa pellentesque sodales.</p>
									<p>Praesent sodales, enim euismod fermentum eleifend, massa turpis maximus erat, a scelerisque nisl nunc a lorem. Phasellus ultricies lectus ut lectus convallis porta. Curabitur eleifend, ligula vel lobortis laoreet, metus velit tincidunt augue, ut maximus lacus sem vel ipsum. Nam nec nisi faucibus, scelerisque libero nec, scelerisque dolor. Mauris molestie cursus faucibus. Vestibulum a elit commodo, sagittis metus vel, pretium magna. Fusce a ex interdum, fermentum purus in, dapibus risus. Pellentesque dignissim nunc at nisl rhoncus pulvinar. Ut leo magna, aliquet vel pulvinar eget, mattis eu orci. Proin consectetur, erat vitae sollicitudin tristique, nulla est efficitur justo, et luctus orci tellus in nibh. Nam at mauris in risus porttitor interdum. Vestibulum tincidunt urna sit amet maximus blandit. Pellentesque maximus ipsum erat, a interdum dolor euismod vitae. Duis interdum porttitor ex, a hendrerit urna dapibus at. Sed quam nisi, congue non lacus id, faucibus placerat velit.</p>
									<p>Vestibulum venenatis leo vel tincidunt porta. Pellentesque ornare ipsum nec turpis sodales laoreet. Quisque a eros ut mauris egestas auctor non eu velit. Duis et tincidunt lorem, vel interdum purus. Integer vehicula sem nec nulla finibus posuere. Nunc sit amet purus maximus, molestie tortor ut, fringilla nisl. Phasellus feugiat eu velit eu dignissim.</p>
									<p>Proin eleifend ex nibh, eget laoreet sem pellentesque id. In laoreet arcu odio, et accumsan ante venenatis eget. In lobortis tortor eros, vel consequat nisl luctus ac. Donec interdum feugiat lorem. Praesent eu dolor lobortis quam bibendum vehicula. Pellentesque non velit id arcu commodo tempus. Cras vel consectetur ligula. Donec sagittis ligula sit amet nulla porttitor vehicula. Aliquam erat volutpat. Mauris volutpat massa non libero condimentum tristique. Suspendisse feugiat a ante id facilisis. Etiam neque odio, sollicitudin vel urna vitae, vulputate aliquet magna. Vestibulum posuere fermentum turpis id venenatis. Suspendisse id libero at orci ultrices placerat ac vulputate neque.</p>
									
	    						</div> <!-- end content px-2 -->
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