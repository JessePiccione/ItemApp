<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.models.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login Page</title>
  <link rel="stylesheet" type="text/css" href="/css/loginstyle.css"/>
  <link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
  <div class="logo">
  	<img src="/img/spencer.jpg"></img>
	<img src="/img/spirit.jpg"></img>
  </div>
  <div class="container">
    <h2>Log in</h2>
    <form method="POST" action="login">
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
  <div class="container2">
  <form method='get' action="/login/register" >
    <div class="form-group">
      <button type="submit">New User</button>
    </div>
  </form>
  </div>
</body>
</html>