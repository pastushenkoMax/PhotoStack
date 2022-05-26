<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<meta charset="ISO-8859-1">
<title>PhotoStack</title>
</head>
<body>
<div class="container">
	<div class="mainContent register">
		<form:form action="/post/${post.id}/update" enctype="multipart/form-data" method="post" modelAttribute="editPost">
			<input type="hidden" name="_method" value="put">
			<div class=submitBtn> 
				<label for="imageFile">Upload Photo: </label>
    			<input type="file" name="imageFile" id="imageFile" accept="image/png, image/jpeg"  value="choose"/>
    		</div>
			<div class=forms>
			    <form:label path="post_text" for="description">Post: </form:label>
				<div>
					<form:textarea path="post_text" id="description" rows="7" cols="25"/>
				</div>
			</div>
			<div class=forms>
				<form:input type="hidden" path="user" value="${user_id}" class="form-control"/>
			</div>
			<div class=submitBtn> 
				<input class="btn btn-primary" type="submit" value="Update!">
			</div>
		</form:form>
	</div>
</div>
</body>
</html>