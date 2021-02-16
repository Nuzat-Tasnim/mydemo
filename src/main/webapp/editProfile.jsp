<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html: charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/style.css">
    <title>My goddamn title</title>
</head>
<body>
<c:out value="${model.user.userName}" />
<h1>Edit Profile</h1>
<form action="editProfile" method="post">
    <caption>Your name</caption><br>
    <input type="text" name="name" value="${model.user.userName}"><br>
    <caption>Email</caption><br>
    <input type="email" name="email" value="${model.user.email}"><br>
    <caption>Password</caption><br>
    <input type="password" name="password" value="${model.user.password}"><br>

    <input type="submit"><br>
</form>
<h4>return <a href="<c:url value="/home" >
                        </c:url>">Home
</a> </h4>

</body>
</html>
