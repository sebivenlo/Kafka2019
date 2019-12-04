$(document).ready(function(){

$('#b').on("click",function(){

  let item2={
    "user":sessionStorage.getItem('user'),
    "article":$("#title").val(),
    "action":"create"

  };
 
  var xhttp = new XMLHttpRequest();
   xhttp.open("POST","http://localhost:8080/Article");
   xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
   xhttp.send(JSON.stringify(item2));
  });  

   

  });
  
  
