function loadNavbar(event){
	document.getElementById("adminNav").addEventListener("click", (event)=>{
		location.assign("/adminview");
	});
	document.getElementById("homeNav").addEventListener("click",(event)=>{
		location.assign("/home");
	});
	document.getElementById("activityNav").addEventListener("click",(event)=>{
		location.assign("/itemview");
	});
}
function loadUsers(event){
	return new Promise((resolve)=>{
		fetch("/user",{method:"GET"}).then((response)=>{
			if(!response.ok){
				throw new Error(response.json());
			}
			return response.json();
		}).then(data => {
			data.forEach((user)=>{
				console.log(user);
			})
			
		});
		
	});
}
function loadMainPageContent(event){
	loadNavbar();
	loadUsers();
}
document.addEventListener("DOMContentLoaded", loadMainPageContent);

