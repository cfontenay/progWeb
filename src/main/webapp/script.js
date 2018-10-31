document.getElementById("all").addEventListener("click", getAllUsers);
document.getElementById("get").addEventListener("click", getUser);
document.getElementById("add").addEventListener("click", addUser);
document.getElementById("set").addEventListener("click", setUser);
document.getElementById("delete").addEventListener("click", delUser);
let url = "/rest/";
function getAllUsers(){
	$.ajax({
		url:url+"users",
		type:"GET",
		dataType: "json",
		success:function(data, status){
			console.log("Data: " + JSON.stringify(data));
		},
		error:function(request, msg,erro){
			alert("error get users");
		}
	});
}
function getUser(){
	let id = document.getElementById("getId").value;
	$.ajax({
		url:url+"user/"+id,
		type:"GET",
		success: function(data){
			console.log("Data: " + JSON.stringify(data) + "\nStatus: " + status);
		},
		error: function(request, msg, error){
			alert("error delete" + data);
		}
	});
	
}
function addUser(){
	let fName = document.getElementById("addFName").value;
	let lName = document.getElementById("addLName").value;
	const user = getJsonToSend(lName,fName);
	
	$.ajax({
		url:url+"user/add",
		type:"POST",
		data: JSON.stringify(user),
		contentType:"application/json",
		success: function(data, status){
			alert("success add");
		},
		error: function(request,msg,error){
			alert("error add");
		}
	});
	
}
function setUser(){
	let fName = document.getElementById("setFName").value;
	let lName = document.getElementById("setLName").value;
	let id = document.getElementById("setId").value;

	const user = getJsonToSend(lName, fName);

	$.ajax({
		url:url+"user/"+id,
		type:"PUT",
		data: JSON.stringify(user),
		contentType: "application/json",
		success: function(data,status){
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

		url:url+"user/"+id,
		type:"DELETE",
		success: function(data){
			alert("success "+data);
		},
		error: function(request, msg, error){
			alert("error delete" + msg);
		}
	});
}
function getJsonToSend(lName, fName){
	return {
			"firstName" : fName,
			"lastName" : lName
		};
}