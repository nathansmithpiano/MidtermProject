<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Begin Card -->
<div class="card">

	<!-- Card header -->
	<div class="card-header">
		<h3 class="card-title">
			Update Note for Incident ${incident.id }
		</h3>
	</div> <!-- end Card header -->
	
	<!-- Begin Card Body -->
	<div class="card-body">
	
		<form action="updateNote.do?id=${note.id }&incidentId=${incident.id }" method="post">
	
			<div class="row">
			
				<div class="col-sm-12">
					<div class="form-group">
						<label>Note Contents</label>
						<textarea name="content" class="form-control" rows="3" placeholder="Enter ...">${note.content }</textarea>
					</div>
				</div>
				
			</div>
			
			<div class="row">
			
				<div class="col-sm-4">
					<div class="form-group">
						<label class="col-form-label" for="created">
							User ID
						</label>
						<input type="text" class="form-control is-valid" name="userId" id="userId" 
							value = "${sessionScope.loggedInUser.id }" placeholder="Enter ..." required disabled>
					</div>
				</div>	
					
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</div>
			
		</form>
		
	</div> <!-- end Card Body -->
	
</div> <!-- end Card -->
