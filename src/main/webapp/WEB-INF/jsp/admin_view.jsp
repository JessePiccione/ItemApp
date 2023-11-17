<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.models.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin</title>
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
		<script src = "/js/script.js" defer></script>
		<c:if test='${role.equals("ADMIN")}'>
			<script src="/js/adminscript.js" defer></script>
			<script src="/js/adminviewscript.js" defer></script>
		</c:if>
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
				<li id="homeNav" class="navbar startNavbar"><a>Home</a></li>
				<c:if test='${role.equals("ADMIN")}'>
					<li id="adminNav" class="navbar selected"><a>Admin</a></li>
				</c:if>
				<li id="activityNav" class="navbar endNavbar"><a>Activity</a></li>
			</ul>
		</div>
		<div id = "activeView" class="viewWrapper">
			<div class="formBar">
				<div class="locationWrapper">
					<div class="colored-wrapper top-form ">
						<div class="formContainer">
							<div id="uploadFormHider">
								<h1 id="editUserHeader" class="formHeader collapsedHeader">
									Edit User <i id="uploadCaret" class="fa fa-caret-down" aria-hidden="true"></i>
								</h1>
							</div>
							<div id="editUserFormHidden">
								<hr />
								<form id="editUserForm">
									<label class="formLabel" for="userSelect">
										Select User 
									</label>
									<select name="userSelect" id="userSelect">
										<option>If you see this option that is a bad sign.</option>
									</select>
									<label class="formLabel" for="roleSelect">
										Select Role
									</label>
									<select name="roleSelect">
										<option value="ADMIN">Admin</option>
										<option value="USER">User</option>
									</select>
									<label class="formLabel" for>Privileges</label>
									<select name="privileges">
										<option>
											No privileges to grant yet
										</option>
									</select>
									<input type="submit" value="submit">
									<div id = "loader" class="skill hide">
										<div class="outer">
											<div class="inner"></div>
										</div>
										<svg xlmns="http://www.w3.org/2000/svg" version="1.1" width="40px" height="40px">
											<defs>
												<linearGradient id="GradientColor">
													<stop offset="0%" stop-color="#DA22FF" />
													<stop offset="100%" stop-color="#9733EE" />
												</linearGradient>
											</defs>
											<circle class="formCircle"cx="20" cy="20" r="10" stroke-linecap="round" />
										</svg>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>	
			<div class="colored-wrapper">
				<table id="activityTable">
					<thead>
						<tr>
							<th id="headerRow" colspan=3>
								<h1 id="tableHeader">Users</h1>
							</th>
						</tr>
					</thead>
					<tbody id ="itemBody">
						<tr class="toprow header">
							<th>
								Role&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
							</th>
							<th>
								Username&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
							</th>
							<th>
								Privileges&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
							</th>
						</tr>
						<tr id ="searchRow">
							<td>									
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</tbody>
					<tfoot>
					<input  id="url" type="hidden" name="contentURL" value="${ url }">
						<tr class="footer">
							<th class="bottomrow" colspan=3>
								<div class="paginationStuff">
								<label>Showing page&nbsp;</label>
								<label id="currentPage"></label>
								<label>&nbsp;of&nbsp;</label>
								<label id="maximumPage"></label>
								<input id="previousPage" type="button" value = "Previous Page"/>
								<input id="nextPage" type="button" value = "Next Page"/>
								<label>Page Size: </label>						
								<select id="pageSize" name="pageSize">
									<option value=12>
										12
									</option>
									<option value=24>
										24
									</option>
									<option value=36>
										36
									</option>
									<option value=48>
										48
									</option>
									<option value=72>
										72	
									</option>
									<option value=96>
										96
									</option>
								</select>
								</div>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</body>
</html>