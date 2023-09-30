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
						<th>
							Style
						</th>
						<th>
							Date
						</th>
						<th>
							Brand
						</th>
						<th>
							Department
						</th>
						<th>
							Class
						</th>
						<th>
							Original Price
						</th>
						<th>
							Sale Price
						</th>
						<th>
							Active Flag
							
						</th>
						<th>
							Image File
						</th>
						<th>
							Variants
						</th>
						<th>
							Select
						</th>
					</tr>
					<tr>
						<td>
							<input id="idSearch" name ="idSearch" type="text" placeholder="Search...">
							<input id="idSearchButton" name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
							<input id="dateSearch" name = "dateSearch" type="text" placeholder="Search...">
							<input id="dateSearchButton"name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
							<input id="brandSearch" name = "brandSearch" type="text" placeholder="Search...">
							<input id="brandSearchButton" name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
							<input id="deptSearch" name = "deptSearch" type="text" placeholder="Search...">
							<input id="deptSearchButton" name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
							<input id="classSearch" name = "classSearch" type="text" placeholder="Search...">
							<input id="classSearchButton" name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
							<input id="activeFlagSearch" name ="activeFlagSearch" type="text" placeholder="Search...">
							<input id="activeFlagSearchButton"name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
							<input id="imageFileSearch" name="imageFileSearch" type="text" placeholder="Search...">
							<input id="imageFileSearchButton" name="Go" class="filterer" type="button" value="Go">
						</td>
						<td>
						</td>
						<td>
						</td>
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
						<td colspan=11>
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