<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="form-group">

	<label for="firstName">First Name:</label>
	<input class="form-control" type="text" name="firstName" id="firstName">
	<br>
	
	<label for="middleName">Middle Name:</label>
	<input class="form-control" type="text" name="middleName" id="middleName">
	<br>
	
	<label for="lastName">Last Name:</label>
	<input class="form-control" type="text" name="lastName" id="lastName">
	<br>
	
	<label for="title">Title:</label>
	<select class="form-control" id="title" name="title" id="title">
 			<option value="" selected></option>
 			<option value="Jr">Jr.</option>
 			<option value="Sr">Sr.</option>
 			<option value="I">I.</option>
 			<option value="II">II.</option>
 			<option value="III">III.</option>
	</select>
	<br>
		
  	<label for="birth">Birth Date:</label>
 	<input class="form-control" type="date" id="birth" name="birth">
 	<br>
 	 
	<label for="ethnicityName">Ethnicity:</label>
	<input class="form-control" list="ethnicityNameId" id="ethnicityName" name="ethnicityName">
	<datalist id="ethnicityNameId">
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
	<input class="form-control" type="text" name="gender" id="gender" required>
	<br>
	
	<label for="description">Description:</label>
	<textarea class="form-control" name="description" id="personDescription"rows="10" cols="30"></textarea>
	<br>
</div>
	