<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spencer.ItemApp.models.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="/css/loginstyle.css"/>
    <script src="https://kit.fontawesome.com/1a10ff1a82.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="logo">
    <img src="/img/betterlogo.png"></img>
</div>
<div class="container">
    <h2>Log in</h2>
    <form method="POST" action="login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="" placeholder="Enter your username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="" placeholder="Enter your password">
        </div>
        <div class="form-group">
            <button id="loginButton" type="submit" value="Log in">Log in</button>
        </div>
    </form>
</div>
</body>
</html>