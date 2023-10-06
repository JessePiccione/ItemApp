<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.models.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Jesse's First Spring Application</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css"/>
		<link rel="stylesheet" type="text/css" href="/css/font-awesome-4.7.0/css/font-awesome.min.css">
	</head>
	<body>
		<br/>
		<form>
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<th onclick="changeSort('id')">
							Style&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('date')">
							Date&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('brand')">
							Brand&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('dept')">
							Department&nbsp;<i id="idSort" class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('itemClass')">
							Class&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('originalPrice')">
							Original Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('salePrice')">
							Sale Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('activeFlag')">
							Active Flag&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('imageFile')">
							Image File&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('variants')">
							Variants&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th>
							Select
						</th>
					</tr>
					<tr>
						<td>
							<input id="idSearch" name ="idSearch" type="text" placeholder="Search...">
						</td>
						<td>
							<input id="dateSearch" name = "dateSearch" type="text" placeholder="Search...">
						</td>
						<td>
							<input id="brandSearch" name = "brandSearch" type="text" placeholder="Search...">
						</td>
						<td>
							<input id="deptSearch" name = "deptSearch" type="text" placeholder="Search...">
						</td>
						<td>
							<input id="classSearch" name = "classSearch" type="text" placeholder="Search...">
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
							<input id="activeFlagSearch" name ="activeFlagSearch" type="text" placeholder="Search...">
						</td>
						<td>
							<input id="imageFileSearch" name="imageFileSearch" type="text" placeholder="Search...">
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<c:forEach var="item" items="${items}">
					<div id="${item.id}">
						<tr>
							<td>${item.id}</td>
							<td>${item.date}</td>
							<td>${item.brand}</td>
							<td>${item.dept}</td>
							<td>${item.itemClass}</td>
							<td>${item.originalPrice}</td>
							<td>${item.salePrice}</td>
							<td>${item.activeFlag}</td>
							<td>${item.imageFile}</td>
							<td>${item.variants}</td>
							<td><input class="CheckBoxInput" type="checkbox" name="id" value="${item.id}"></td>
						</tr>
					</div>
					</c:forEach>
					<tr id="addRow"></tr>
					<tr>
						<td colspan=11>
							<input id ="addButton" type="button" name="add" value="add">
							<input id = "updateButton" type="button" name="update" value="update">
							<input id = "deleteButton" type="button" name="delete" value="delete">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script src = "/js/script.js"></script>
	</body>
</html>