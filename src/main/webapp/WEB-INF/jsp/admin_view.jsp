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
			<table id="activityTable">
						<thead>
							<tr>
								<th id="headerRow" colspan=12>
									<h1 id="tableHeader"></h1>
								</th>
							</tr>
						</thead>
						<tbody id ="itemBody">
							<tr class="toprow header">
								<th onclick="changeSort('id')">
									Id&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('sku')">
									Sku&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('date')">
									Date&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('brand')">
									Brand&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('dept')">
									Department&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('itemClass')">
									Class&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('originalPrice')">
									Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('salePrice')">
									Sale Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('activeFlag')">
									Active&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('imageFile')">
									Image&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('variants')">
									Variants&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
								<th onclick="changeSort('rating')">
									Rating&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
								</th>
							</tr>
							<tr id ="searchRow">
								<td>
									<input id="idSearch" name ="idSearch" type="text" placeholder="Search..." value="${ id }" >
								</td>
								<td>
									<input id="skuSearch" name="idSearch" type="text" placeholder="Search..." value="${ sku }">
								</td>
								<td>
									<input id="dateSearch" name = "dateSearch" type="text" placeholder="Search..." value="${ date }">
								</td>
								<td>
									<input id="brandSearch" name = "brandSearch" type="text" placeholder="Search..." value="${ brand }">
								</td>
								<td>
									<input id="deptSearch" name = "deptSearch" type="text" placeholder="Search..." value="${ dept }">
								</td>
								<td>
									<input id="itemClassSearch" name = "itemClassSearch" type="text" placeholder="Search..." value="${ itemClass }">
								</td>
								<td>
								</td>
								<td>
								</td>
								<td>
									<input id="activeFlagSearch" name ="activeFlagSearch" type="text" placeholder="Search..." value="${ activeFlag }">
								</td>
								<td>
									<input id="imageFileSearch" name="imageFileSearch" type="text" placeholder="Search..." value="${ imageFile }">
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
								<th class="bottomrow" colspan=12>
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
		<script src = "/js/script.js"></script>
		<c:if test='${role.equals("ADMIN")}'>
			<script src="/js/adminscript.js"></script>
		</c:if>
	</body>
</html>