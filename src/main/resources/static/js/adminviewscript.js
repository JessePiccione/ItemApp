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
}
document.addEventListener("DOMContentLoaded", loadAdminViewerDOMContent);