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
        <a href="<c:url value="/editPlace" >
                 <c:param name="elementid" value="${model.place.placeId}" />
                 </c:url>">Edit Place
        </a>
    </c:if>
    <c:if test = "${model.bookmark}">
        <p>Bookmarked.</p>
        <form action="unbookmarkplace">
            <input type="hidden" name="elementid" value = "${model.place.placeId}">
            <input type="submit" value = "Unbookmark">
        </form>
    </c:if>
    <c:if test = "${model.bookmark!=true}">
        <form action="bookmarkplace">
            <input type="hidden" name="elementid" value = "${model.place.placeId}">
            <input type="submit" value = "Bookmark">
        </form>
    </c:if>

    <table>
        <tr>
            <th>Place Name</th>
        </tr>
        <tr>
            <td><c:out value="${model.place.placeName}" /><br>
                <c:out value="${model.place.description}"/></td>

        </tr>
    </table>

    <c:if test = "${model.user.admin}">
        <a href="<c:url value="/addPlaceInfo" >
                     <c:param name="elementid" value="${model.place.placeId}" />
                     </c:url>">Edit Place Information
        </a>
    </c:if>
    <c:if test="${not empty model.place.hospcontacts}">
        <p>Nearby Hospital Information</p>
    </c:if>
    <c:forEach items="${model.place.hospcontacts}" var="hospital">

        <tr>
            <td><br><c:out value="${hospital.name}" /><br>
                    <c:out value="${hospital.address}" /><br>
                    <c:out value="${hospital.contact}" /><br>
        </tr>

    </c:forEach>

    <c:if test="${not empty model.place.policecontacts}">
        <p>Nearby Police Station Information</p>
    </c:if>

    <c:forEach items="${model.place.policecontacts}" var="police">

        <tr>
            <td><br><c:out value="${police.name}" /><br>
                    <c:out value="${police.address}" /><br>
                    <c:out value="${police.contact}" /><br>
        </tr>

    </c:forEach>
    <c:if test="${not empty model.place.transportList}">
        <p>Available Transport Information</p>
    </c:if>

    <c:forEach items="${model.place.transportList}" var="transport">

        <tr>
            <td><br><c:out value="${transport.name}" /><br>
                <c:if test = "${not transport.availability}">
                    Not
                </c:if>Available<br>
            </td>
        </tr>

    </c:forEach>

    <h4>Reviews</h4>
    <form action="addreviewplace">
        <caption>Add Review</caption><br>
        <input type="text" name="review"><br>
        <input type="hidden" name="elementid" value="${model.place.placeId}">
        <input type="submit"><br>
    </form>
    <table>
    <c:forEach items="${model.place.reviewList}" var="review">

        <tr>
            <td><c:out value="${review.user.userName}" /><br>
                <c:out value="${review.text}" /></td
        </tr>

    </c:forEach>
    </table>

    <h4>return <a href="<c:url value="/home" >
                        </c:url>">Home
    </a> </h4>


</body>
</html>
