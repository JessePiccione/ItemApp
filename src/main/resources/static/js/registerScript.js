document.getElementById("registerButton").addEventListener("click",function(event){
	var request = new XMLHttpRequest();
	request.open("POST","/create/user");
	request.setRequestHeader('Content-Type', 'application/json');
	request.onload = () => {
		if(request.status >= 400){
			alert("Error: " + JSON.parse(request.response).Error);
		}
		else {
			alert("Success: " + JSON.parse(request.response).Success);
		}
	}
	const body = {
		username: document.getElementById("username").value,
		password: document.getElementById("password").value,
		role: document.getElementById("role").value
	}
	request.send(JSON.stringify(body));
});