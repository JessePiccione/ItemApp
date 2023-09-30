document.getElementById("addButton").addEventListener("click", addHandler);
document.getElementById("updateButton").addEventListener("click", updateHandler);
document.getElementById("deleteButton").addEventListener("click", deleteHandler);
document.getElementById("idSearchButton").addEventListener("click", function(event){
	const data = document.getElementById("idSearch").value;
	location.assign("/item/id/"+data);
});

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
			location.reload();
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
				'id': "",
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
			body.id = ids[x];
			request.open("PATCH", "/item");
			request.setRequestHeader("Content-Type","application/json");
			request.onload = () =>{
				location.reload()
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
			location.reload();
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
	tr.innerHTML = "<td></td>"+
				   "<td><input id='date' type='text' name='date' placeholder='Enter a date ...' value=''></td>"+
				   "<td><input id='brand' type='text' name='brand' placeholder='Enter a brand...' value=''></td>"+
				   "<td><input id='dept' type='text' name='dept' placeholder='Enter a dept...' value=''></td>"+
				   "<td><input id='itemClass' type='text' name='itemClass' placeholder='Enter a Class...' value=''></td>"+
				   "<td><input id='originalPrice' type='text' name='originalPrice' placeholder='Enter a Price...' value=''></td>"+ 
				   "<td><input id='salePrice' type='text' name='salePrice' placeholder='Enter a Price...' value=''></td>"+
				   "<td><input id='activeFlag' type='text' name='activeFlag' placeholder='Enter a Flag...' value=''></td>"+
				   "<td><input id='imageFile' type='text' name='imageFile' placeholder='Enter a image file...' value=''></td>" +
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