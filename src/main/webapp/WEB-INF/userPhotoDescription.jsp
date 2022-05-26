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
<div class="container ">
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
	<div class="mainContent register">
		<div class="postContainer">
			<div class="topSidePost">
				<div class="leftSidePost">
					<h2>Posted By: </h2>
					<h3><a href="/profile/${post.user.id}"><c:out value="${post.user.userName}"></c:out></a></h3>
				</div>
				<div class="rightSidePost">
					<div class="edit">
						<a href="/post/${post.id}/edit"><img src="/images/edit_icon.png"alt="edit_icon"></a>
					</div>
				</div>
			</div>
			<div class=postImg>
				<img alt="img" src="/photos/<c:out value="${post.photo.fileName}" />">
			</div>
			<div class="photoDelete">
				<h3><c:out value="${post.post_text}"></c:out></h3>
				<div class="myBtn">
						<form action="/post/delete/${post.id}" method="post">
							<input type="hidden" name="_method" value="delete">
							<input class="btnDelete" type="submit" value="Delete">
						</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>