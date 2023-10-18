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
			<form method="POST" action = "item/upload" enctype="multipart/form-data">
				<input id="uploadButton"  type = "file" name = "file">
				<input id="sendUploadButton" type = "submit" name = "submit" value = "upload">
			</form>
		<div id="hidder" class="hide">
			<table>
				<tbody id ="itemBody">
					<tr>
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
							Department&nbsp;<i id="idSort" class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('itemClass')">
							Class&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('originalPrice')">
							Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i>
						</th>
						<th onclick="changeSort('salePrice')">
							<header>Sale Price&nbsp;<i class="fa fa-sort" aria-hidden="true"></i></header>
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
				<tr id="addRow"></tr>
					<tr>
						<td colspan=12>
							<input id="addButton" type="button" name="add" value="add">
							<input id="updateButton" type="button" name="update" value="update">
							<input id="deleteButton" type="button" name="delete" value="delete">
						</td>						
					</tr>
					<tr>
						<th colspan=12>
							<label>Showing Page&nbsp;</label>
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
						</th>
					</tr>
				</tfoot>	
			</table>
		</div>
		<script src = "/js/script.js"></script>
	</body>
</html>