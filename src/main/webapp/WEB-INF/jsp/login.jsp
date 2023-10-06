<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.models.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    .container {
      width: 300px;
      margin: 0 auto;
      margin-top: 100px;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background-color: #f5f5f5;
    }
    h2 {
      text-align: center;
    }
    .form-group {
      margin-bottom: 10px;
    }
    .form-group label {
      display: block;
      font-weight: bold;
    }
    .form-group input {
      width: 100%;
      padding: 5px;
      border-radius: 3px;
      border: 1px solid #ccc;
    }
    .form-group button {
      width: 100%;
      padding: 8px;
      border-radius: 3px;
      border: none;
      background-color: #4CAF50;
      color: #fff;
      font-weight: bold;
    }
  </style>
</head>
<body>
  <c:if test="${Error != null}">
  	<h1>${Error}</h1>
  </c:if>
  <c:if test="${Success != null}">
  	<h1>${Success}</h1>
  </c:if>
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
  <div class="container">
  <form method='get' action="/login/register" >
    <div class="form-group">
      <button type="submit">New User</button>
    </div>
  </form>
  </div>
</body>
</html>