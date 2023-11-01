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
			<div class ='logoHeader'>
				<img class="imgHeader" src="/img/spencer.jpg"></img>
				<img class="imgHeader" src="/img/spirit.jpg"></img>
				<a class="logout" href="/logout">Log out</a>
			</div>
			<div id="graphPopup" class="popup">
				<span class="close" onclick="closeGraphPopup()">&times;</span>
				<canvas id="myChart"></canvas>
			</div>
		<div class="formBar">
			<div class="locationWrapper">
				<div class="colored-wrapper top-form">
					<div class="formContainer">
						<div id="uploadFormHider">
							<h1 id="uploadFormHeader"class="formHeader collapsedHeader">
								Upload New File <i id="uploadCaret" class="fa fa-caret-down" aria-hidden="true"></i>
							</h1>
						</div>
						<div id="uploadFormHidden" class="hide">
							<hr />
							<form id="sendUploadForm" method="POST" action = "item/upload" enctype="multipart/form-data">
								<label class="formLabel">Select Date</label>
								<input id='fileDate' type="date" name="date" required>
								<input id="uploadButton"  type = "file" name = "file" required>
								<input id="sendUploadButton" type = "submit" name = "submit" value = "upload">
								<div id = "loader" class="skill hide">
									<div class="outer">
										<div class="inner">
										</div>
									</div>
									<svg xlmns="http://www.w3.org/2000/svg" version="1.1" width="40px" height="40px">
										<defs>
											<linearGradient id="GradientColor">
												<stop offset="0%" stop-color="#DA22FF" />
												<stop offset="100%" stop-color="#9733EE" />
											</linearGradient>
										</defs>
										<circle cx="20" cy="20" r="10" stroke-linecap="round" />
									</svg>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="locationWrapper">
				<div class="colored-wrapper top-form second-form">
					<div class="formContainer">
						<div id="searchFormHider">
							<h1 id="searchFormHeader"class="formHeader collapsedHeader">Search By <i id="searchCaret" class="fa fa-caret-down" aria-hidden="true"></i></h1>
						</div>
						<div id="searchFormHidden" class="hide">
							<hr />
							<form id="superSearch">
								<label class="formLabel">
									Start Date 
								</label>
								<input type="date" id="fileDate">
								<label class="formLabel">
									End Date
								</label>
								<input type="date" id="fileDate">
								<br />
								<label class="formLabel">Search Type:</label>
								<select id="searchType" class="searchFormSelect">
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
								<label class="formLabel">Status</label>
								<select id="activeStatus" class="searchFormSelect">
									<option value="B" selected>
										Both
									</option>
									<option value="N">
										Inactive
									</option>
									<option value="Y">
										Active
									</option>
								</select>
								<br />
								<label class="formLabel">
									Enter the search values separated by comma; white space is ignored.
								</label>
								<br />
								<textarea id="bigBox" class="bigbox"  name="values" palceholder="Seperate each value by semi colon, white space is negligable"></textarea>
								<br />
								<input id="supersearch" type="submit" value="Search" name="Search">
							</form>
						</div>
					</div>
				</div>
			</div>	
		</div>	
		<div id="hidder" class="hide">
			<div class="colored-wrapper table-margin">
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
		</div>
		
		<script src = "/js/script.js"></script>
	</body>
</html>