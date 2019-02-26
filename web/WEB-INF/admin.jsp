<%@ page import="model.Genre" %><%--
  Created by IntelliJ IDEA.
  User: roza
  Date: 2/26/19
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Add Genre<br><br>
<hr>
<form action="/addGenre" method="post">
    <input type="text" name="name">

    <input type="submit" value="Add Genre"><br>
</form>
<br><br>

<hr>
Add Movie
<form action="/addMovie" method="post" enctype="multipart/form-data">
    title: <br><input type="text" name="title"><br>
    description: <br><input type="text" name="description"><br>
    <c:forEach var="g" items="${requestScope.get('genre')}">

        ${g.name}<input type="checkbox" name="gId" value="${g.name}"><br>
    </c:forEach>

    Directet By: <br><input type="text" name="director"/><br><br>
    image: <input type="file" name="picture"/><br>
    <br><br>
    <input type="submit" id="submit" value="Add Post"><br>


</form>

</body>
</html>
