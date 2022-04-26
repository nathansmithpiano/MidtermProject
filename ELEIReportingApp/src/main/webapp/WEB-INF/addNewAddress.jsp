<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Address</title>
</head>
<body>

	<h1>Add New Address</h1>
	
	<form action="addNewAddress.do" method="post">
	
		<label for="streetOne">Street 1:</label>
		<input type="text" name="streetOne">
		<br>
		<label for="streetTwo">Street 2:</label>
		<input type="text" name="streetTwo">
		
		<br>
		<label for="city">City:</label>
		<input type="text" name="city">
		
		<br>
		<label for="stateCode">State Code:</label>
		<input type="text" name="stateCode" maxlength="2">
		
		<br>
		<label for="zip">ZIP:</label>
		<input type="number" name="zip" min="0" max="99999">
		

 	 		<label for="description">Description:</label>
		<textarea name="description" rows="10" cols="30">
</textarea>

		<br>
		<input type="submit" value="Submit">
		
	</form>

</body>
</html>