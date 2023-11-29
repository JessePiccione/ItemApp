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
		<!-- Include date-fns (non-minified version) -->
		<script src="https://cdn.jsdelivr.net/npm/date-fns@2.16.1"></script>
		<link rel="stylesheet" type="text/css" href="/css/style.css"/>
		<script src="https://kit.fontawesome.com/1a10ff1a82.js" crossorigin="anonymous"></script>
		<input type="hidden" id="_csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}">
	</head>
	<div>
		<div  id="pageShadow" class="pageShadow">
		</div>
		<div class ='logoHeader'>
			<img class="imgHeader" src="/img/betterlogo.png"></img>
		</div>
		<div class="userWrapper">
			<h4 class="usernameHeader">${role}: ${ username }</h4>
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
			<div class="formBar">
				<div class="locationWrapper">
					<div class="colored-wrapper">
						<div id="totalFeedsHider">
							<h1 id="totalFeedsHeader" class="formHeader collapsedHeader">
								Total Feeds : ${totalFile}
							</h1>

						</div>
					</div>
				</div>
				<div class="locationWrapper">
					<div class="colored-wrapper second-form">
						<div id="lastFeedHider">
							<h1 id="lastFeedHeader" class="formHeader collapsedHeader">
								Last Feed : ${lastFile}
							</h1>
						</div>
					</div>
				</div>
			</div>
			<div class="formBar">
				<div class="locationWrapper">
					<div class="colored-wrapper">
						<div id="totalUsersHider">
							<h1 id="totalUsersHeader" class="formHeader collapsedHeader">
								Total Users : ${totalUsers}
							</h1>
						</div>
					</div>
				</div>
				<div class="locationWrapper">
					<div class="colored-wrapper second-form">
						<div id="lastUserHider">
							<h1 id="lastUserHeader" class="formHeader collapsedHeader">
								Last User : ${lastUser}
							</h1>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="/js/homescript.js">
		<script src = "/js/script.js"></script>
		<c:if test='${role.equals("ADMIN")}'>
			<script src="/js/adminscript.js"></script>
		</c:if>
	</body>
</html>