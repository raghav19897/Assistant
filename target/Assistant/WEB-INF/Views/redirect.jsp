<%--
  Created by IntelliJ IDEA.
  User: raghav
  Date: 9/4/19
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Redirect</title>
</head>
<body onload="openLinks()">
<script type="text/javascript">
    function openLinks(){
        window.open("${link0}","_self");
        window.open("${link1}", "_blank");
    }
</script>
</body>
</html>
