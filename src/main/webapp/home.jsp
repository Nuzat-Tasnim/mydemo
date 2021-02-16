<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html: charset=UTF-8">
    <title>My title</title>
</head>
<body>
    <c:out value="${model.user.userName}" /><br>

    <br><a href="<c:url value="/editProfilePage" ></c:url>">Edit Profile</a><br>

    <c:if test="${model.user.admin}">
        <br><a href="<c:url value="/addPlaceForm" ></c:url>">Update Place</a><br>

        <br><a href="<c:url value="/addJourneyForm" ></c:url>">Update Journey</a><br>
    </c:if>

    <br><a href="<c:url value="/searchplaceform" ></c:url>">Search Place</a><br>

    <br><a href="<c:url value="/searchjourneyform" ></c:url>">Search Journey</a><br>

    <br><a href="<c:url value="/bookmarkedPlaces" ></c:url>">Bookmarked Places</a><br>

    <br><a href="<c:url value="/bookmarkedJourneys" ></c:url>">Bookmarked Journeys</a><br>


    <br><a href="logout">Logout</a>

</body>
</html>
