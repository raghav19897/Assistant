<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: raghav
  Date: 6/3/19
  Time: 7:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>

<script src="${pageContext.request.contextPath}/resources/scripts/scriptHome.js" type="text/javascript"></script>

<body onload="setInterval(type,50)">

<link href="${pageContext.request.contextPath}/resources/stylesheets/theme3.css" rel="stylesheet">

<p id="description"></p>

<form action="/create" method="post">
    <table>
        <tr>
            <td><input id="user_box" name="user" type="text" placeholder=" Enter User ID"></td>
        </tr>
        <tr>
            <td><input id="password_box" name="password" type="password" placeholder=" Set Password" /></td>
        </tr>
        <tr>
            <td><input id="name_box" name="name" type="text" placeholder=" Enter your Name"/></td>
        </tr>
        <tr>
            <td><input id="create_but" type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>

<p id="error">${error}</p>

</body>
</html>
