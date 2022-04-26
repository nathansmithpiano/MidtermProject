<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Note</title>
</head>
<body>

	<h1>Add New Note</h1>
	
	<form action="addNewNote.do" method="post">
		

 	 		<label for="content">Description:</label>
		<textarea name="content" rows="10" cols="30">
</textarea>

		<br>
		<input type="submit" value="Submit">
		
	</form>

</body>
</html>