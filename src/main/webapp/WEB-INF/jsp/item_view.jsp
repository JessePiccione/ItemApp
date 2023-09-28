<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.spencer.ItemApp.db.Item" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Jesse's First Spring Application</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<br/>
		<form id="table">
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<th>Style</th>
						<th>Date</th>
						<th>Brand</th>
						<th>Department</th>
						<th>Class</th>
						<th>Original Price</th>
						<th>Sale Price</th>
						<th>Active Flag</th>
						<th>Image File</th>
						<th>Variants</th>
						<th>Select</th>
					</tr>
					<c:forEach var="item" items="${items}">
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
					</c:forEach>
					<tr id="addRow"></tr>
					<tr>
						<td>
							<input id ="addButton" type="submit" name="add" value="add">
							<input id = "updateButton" type="submit" name="update" value="update">
							<input id = "deleteButton" type="submit" name="delete" value="delete">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script src = "js/script.js"></script>
	</body>
</html>