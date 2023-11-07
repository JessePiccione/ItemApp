<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login Page</title>
  <link rel="stylesheet" type="text/css" href="/css/loginstyle.css"/>
  <link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
</head>
<body>
  <div class="logo">
  	<img src="/img/betterlogo.png"></img>
  </div>
  <div class="container">
    <h2>Register New User</h2>
    <form>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="" placeholder="Enter your username" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="" placeholder="Enter your password" required>
      </div>
      <div class="form-group">
        <button id="registerButton" type="button" value="Register">Register User</button>
      </div>
    </form>
  </div>
  <div class="container2">
  <form method='get' action="/login" >
    <div class="form-group">
      <button type="submit">Log In Page</button>
    </div>
  </form>
  <script src="/js/registerScript.js"></script>
  </div>
</body>
</html>