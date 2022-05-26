<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@1,300&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<meta charset="ISO-8859-1">
<title>PhotoStack</title>
</head>
<body>
<div class="container">
	<div class="mainContent">
		<div class="left">
			<h1 class="welcome">Welcome</h1> <h1 class="to">To</h1> <h1 class="ph_st">Photo Stack</h1>
		</div>
		<div class="mid">
			<img src="/images/logo_for_index.png" alt="logo">
		</div>
		<div class="right">
		<form:form action="/login_user" method="post" modelAttribute="createLogin">
			<h1>Sign in to account</h1>
			<div class=forms> 
			<p><form:errors path="email" class="text-danger"></form:errors></p>
				<form:label path="email" for="userEmail">Email: </form:label>
				<form:input path="email" type="text" class="form-control" id="userEmail" placeholder="User Email"></form:input>
			</div>   
			<div class=forms> 
			<p><form:errors path="password" class="text-danger"></form:errors></p>
				<form:label path="password" for="userPassword">Password: </form:label>
				<form:input path="password" type="password" class="form-control" id="userPassword" placeholder="User Password"></form:input>
			</div>   
			<div class=submitBtn> 
				<input class="btn btn-primary" type="submit" value="SIGN IN">
			</div>
		</form:form>
		<div>
			<p>Don't have an account? <a href="/new_user">Sign Up!</a></p>
		</div>
		</div>
	</div>
</div>
</body>
</html>