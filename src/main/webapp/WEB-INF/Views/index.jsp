<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: raghav
  Date: 4/3/19
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<body>

<link href="<c:url value="/resources/stylesheets/theme2.css" />" rel="stylesheet">

<form action="/login" method="post">
    <table>
        <tr>
            <td><input id="user_box" name="user" type="text" placeholder=" Enter User ID" /></td>
        </tr>
        <tr>
            <td><input id="password_box" name="password" type="password" placeholder=" Enter Password" /></td>
        </tr>
        <tr>
            <td><input id="login_but" type="submit" value="Login" /></td>
        </tr>
    </table>
</form>

<p id="error">${error}</p>
<p><a href="/createAccount" id="new_link">Create a new account >_</a></p>

</body>
</html>
