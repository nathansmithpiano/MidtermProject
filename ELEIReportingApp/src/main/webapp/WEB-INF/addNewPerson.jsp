<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Person</title>
</head>
<body>

	<h1>Add New Person</h1>
	
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
  			<option value=""></option>
  			<option value="jr">Jr.</option>
  			<option value="sr">Sr.</option>
  			<option value="i">I.</option>
  			<option value="ii">II.</option>
  			<option value="iii">III.</option>
		</select>
		
		<br>
		
  	<label for="birthDate">Birth Date:</label>
 	 <input type="date" id="birthDate" name="birthDate">
 	 
 	 <br>
 	 
 	 		<label for="ethnicityId">Title:</label>
		<select id="ethnicityId" name="ethnicityId">
  			<option type="number" value=1>White</option>
  			<option type="number" value=2>Black</option>
  			<option type="number" value=3>Hispanic</option>
  			<option type="number" value=4>Asian</option>
  			<option type="number" value=5>American Indian</option>
  			<option type="number" value=6>Pacific Islander</option>
  			<option type="number" value=7>Other</option>
		</select>
		
		<br>
 	 
 	 		<label for="gender">Gender:</label>
		<input type="text" name="gender" required>
		<br>
 	 		<label for="description">Description:</label>
		<input type="text" name="description">
		
		<br>
		<input type="submit" value="Submit">
		
	</form>

</body>
</html>