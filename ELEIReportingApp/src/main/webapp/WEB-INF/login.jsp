<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<h1>Login</h1>
	
	<form action="login.do" method="post">
		<label for="username">Username</label>
		<input type="text" name="username">
	</form>

</body>
</html>