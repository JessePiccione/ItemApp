<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <div class="container">
  <form method='get' action="/login" >
    <div class="form-group">
      <button type="submit">Log In Page</button>
    </div>
  </form>
  <script src="/js/registerScript.js"></script>
  </div>
</body>
</html>