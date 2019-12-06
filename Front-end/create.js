$(document).ready(function(){

$('#create').on("click",function(){
  console.log("fgff")
  let item2={
    "name":sessionStorage.getItem('user'), 
    "article":$("#title").val(),
    "action":"write"

  };
  let item1={
    "category":$("#category").val(),
    "Title":$("#title").val(),
    "text":$("#textarea").val(),

  }
 let articels=JSON.parse(sessionStorage.getItem("articels"));
//  console.log(articels.length())

  articels[articels.length]=item1
  sessionStorage.setItem("articels",(JSON.stringify(articels)));
  var xhttp = new XMLHttpRequest();
   xhttp.open("POST","http://localhost:8082/Article");
   xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
   xhttp.send(JSON.stringify(item2));

   window.location.replace("index.html");
  });  

   

  });
  
  
