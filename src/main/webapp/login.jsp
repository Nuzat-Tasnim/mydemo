<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html: charset=UTF-8">
<link type="text/css" href="<c:url value="login.css" />" rel="stylesheet">
    <title>My goddamn title</title>
</head>
<body>

<div class = "form-container">
<h1>Log in</h1>
<form action="login" method = "post">
    <p class = "input-caption">Email</p><br>
    <input class = "input" type="email" name="email"><br>
    <p class = "input-caption">Password</p><br>
    <input class = "input" type="password" name="password"><br>
    <input class = "button" type="submit"><br>
</form>
</div>


</body>
</html>
