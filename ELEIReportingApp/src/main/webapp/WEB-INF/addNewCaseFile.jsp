<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Case File</title>
</head>
<body>

	<h1>Add New Case File</h1>
	
	<form action="addNewCaseFile.do" method="post">
	
		<label for="caseNumber">Case #:</label>
		<input type="number" name="caseNumber">
		<br>
		<label for="suspectedCrime">Suspected Crime:</label>
		<input type="text" name="suspectedCrime">

 	 		<label for="description">Description:</label>
		<textarea name="description" rows="10" cols="30">
</textarea>
		
		<br>
		<input type="submit" value="Submit">
		
	</form>

</body>
</html>