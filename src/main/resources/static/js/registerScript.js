document.getElementById("registerButton").addEventListener("click",function(event){
	var request = new XMLHttpRequest();
	request.open("POST","/login/register");
	request.setRequestHeader('Content-Type', 'application/json');
	request.onload = () => {
		location.assign("/login")
	}
	const body = {
		username: document.getElementById("username").value,
		password: document.getElementById("password").value
	}
	request.send(JSON.stringify(body));
});