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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>PhotoStack</title>
</head>
<body>
<div class="container">
	<nav class="navbar navbar-dark bg-dark">
	  	<a class="navbar-brand" href="/homepage">
	    	<img src="/images/logo_mini.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
	    	Photo Stack
	  	</a>
	  	<div class="btn-group" role="group" aria-label="Basic example">
		    <a href="/profile/<c:out value="${ID}"/>"><button type="button" class="btn btn-secondary">Home</button></a>
				<a href="/all_friends/<c:out value="${ID}"/>"><button type="button" class="btn btn-secondary">Friends</button></a>
			<a href="/logout"><button type="button" class="btn btn-secondary">LogOut</button></a>
		</div>
	</nav>
	<div class="mainContent userContent">
		<div>
			<c:forEach var="profile" items="${profilePage}">
				<div class="postBody">
						<img alt="img" src="/photos/<c:out value="${profile.fileName}" />">
		    	</div>
		    	<div>
						<c:out value="${profile.user_description}" />
		    	</div>
			</c:forEach>
		</div>
		<div class="addFriandBtn">
			<form:form action="/newFriend"  method="post" modelAttribute="createFriend">   
	    		<div class=forms>
					<form:input type="hidden" path="user" value="${user_id}" class="form-control"/>
					<c:forEach var="prof" items="${profilePage}">
						<form:input type="hidden" path="friendID" value="${prof.user.id}" class="form-control"/>
					</c:forEach>
				</div>
				<div class=submitBtn> 
					<input class="btn btn-primary" type="submit" value="Add to friends">
				</div>
			</form:form>
		</div>
	</div>
		<div class=photoContent>
		<div class="row"> 
	  		<c:forEach var="post" items="${posts}">
				<div class=column>
					<a href="/post/${post.id}">
						<img alt="img" src="/photos/<c:out value="${post.photo.fileName}" />">
			    	</a>
		    	</div>
			</c:forEach>
		</div>
	</div>
</div>
<div>
			
		</div>
</body>
</html>