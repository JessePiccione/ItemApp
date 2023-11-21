let typingTimer;
let globals = {
	"url": document.getElementById("url").value,
	"method":"GET",
	"pageSize": 12,
	"pageNumber":0,
	"direction": true,
	"oldSorter":"id",
	"body":{},
	"type":"id",
	"chart":null,
	"startDate":'',
	"endDate": new Date(),
	"status": document.getElementById("activeStatus").value,
};
globals.startDate = new Date(globals.endDate.getTime() - 6 * 24 * 60 * 60 * 1000);
//onDOMContentLoader
document.addEventListener("DOMContentLoaded",loadInnerDOMContent);
document.getElementById("sendUploadForm").addEventListener("submit",function(event){
	var loader = document.getElementById("loader");
	freezeTable();
	loader.classList.remove("hide");
});
document.getElementById("uploadFormHider").addEventListener("click", function(event){
	changeCaret("upload");
});
document.getElementById("searchFormHider").addEventListener("click", function(event){
	changeCaret("search");
});
//superSearch
function setRequestValues(body, url, method){
	globals.body= body;
	globals.url = url;
	globals.method = method; 
}
document.getElementById("superSearch").addEventListener("submit", function(event){
	event.preventDefault();
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	globals.status  = document.getElementById("activeStatus").value;
	globals.startDate = startDate !== "" ? moment.tz(startDate, "America/New_York").toDate() : globals.startDate;
	globals.endDate = endDate !== "" ? moment.tz(endDate, "America/New_York").toDate() : globals.endDate;
	globals.type = document.getElementById("searchType").value;
	console.log(globals.status);
	const textArea = document.getElementById("bigBox").value;
	pageNumber = 0;
	if(globals.type=="" || textArea==""){
		setRequestValues({}, "/item","GET");
		loadInnerDOMContent();
		return;
	}
	var payload = textArea.split(",");
	for (var x = 0;x < payload.length; x++){
		payload[x] = payload[x].trim();
	} 
	const body = {"values":payload};
	setRequestValues(body,"/item/"+globals.type, "POST");
	loadInnerDOMContent();
	updatePageCountSpecialCase();
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
function previousPage(event){
	if(globals.pageNumber <= 0){
		alert("Cannot go back Because this is the Start Page");
		return;
	}
	globals.pageNumber--;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
}
function nextPage(event){
	if(globals.pageNumber > Number(document.getElementById("maximumPage").innerHTML)-1){
		alert("Sorry, this is the last page cannot go to next page.");
		return;
	}
	globals.pageNumber++;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
}
function pageSize(event){
	globals.pageSize = document.getElementById("pageSize").value;
	globals.pageNumber=0;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
}
//handler functions 

//helper functions 
function loadPage(){
	freezeTable();
	return new Promise((resolve)=>{
		var request = new XMLHttpRequest();
		
		request.open(globals.method, globals.url+
									"?pageNumber="+
									globals.pageNumber+
									"&itemsPerPage="+
									globals.pageSize+
									"&startDate="+
									formatDate(globals.startDate)+
									"&endDate="+
									formatDate(globals.endDate)+
									"&flag="+
									globals.status);
		
		if(globals.method == "POST"){
			request.setRequestHeader("Content-Type","application/json");
		}
		request.onload = () => {
			document.getElementById("tableHeader").innerText = `Activity Status from ${formatDate(globals.startDate)} to ${formatDate(globals.endDate)}`;
			globals.itemsList = JSON.parse(request.response);
			loadItems(request.response, true);
			unfreezeTable();
			resolve();
		}
		globals.method=="POST"?request.send(JSON.stringify(globals.body)): request.send();
	})
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
	loadPage().then(()=>{
		var hidder = document.getElementById("hidder");
		globals["itemsList"].length > 0 ? hidder.classList.remove("hide") : hidder.classList.add("hide");
		
	});
	updatePageCount();
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
							<td><div onclick="showTablePopup(\''+items[x].id+'\',\''+items[x].sku+'\')">'+items[x].activeFlag+'</div></td>\
							<td class="img"><img src="'+items[x].imageFile+'" class="fixed-size" alt="Description of image"></td>\
							<td>'+items[x].variants+'</td>\
							<td>'+items[x].rating+'/'+
							Math.floor((((globals.endDate - globals.startDate)/(1000 * 60 * 60 * 24)) + 1))+
							'</div></td>';
							lastRow.appendChild(newRow);
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
function setPageNumber(pageNumber){
	globals.pageNumber = pageNumber;
	globals.method == "POST"?updatePageCountSpecialCase():updatePageCount();
	loadPage();
}
function buildPagingTools(count){
	//page count
	let pageCount = Math.floor(count/globals.pageSize);
	//get tool bar and clear elements
	let toolBar = document.getElementById("bottomrow");
	toolBar.innerHTML = "";
	//chevron left
	let leftChevron = document.createElement("i");
	leftChevron.id = "previousPage"
	leftChevron.classList.add("fa-solid");
	leftChevron.classList.add("fa-chevron-left");
	leftChevron.style.marginRight = "30px";
	toolBar.appendChild(leftChevron);
	leftChevron.addEventListener("click", previousPage)
	//generate inner page values 
	if(globals.pageNumber != 0){
		let ellipse = document.createElement("label");
		ellipse.style.marginRight = "30px";
		ellipse.innerText ="...";
		toolBar.appendChild(ellipse);
	}
	let i = 0;
	for(let index = 0; index < 5 && globals.pageNumber+index < pageCount; index++){
		let label = document.createElement("label");
		label.style.marginRight = "30px";
		label.addEventListener("click",function(){setPageNumber(globals.pageNumber+index)});
		label.innerText = globals.pageNumber+index+1;
		toolBar.appendChild(label);
		i = index;
	}
	
	if (globals.pageNumber + i < pageCount){
		ellipse = document.createElement("label");
		ellipse.style.marginRight = "30px";
		ellipse.innerText ="...";
		toolBar.appendChild(ellipse);
		
	}
	let label = document.createElement("label");
		label.style.marginRight = "15px";
		label.id = "maximumPage";
		label.addEventListener("click", function(){setPageNumber(pageCount)});
		label.innerText = pageCount+1;
		toolBar.appendChild(label);
	//chevron right 
	let rightChevron = document.createElement("i");
	rightChevron.classList.add("fa-solid");
	rightChevron.classList.add("fa-chevron-right");
	rightChevron.style.marginRight = "30px";
	rightChevron.id = "nextPage";
	rightChevron.addEventListener("click", nextPage);
	toolBar.appendChild(rightChevron);
	//pagesize selector 
	let main = document.createElement("div");
	main.className = "pageSizeDiv";
	main.innerHTML = "<label>Page Size: </label>	\
									<select id='pageSize' name='pageSize'>\
										<option value=12"+(globals.pageSize==12?" selected":"")+">\
											12\
										</option>\
										<option value=24"+(globals.pageSize==24?" selected":"")+">\
											24\
										</option>\
										<option value=36"+(globals.pageSize==36?" selected":"")+">\
											36\
										</option>\
										<option value=48"+(globals.pageSize==48?" selected":"")+">\
											48\
										</option>\
										<option value=72"+(globals.pageSize==72?" selected":"")+">\
											72	\
										</option>\
										<option value=96"+(globals.pageSize==96?" selected":"")+">\
											96\
										</option>\
									</select>"
	toolBar.appendChild(main); 
	document.getElementById("pageSize").addEventListener("change", pageSize);
}
function updatePageCount(){
	const request = new XMLHttpRequest();
	request.open("GET", "/count?startDate="+
						formatDate(globals.startDate)+
						"&endDate="+
						formatDate(globals.endDate)+
						"&flag="+
						globals.status);
	request.onload = () =>{
		buildPagingTools(JSON.parse(request.response));
	} 
	request.send();
}
function updatePageCountSpecialCase(){
	const request = new XMLHttpRequest();
	request.open(globals.method, "/count?type="+
								 globals.type+
								 "&startDate="+
								 formatDate(globals.startDate)+
								 "&endDate="+
								 formatDate(globals.endDate)+
								 "&flag="+
								 globals.status);
	request.setRequestHeader("Content-Type","application/json");
	request.onload = () => {
		buildPagingTools(JSON.parse(request.response));
	}
	request.send(JSON.stringify(globals.body));
}
async function showTablePopup(id,sku){
	closeTablePopup();
	let itemResponse;
	let p1 = new Promise((resolve)=>{
		var itemRequest= new XMLHttpRequest();
		itemRequest.open("GET", "/item/table/top?sku="+
								sku+
								"&startDate="+
								formatDate(globals.startDate)+
								"&endDate="+
								formatDate(globals.endDate));
		itemRequest.onload = () => {
			itemResponse = itemRequest.response;
			resolve();
		};
		itemRequest.send();
	}).then(()=>{
			buildTable(JSON.parse(itemResponse));
			document.getElementById("tablePopup").style.display="block";
			document.getElementById("pageShadow").style.display = "block";
	});
}
function showChanges(items){
	if(items.length <= 0){
		return items;
	}
	var newItems = [];
	items.forEach((item,i)=>{
		if( newItems.length <= 0 || items.length-1 == i || newItems[newItems.length-1].activeFlag !== item.activeFlag){
			newItems.push(item);
		}
	});
	return newItems;
}
function buildTable(items){
	//clear table out
	items = showChanges(items);
	let table = document.getElementById("myTable");
	for(let x =table.children.length-1; x >= 0; x--){
		table.children[x].remove();
	}
	//create new body and header
	let tBody = document.createElement("tbody");
	tBody.classList.add("popupTBody");
	//header row
	let headRow = document.createElement("tr");
	let headCol = document.createElement("th");
	let header =  document.createElement("h4");
	headCol.classList.add("popupHeader");
	headCol.colSpan = 2;
	header.classList.add("popupHeader");
	header.innerText = `Activity of ${items[0].sku} \n${formatDate(globals.startDate)} to ${formatDate(globals.endDate)}`;
	headCol.appendChild(header);
	headRow.appendChild(headCol);
	tBody.appendChild(headRow);
	//header columns date and active  
	headRow = document.createElement("tr");
	headCol = document.createElement("th");
	headCol.innerText = "Date";
	headRow.appendChild(headCol);
	headCol = document.createElement("th");
	headCol.innerText = "Active";
	headRow.appendChild(headCol);
	tBody.appendChild(headRow);
	let row,col;
	items.forEach((item,index)=>{
		row = document.createElement("tr");
		col = document.createElement("td");
		col.innerText = item.date;
		row.appendChild(col);
		col = document.createElement("td");
		col.innerText = item.activeFlag;
		col.style.backgroundColor = item.activeFlag == "Y"?"green":"red";
		col.style.backgroundColor = item.activeFlag == "Y" && (index == 0 || index==items.length-1)? 
									"lightgreen":
									col.style.backGroundColor;
		col.style.backgroundColor = item.activeFlag == "N" && (index == 0 || index==items.length-1)? 
									"#FFCCCB":
									col.style.backgroundColor;
		row.appendChild(col);
		tBody.appendChild(row);
	});
	table.appendChild(tBody);
}
function closeTablePopup(){
	document.getElementById("tablePopup").style.display="none";
	document.getElementById("pageShadow").style.display="none";
}
function changeCaret(name){
	var hiddenForm = document.getElementById(name+"FormHidden");
	var uploadHeader = document.getElementById(name+"FormHeader");
	var uploadCaret = document.getElementById(name+"Caret");
	if(uploadHeader.classList.contains("collapsedHeader")){
		uploadHeader.classList.remove("collapsedHeader");
	}
	else {
		uploadHeader.classList.add("collapsedHeader");
	}
	if(uploadCaret.classList.contains("fa-caret-down")){
		uploadCaret.classList.remove("fa-caret-down");
		uploadCaret.classList.add("fa-caret-up");
	}else {
		uploadCaret.classList.remove("fa-caret-up");
		uploadCaret.classList.add("fa-caret-down")
	}
	hiddenForm.className = hiddenForm.className == "hide"?"":"hide";
}
function formatDate(date){
	let year = date.getFullYear();
	let month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-indexed, add 1
	let day = date.getDate().toString().padStart(2, '0');
	return `${year}-${month}-${day}`;
}
function shadowMatchTable(){
	let loader = document.getElementById("tableLoader");
	let tableShadow  = document.getElementById("tableShadow");
	let activityTable = document.getElementById("activityTable");
	tableShadow.style.width = activityTable.offsetWidth +"px";
	tableShadow.style.height = activityTable.offsetHeight+"px";
	tableShadow.style.display = "block";
	loader.classList.remove("hide");
}
function removeTableShadow(){
	let tableShadow = document.getElementById("tableShadow");
	let loader = document.getElementById("tableLoader");
	tableShadow.style.display = "none";
	loader.classList.add("hide");
}
function freezeTable(){
	shadowMatchTable();
	window.onresize = shadowMatchTable;
	window.onload = shadowMatchTable;
}

function unfreezeTable(){
	window.onresize = null;
	window.onload = null;
	removeTableShadow();
}
