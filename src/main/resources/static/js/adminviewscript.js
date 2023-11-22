function submitUserForm(event){
	let id = Number.parseInt(document.getElementById("userSelect").value);
	let user = adminglobals.users[0];
	for(let x = 1; x < adminglobals.users.length; x++){
		if(id == adminglobals.users[x].id){
			user = adminglobals.users[x]
		}
	}
	let role = document.getElementById("roleSelect").value;
	user.role = role;
	event.preventDefault();
	return new Promise((resolve)=>{
		fetch(`/update/user?id=${id}`, {
			method:"POST",
			headers:{
				"Content-Type":"application/json"
			},
			body:JSON.stringify({
				'id':user.id,
				'email':user.email,
				'role':user.role,
				'privileges':user.privileges
			})
			
		}).then((response)=>{
			if(!response.ok){
				throw new Error(response.json());
			}
			return response.json();
		}).then((response)=>{
			resolve();
			location.reload();
			
		});
	})
}
function updateUserForm(users){
	//get select element and clear out the users
	let userSelect =  document.getElementById("userSelect");
	userSelect.innerHTML = "";
	users.forEach((user)=>{
		let option = document.createElement("option");
		option.innerText = user.email;
		option.value = user.id;
		userSelect.appendChild(option);
	});
}
function createPrivilegeString(privileges){
	let privilegeString = "";
	privileges.forEach((privilege,index)=>{ 
		privilegeString += index!=0?", " + privilege:privilege;
	});
	return privilegeString;

}
function clearTable(){
	let entries = document.querySelectorAll(".dynamicRowEntry");	
	entries.forEach((entry)=>{
		entry.remove();
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
			adminglobals.users = data;
			clearTable();
			updateUserForm(data);
			data.forEach((user)=>{
				let tBody = document.getElementById("itemBody");
				let tRow = document.createElement("tr");
				tRow.classList.add("dynamicRowEntry")
				let tRole = document.createElement("td");
				let tUsername = document.createElement("td");
				let tPrivileges = document.createElement("td");
				tRole.innerText = user.role;
				tUsername.innerText = user.email;
				tPrivileges.innerText = createPrivilegeString(user.privileges);
				tRow.appendChild(tRole);
				tRow.appendChild(tUsername);
				tRow.appendChild(tPrivileges)
				tBody.appendChild(tRow);
			});
			resolve();
		});
		
	});
}
function loadAdminViewerDOMContent(){
	loadUsers();
	document.getElementById("editUserForm").addEventListener("submit", submitUserForm);
}
document.addEventListener("DOMContentLoaded", loadAdminViewerDOMContent);