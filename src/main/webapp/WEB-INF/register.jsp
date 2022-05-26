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
	<div class="mainContent register">
		<form:form action="/register_user" method="post" modelAttribute="createUser">
			<h1>Sign Up For Photo Stack!</h1>
			<div class=forms>
			<p><form:errors path="userName" class="text-danger"></form:errors></p>
			    <form:label path="userName" for="nameOfUser">Name: </form:label>
			    <form:input path="userName" type="text" class="form-control" id="user_Name" placeholder="Name" autocomplete="off"></form:input>
			</div>   
			<div class=forms> 
			<p><form:errors path="email" class="text-danger"></form:errors></p>
			    <form:label path="email" for="userEmail">Email: </form:label>
			    <form:input path="email" type="text" class="form-control" id="userEmail" placeholder="Email"></form:input>
			</div>   
			<div class=passwordForms>
				<div class="forms password"> 
				<p><form:errors path="password" class="text-danger"></form:errors></p>
					<form:label path="password" for="userPassword">Password: </form:label>
					<form:input path="password" type="password" class="form-control" id="userPassword" placeholder="Password"></form:input>
				</div>   
				<div class="forms confirm"> 
				<p><form:errors path="confirm" class="text-danger"></form:errors></p>
					<form:label path="confirm" for="passwordConfirm">Confirm Password: </form:label>
					<form:input path="confirm" type="password" class="form-control" id="userPassword" placeholder="Confirm Password"></form:input>
				</div>  
			</div> 
			<div class=submitBtn> 
				<input class="btn btn-primary" type="submit" value="Register">
			</div>
		</form:form>
	</div>
</div>
</body>
</html>