<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.models.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Home Page</title>
		<!-- Include Moment.js -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
		<!-- Include Moment-Timezone with data -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.34/moment-timezone-with-data.min.js"></script>
		<!-- Include Chart.js -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<!-- Include date-fns (non-minified version) -->
		<script src="https://cdn.jsdelivr.net/npm/date-fns@2.16.1"></script>
		<!-- Include chartjs-adapter-date-fns -->
		<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@2.0.0"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
	</head>
	<body>
		<div  id="pageShadow" class="pageShadow">
		</div>
		<div class ='logoHeader'>
			<img class="imgHeader" src="/img/betterlogo.png"></img>
		</div>
		<div class="userWrapper">
			<h4 class="usernameHeader">${role}: ${ username }</h4>
			<c:if test = '${role.equals("ADMIN")}'>
				<form method="GET" action="/create/user">
					<input id="registerUserButton" type="submit" name="submit" value="Register User">
				</form>
			</c:if>
			<a class="logout" href="/logout">Log out</a>
		</div>
		<div id="graphPopup" class="popup">
			<span class="close" onclick="closeGraphPopup()">&times;</span>
			<canvas id="myChart"></canvas>
		</div>
		<div id="tablePopup" class="popup">
			<span class="close" onclick="closeTablePopup()">&times;</span>
			<table id="myTable"></table>				
		</div>
		<div id="navbar" class="navbar">
			<ul class="navbar">
				<li id="homeNav" class="navbar startNavbar selected"><a>Home</a></li>
				<c:if test='${role.equals("ADMIN")}'>
					<li id="adminNav" class="navbar"><a>Admin</a></li>
				</c:if>
				<li id="activityNav" class="navbar endNavbar"><a>Activity</a></li>
			</ul>
		</div>
		<div id = "activeView" class="viewWrapper">
			<h2> This is the home page. Nothing is really going on here right now!</h2>
		</div>
		<script src = "/js/script.js"></script>
		<c:if test='${role.equals("ADMIN")}'>
			<script src="/js/adminscript.js"></script>
		</c:if>
	</body>
</html>