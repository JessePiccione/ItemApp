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
			<div>
				<img class="offset imgHeader" src="/img/spencer.jpg"></img>
				<img class="imgHeader" src="/img/spirit.jpg"></img>
				<a class="logout" href="/logout">Log out</a>
			</div>
			<div class="formContainer">
				<h1>Upload New File</h1>
				<hr />
				<form method="POST" action = "item/upload" enctype="multipart/form-data">
					<label>Select Date</label>
					<input id='fileDate' type="date" name="date" required>
					<br />
					<br />
					<input id="uploadButton"  type = "file" name = "file" required>
					<input id="sendUploadButton" type = "submit" name = "submit" value = "upload">
				</form>
			</div>
			<div id="graphPopup" class="popup">
				<span class="close" onclick="closeGraphPopup()">&times;</span>
				<canvas id="myChart"></canvas>
			</div>
		<div id="hidder" class="hide">
			<div class="formContainer">
				<h1>Search By</h1>
				<hr />
				<form id="superSearch">
					<label>Select The Search Type:</label>
					<select id="searchType">
						<option value="">
						</option>
						<option value="id">
							Id
						</option>
						<option value="sku">
							Sku
						</option>
						<option value="date">
							Date
						</option>
						<option value="brand">
							Brand
						</option>
						<option value="dept">
							Department
						</option>
						<option value="itemClass">
							Class
						</option>
						<option value="originalPrice">
							Price
						</option>
						<option value="salePrice">
							Sale Price
						</option>
						<option value="itemClass">
							Active
						</option>
						<option value="imageFile">
							Image
						</option>
						<option value="variants">
							Variants
						</option>
					</select>
					<br />
					<br />
						<label>
							Enter the search values separated by comma; white space is ignored.
						</label>
					<br />
					<textarea id="bigBox" class="bigbox"  name="values" palceholder="Seperate each value by semi colon, white space is negligable"></textarea>
					<br />
					<input id="supersearch" type="submit" value="Search" name="Search">
				</form>
			</div>
			<br/>
			<table>
				<tbody id ="itemBody">
					<tr class="toprow">
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
						<td>
						</td>
					</tr>
				</tbody>
				<tfoot>
				<input  id="url" type="hidden" name="contentURL" value="${ url }">
				<tr id="addRow"></tr>
					<tr>
						<td colspan=13>
							<input id="addButton" type="button" name="add" value="add">
							<input id="updateButton" type="button" name="update" value="update">
							<input id="deleteButton" type="button" name="delete" value="delete">
						</td>						
					</tr>
					<tr>
						<th class="bottomrow" colspan=13>
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
	</body>
</html>