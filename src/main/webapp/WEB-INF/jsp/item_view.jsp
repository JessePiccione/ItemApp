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
		<form>
			<table>
				<thead>
				</thead>
				<tbody>
					<tr>
						<th>Style<a href="/item?mode=style"></a></th>
						<th>Date<a href="/item?mode=date"></a></th>
						<th>Brand<a href="/item?mode=brand"></a></th>
						<th>Department<a href="/item?mode=dept"</th>
						<th>Class<a href="/item?mode=itemClass"></th>
						<th>Original Price<a href="/item?mode=originalPrice"></a></th>
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
							<input id ="addButton" type="button" name="add" value="add">
							<input id = "updateButton" type="button" name="update" value="update">
							<input id = "deleteButton" type="button" name="delete" value="delete">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<script src = "js/script.js"></script>
	</body>
</html>