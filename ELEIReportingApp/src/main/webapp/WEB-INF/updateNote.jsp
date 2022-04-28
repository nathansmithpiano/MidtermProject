<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Note</title>
</head>
<body>

	<h1>Update Note</h1>
	
	<form action="updateNote.do" method="post">
		
		<input type="hidden" name="id" value="${note.id }">

 	 		<label for="content">Description:</label>
		<textarea name="content" rows="30" cols="50">${note.content }</textarea>

		<br>
		<input type="submit" value="Submit">
		
	</form>

</body>
</html>