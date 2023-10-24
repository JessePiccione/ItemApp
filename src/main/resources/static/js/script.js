let typingTimer;
var globals = {
	"itemsList":[],
	"url": document.getElementById("url").value,
	"method":"GET",
	"pageSize": document.getElementById("pageSize").value,
	"pageNumber":0,
	"direction": true,
	"oldSorter":"id",
	"body":{},
	"type":"id"
};
//onDOMContentLoader
document.addEventListener("DOMContentLoaded",DOMit);
//crud buttons 
document.getElementById("addButton").addEventListener("click", addHandler);
document.getElementById("updateButton").addEventListener("click", updateHandler);
document.getElementById("deleteButton").addEventListener("click", deleteHandler); 
//superSearch
function setRequestValues(body, url, method){
	globals.body= body;
	globals.url = url;
	globals.method = method; 
}
document.getElementById("superSearch").addEventListener("submit", function(event){
	event.preventDefault();
	globals.type = document.getElementById("searchType").value;
	const textArea = document.getElementById("bigBox").value;
	pageNumber = 0;
	if(globals.type=="" || textArea==""){
		setRequestValues({}, "/item","GET");
		updatePageCount();
		loadPage()
		return;
	}
	var payload = textArea.split(",");
	for (var x = 0;x < payload.length; x++){
		payload[x] = payload[x].trim();
	} 
	const body = {"values":payload};
	setRequestValues(body,"/item/"+globals.type, "POST");
	updatePageCountSpecialCase();
	loadPage();
});
//search fields
document.getElementById("idSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('id');}, 100);
});
document.getElementById("skuSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('sku');}, 100);
});
document.getElementById("dateSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('date');}, 100);
});
document.getElementById("brandSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('brand');}, 100);
});
document.getElementById("deptSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('dept');}, 100);
});
document.getElementById("itemClassSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('itemClass');}, 100);
});
document.getElementById("activeFlagSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('activeFlag');}, 100);
});
document.getElementById("imageFileSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {sendSearchRequest('imageFile');}, 100);
});
//pagination stuff 
document.getElementById("previousPage").addEventListener("click", function(event){
	if(globals.pageNumber <= 0){
		alert("Cannot go back Because this is the Start Page");
		return;
	}
	globals.pageNumber--;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
});
document.getElementById("nextPage").addEventListener("click", function(event){
	if(globals.pageNumber > Number(document.getElementById("maximumPage").innerHTML)-1){
		alert("Sorry, this is the last page cannot go to next page.");
		return;
	}
	globals.pageNumber++;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
});
document.getElementById("pageSize").addEventListener("change", function(event){
	globals.pageSize = document.getElementById("pageSize").value;
	globals.pageNumber=0;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
});
//handler functions 
function DOMit(event){
	loadInnerDOMContent(event);
} 
//handles adds to the server.
function addHandler(event){
	if(hasTextFields()){
		insertTextFields(event);	                                                                                                                                             
	}
	else {
		var request = new XMLHttpRequest();
		request.open("POST", "/item");
		request.setRequestHeader("Content-Type","application/json");
		const body = getItemFieldValues();
		request.onload = () =>{
			updatePageCount();
			loadItems(request.response);
			removeTextFields();
		};
		request.send(JSON.stringify(body));
		event.preventDefault();
	}
}
//handles updates to the server
function updateHandler(event){
	if(hasTextFields()){
		insertTextFields(event);
	}
	else {
		var ids = getList();
		var request;
		var body = getItemFieldValues();
		for (var x = 0; x < ids.length; x++ ){
			request = new XMLHttpRequest();
			body.uniqueId = ids[x];
			request.open("PATCH", "/item");
			request.setRequestHeader("Content-Type","application/json");
			request.onload = () =>{
				loadItems(request.response);
				removeTextFields();
			}			
			request.send(JSON.stringify(body));
			event.preventDefault();
		}
	}	
}
//handles deletes to the server
function deleteHandler(event){
	var ids = getList();
	var request;
	for (var x = 0; x < ids.length; x++ ){
		request = new XMLHttpRequest();
		request.open("DELETE", "/item/"+ids[x]);
		request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		request.onload = () =>{
			if(request.status === 405 ){
				var anotherRequest = new XMLHttpRequest();
				anotherRequest.open("GET", "/item");
				anotherRequest.onload = () => {
					updatePageCount();
					document.getElementById("hidder").className = JSON.parse(anotherRequest.response).length>0?"":"hide";
					loadItems(anotherRequest.response);
				}
				anotherRequest.send();
				return;
			}
			loadItems(request.response);
			updatePageCount()
		};
		request.send();
	}
}
//helper functions 
function loadPage(){
	var request = new XMLHttpRequest();
	request.open(globals.method, globals.url+"?pageNumber="+globals.pageNumber+"&itemsPerPage="+globals.pageSize);
	if(globals.method == "POST"){
		request.setRequestHeader("Content-Type","application/json");
	}
	request.onload = () => {
		loadItems(request.response);
	}
	console.log(globals.body);
	globals.method=="POST"?request.send(JSON.stringify(globals.body)):request.send();
}
function loadSearchItems(items, type, data){
	var filtered = items.filter(e => e[type].includes(data));
	loadItems(JSON.stringify(filtered), true);
}
function sendSearchRequest(type){
	const data = document.getElementById(type+"Search").value;
	loadSearchItems(globals.itemsList, type, data);
}
function loadInnerDOMContent(event){
	const url = document.getElementById("url").value;
	var request = new XMLHttpRequest();
	request.open("GET", url);
	request.onload = () => {
		updatePageCount();
		document.getElementById("hidder").className = JSON.parse(request.response).length>0?"":"hide";
		loadItems(request.response);
	}
	request.send();
}
function loadItems(response, off){
	var items = JSON.parse(response);
	document.querySelectorAll(".dynamicRowEntry").forEach(e => {e.remove()});
	var lastRow = document.querySelector("#itemBody");
	for (var x = 0; x < items.length; x++){
		var newRow = document.createElement("tr");
		newRow.className = "dynamicRowEntry";
		newRow.innerHTML = '<td>'+items[x].id+'</td>\
							<td>'+items[x].sku+'</td>\
							<td class="dateField">'+items[x].date+'</td>\
							<td>'+items[x].brand+'</td>\
							<td>'+items[x].dept+'</td>\
							<td>'+items[x].itemClass+'</td>\
							<td>'+items[x].originalPrice+'</td>\
							<td>'+items[x].salePrice+'</td>\
							<td>'+items[x].activeFlag+'</td>\
							<td class="img"><img src="'+items[x].imageFile+'" class="fixed-size" alt="Description of image"></td>\
							<td>'+items[x].variants+'</td>\
							<td><input class="CheckBoxInput" type="checkbox" name="id" value="'+items[x].uniqueId +'"></td>';
		lastRow.appendChild(newRow);
	}
	if (!off){
		globals.itemsList = items;
	}
}
//sorting implentaion
function changeSort(s){
	if(globals.oldSorter == s){
		globals.direction = !globals.direction;
	}else {
		direction = true;
	}
	globals.itemsList.sort((a, b) =>{
		if(globals.direction == true){
			if(a[s] > b[s]){
				return 1;
			}
			if(a[s] < b[s]){
				return -1;
			}
		} else {
			if(a[s] < b[s]){
				return 1;
			}
			if(a[s] > b[s]){
				return -1;
			}
		}
		return 0;
	});
	globals.oldSorter = s;
	loadItems(JSON.stringify(globals.itemsList));
}
//helper functions 
function getItemFieldValues(){
	return {
				'id': document.getElementById("id").value,
				'sku': document.getElementById("sku").value,
				'date': document.getElementById("date").value,
				'dept': document.getElementById("dept").value,
				'brand': document.getElementById("brand").value,
				'itemClass': document.getElementById("itemClass").value,
				'originalPrice': document.getElementById("originalPrice").value,
				'salePrice': document.getElementById("salePrice").value,
				'activeFlag': document.getElementById ("activeFlag").value,
				'imageFile': document.getElementById("imageFile").value,
				'variants': document.getElementById("variants").value
			};
}
function hasTextFields(){
	return document.getElementById('addRow').innerHTML == "";
}
function insertTextFields(event){
	var tr = document.getElementById('addRow');
	tr.innerHTML = "<td class='inputCell'><input id='id' type='text' name='id' placeholder='Enter an id ...' value=''></td>"+
				   "<td class='inputCell'><input id='sku' type='text' name='sku' placeholder='Enter a Sku ...' value=''></td>"+
				   "<td class='inputCell'><input id='date' type='date' name='date' placeholder='Enter a date ...' value=''></td>"+
				   "<td class='inputCell'><input id='brand' type='text' name='brand' placeholder='Enter a brand...' value=''></td>"+
				   "<td class='inputCell'><input id='dept' type='text' name='dept' placeholder='Enter a dept...' value=''></td>"+
				   "<td class='inputCell'><input id='itemClass' type='text' name='itemClass' placeholder='Enter a Class...' value=''></td>"+
				   "<td class='inputCell'><input id='originalPrice' type='number' name='originalPrice' placeholder='Enter a Price...' value=''></td>"+ 
				   "<td class='inputCell'><input id='salePrice' type='number' name='salePrice' placeholder='Enter a Price...' value=''></td>"+
				   "<td class='inputCell'><input id='activeFlag' type='text' name='activeFlag' placeholder='Enter a Flag...' value=''></td>"+
				   "<td class='inputCell'><input id='imageFile' type='text' name='imageFile' placeholder='Enter an image file...' value=''></td>" +
				   "<td class='inputCell'><input id='variants' type='text' name='variants' placeholder='Enter variants'></td>";
	event.preventDefault();
}
function removeTextFields(event){
	document.querySelectorAll('.inputCell').forEach(e => {e.remove();});
}
function getList(){
	var inputs = document.getElementsByClassName("CheckBoxInput");
	var id = [];
	for (var x = 0; x < inputs.length; x++){
		if(inputs[x].checked){
			id.push(inputs[x].value);
		}
	}
	return id;
}
function updatePageCount(){
	const request = new XMLHttpRequest();
	request.open("GET", "/count");
	request.onload = () =>{
		document.getElementById("currentPage").innerHTML = globals.pageNumber+1;
		document.getElementById("maximumPage").innerHTML = Math.ceil(request.response/globals.pageSize); 
	} 
	request.send();
}
function updatePageCountSpecialCase(){
	const request = new XMLHttpRequest();
	request.open(globals.method, "/count?type="+globals.type);
	request.setRequestHeader("Content-Type","application/json");
	request.onload = () => {
		document.getElementById("currentPage").innerHTML = globals.pageNumber+1;
		document.getElementById("maximumPage").innerHTML = Math.ceil(request.response/globals.pageSize);
	}
	request.send(JSON.stringify(globals.body));
}