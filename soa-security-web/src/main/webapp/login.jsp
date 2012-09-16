<%--
~ Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<html>
<head>
    <title>Login</title>

    <meta charset="utf-8">

    <style type="text/css">

        @import url('style/main.css');
    </style>

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <script src="script/jquery-1.7.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <div class="page-header">
        <h1>SOA Security
            <small>Admin Console</small>
        </h1>
    </div>

    <div>
        <div>
            <c:if test="${not empty param.error}">
                <div class="errors">
                    Login failed: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                </div>
            </c:if>
        </div>

        <form class="form-horizontal" action="login" method="POST">

            <div class="control-group">
                <label class="control-label" for="login">Login</label>

                <div class="controls">
                    <input id="login" name="j_username" type="text" placeholder="Login" autofocus="autofocus">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="password">Password</label>

                <div class="controls">
                    <input id="password" name="j_password" type="password" placeholder="Password">
                </div>
            </div>

            <div class="control-group">
                <div class="controls">

                    <button type="submit" class="btn">Login</button>
                </div>
            </div>
        </form>
    </div>

</div>

</body>
</html>