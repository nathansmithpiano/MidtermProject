<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="generic/head.jsp" />
<title>Add a Person</title>
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

</body>
</html>