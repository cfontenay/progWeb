document.getElementById("all").addEventListener("click", getAllUsers);
document.getElementById("add").addEventListener("click", addUser);
document.getElementById("set").addEventListener("click", setUser);
document.getElementById("delete").addEventListener("click", delUser);
let url = "/rest/";
function getAllUsers(){
		$.get(url+"users", function(data, status){
			console.log("Data: " + data + "\nStatus: " + status);
		});
}
function addUser(){
	let fName = document.getElementById("addFName").value;
	let lName = document.getElementById("addLName").value;
	$.post(url+"add", {firstName: fName, lastName:lName}, function(data){
		alert("data:"+data);
	});
}
function setUser(){
	let fName = document.getElementById("setFName").value;
	let lName = document.getElementById("setLName").value;
	let id = document.getElementById("setId").value;

	const user = {
		firstName : fName,
		lastName : lName
	};

	$.ajax({
		url:url+id,
		type:"PUT",
		data: user,
		dataType: "json",
		success: function(data){
			alert("success put");
		},
		error: function(request, msg, error){
			alert("error put");
		}
	});
}
function delUser(){
	let id = document.getElementById("deleteId").value;
	$.ajax({

		url:url+id,
		type:"DELETE",
		data : {id: id},
		dataType: "json",
		success: function(data){
			alert("success "+data);
		},
		error: function(request, msg, error){
			alert("error delete" + data);
		}
	});
}