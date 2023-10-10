document.addEventListener("DOMContentLoaded",function(event){
	var len, end;
	if((len = document.getElementById("idSearch").value.length) > 0){
		end = document.getElementById("idSearch");
	}
	else if((len = document.getElementById("skuSearch").value.length) > 0){
		end = document.getElementById("skuSearch");
	}
	else if((len = document.getElementById("dateSearch").value.length) > 0){
		end = document.getElementById("dateSearch");
	}
	else if((len = document.getElementById("brandSearch").value.length) > 0){
		end = document.getElementById("brandSearch");
	}
	else if((len = document.getElementById("deptSearch").value.length) > 0){
		end = document.getElementById("deptSearch");
	}
	else if((len = document.getElementById("classSearch").value.length) > 0){
		end = document.getElementById("classSearch");
	}
	else if((len = document.getElementById("activeFlagSearch").value.length) > 0){
		end = document.getElementById("activeFlagSearch");
	}
	else if((len = document.getElementById("imageFileSearch").value.length) > 0){
		end = document.getElementById("imageFileSearch");
	}
	loadInnerDOMContent(event);
	if (end.setSelectionRange) {
		end.focus();
		end.setSelectionRange(len, len);
    }
    else if (end.createTextRange) {
        var t = end.createTextRange();
        t.collapse(true);
        t.moveEnd('character', len);
        t.moveStart('character', len);
        t.select();
    }
    
});
function loadInnerDOMContent(event){
	const url =	document.getElementById("url").value;
	var request = new XMLHttpRequest();
	request.open("GET", url);
	request.onload = () => {
		loadItems(request.response);
	}
	request.send();
	
}
function loadItems(response){
	console.log(response);
}
//crud buttons 
document.getElementById("addButton").addEventListener("click", addHandler);
document.getElementById("updateButton").addEventListener("click", updateHandler);
document.getElementById("deleteButton").addEventListener("click", deleteHandler);
let typingTimer;

//search fields
document.getElementById("idSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {
    // This code will be executed when typing has ended
 		const data = document.getElementById("idSearch").value;
		location.assign("/item/id/"+data);
  	}, 1000);
});
document.getElementById("idSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function () {
    // This code will be executed when typing has ended
 		const data = document.getElementById("idSearch").value;
		location.assign("/item/id/"+data);
  	}, 1000);
});
document.getElementById("dateSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("dateSearch").value;
		location.assign("/item/date/"+data);
	}, 1000);
});
document.getElementById("brandSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("brandSearch").value;
		location.assign("/item/brand/"+data);
	}, 1000);
});
document.getElementById("deptSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("deptSearch").value;
		location.assign("/item/dept/"+data);
	}, 1000);
	
});
document.getElementById("classSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("classSearch").value;
		location.assign("/item/itemClass/"+data);
	}, 1000);
	
});
document.getElementById("activeFlagSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("activeFlagSearch").value;
		location.assign("/item/activeFlag/"+data);
	}, 1000);
});
document.getElementById("imageFileSearch").addEventListener("input", function(event){
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function(){
		const data = document.getElementById("imageFileSearch").value;
		location.assign("/item/imageFile/"+data);
	}, 1000);
});
//sorting implentaion

function changeSort(s){
	location.assign(location.pathname + "?sort="+s);
}

//handles adds to the server
function addHandler(event){
	if(hasTextFields()){
		insertTextFields(event);	                                                                                                                                             
	}
	else {
		var request = new XMLHttpRequest();
		request.open("POST", "/item");
		request.setRequestHeader("Content-Type","application/json");
		const body ={
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
		request.onload = () =>{
			location.assign(location.pathname);
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
		var body ={
				'uniqueId': '',
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
		for (var x = 0; x < ids.length; x++ ){
			request = new XMLHttpRequest();
			body.uniqueId = ids[x];
			request.open("PATCH", "/item");
			request.setRequestHeader("Content-Type","application/json");
			request.onload = () =>{
				location.assign(location.pathname);
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
		request.send();
		request.onload = () =>{
			location.assign(location.pathname);
		};
		event.preventDefault();
	}
}
//helper functions 
function hasTextFields(){
	return document.getElementById('addRow').innerHTML == "";
}

function insertTextFields(event){
	var tr = document.getElementById('addRow');
	tr.innerHTML = "<td><input id='id' type='number' name='id' placeholder='Enter an id ...' value=''></td>"+
				   "<td><input id='sku' type='number' name='sku' placeholder='Enter a Sku ...' value=''></td>"+
				   "<td><input id='date' type='date' name='date' placeholder='Enter a date ...' value=''></td>"+
				   "<td><input id='brand' type='text' name='brand' placeholder='Enter a brand...' value=''></td>"+
				   "<td><input id='dept' type='text' name='dept' placeholder='Enter a dept...' value=''></td>"+
				   "<td><input id='itemClass' type='text' name='itemClass' placeholder='Enter a Class...' value=''></td>"+
				   "<td><input id='originalPrice' type='number' name='originalPrice' placeholder='Enter a Price...' value=''></td>"+ 
				   "<td><input id='salePrice' type='number' name='salePrice' placeholder='Enter a Price...' value=''></td>"+
				   "<td><input id='activeFlag' type='text' name='activeFlag' placeholder='Enter a Flag...' value=''></td>"+
				   "<td><input id='imageFile' type='text' name='imageFile' placeholder='Enter an image file...' value=''></td>" +
				   "<td><input id='variants' type='text' name='variants' placeholder='Enter variants'></td>";
	event.preventDefault();
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