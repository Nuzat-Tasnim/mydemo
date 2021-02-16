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
    <c:set var = "bookmark" scope = "session" value = ""/>
    <c:if test = "${model.user.admin}">
        <a href="<c:url value="/editJourney" >
                 <c:param name="elementid" value="${model.journey.journeyId}" />
                 </c:url>">Edit Journey
        </a>
    </c:if>
    <c:if test = "${model.bookmark}">
        <p>Bookmarked.</p>
        <form action="unbookmarkjourney">
            <input type="hidden" name="elementid" value = "${model.journey.journeyId}">
            <input type="submit" value = "Unbookmark">
        </form>
    </c:if>
    <c:if test = "${model.bookmark!=true}">
        <form action="bookmarkjourney">
            <input type="hidden" name="elementid" value = "${model.journey.journeyId}">
            <input type="submit" value = "Bookmark">
        </form>
    </c:if>

    <table>
        <tr>
            <th>Source</th>
            <th>Destination</th>
            <th>Description</th>

        </tr>

        <tr>
            <td><c:out value="${model.journey.source.placeName}" />
                <c:out value="${model.journey.source.description}" /></td>
            <td><c:out value="${model.journey.destination.placeName}" />
                <c:out value="${model.journey.destination.description}" /></td>
            <td><c:out value="${model.journey.description}" /></td>
        </tr>
    </table>
    <c:if test = "${model.user.admin}">
        <a href="<c:url value="/addJourneyInfo" >
                     <c:param name="elementid" value="${model.journey.journeyId}" />
                     </c:url>">Edit Journey Information
        </a>
    </c:if>
    <c:if test="${not empty model.journey.transportList}">
        <p>Available Transport Information</p>
    </c:if>
    <c:forEach items="${model.journey.transportList}" var="transport">
        <tr>
            <td><c:out value="${transport.name}" />
                <c:out value="${transport.rent}" /><br>
            </td>
        </tr>

    </c:forEach>

    <form action="addreviewjourney">
        <caption>Add Review</caption><br>
        <input type="text" name="review"><br>
        <input type="hidden" name="elementid" value="${model.journey.journeyId}">
        <input type="submit"><br>
    </form>
    <table>
    <c:forEach items="${model.journey.reviewList}" var="review">
    <tr>
        <td><c:out value="${review.user.userName}" /><br>
            <c:out value="${review.text}" /></td
    </tr>

    </c:forEach>
    </table>
    <h4>return <a href="<c:url value="/home" ></c:url>">Home</a></h4>

</body>
</html>
