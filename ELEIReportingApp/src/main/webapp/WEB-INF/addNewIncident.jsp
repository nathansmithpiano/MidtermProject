<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Incident</title>
</head>
<body>

	<h1>Add New Incident</h1>
	
	<form action="addNewIncident.do" method="post">
	
		<label for="reasonForContact">Reason for contact:</label>
		<input type="text" name="reasonForContact">
		<br>
		<label for="location">Location:</label>
		<input type="text" name="location">
		
		<br>

 	 		<label for="description">Description:</label>
		<textarea name="description" rows="10" cols="30">
</textarea>

		<br>
	<br>
	
		<input type="submit" value="Submit">
		
	</form>
	
			<form action="addNewIncident.do" method="post">
	<input type="submit" value="Add Note">
	</form>
	
	<a href="goToAddNewAddress.do"><button>Add Address</button></a>




</body>
</html>