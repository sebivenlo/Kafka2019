

//var category=["Sport","News","Egonomy","Finincial","Sience","Health"];

var select=$("#category"); 
var title=$("#title");
var article=$("#text");



$(document).ready(function(){
  sessionStorage.setItem("user","bader ammoun");
  
    
    category.forEach(function(item,index){
          select.append("<p id="+item+">"+item+"</p>");
    });
    $("#category p").on(
      {
      
        "click":function(event){
           
      
            $("#title p").remove();
            $("#text p").remove();
            $('#title p').slideToggle();
            event.target.id;
           articels.forEach(function(item,index){
    
            if(item.category===event.target.id){
                  var id=item.Title.replace(/\s/g,'');
                  title.append("<p id="+id+">"+item.Title+"</p>");
                  //if($('#title').css('display')==='none'){
         
         
                  //}
            }  
     
           })
    
      },
        "mouseover":function(){
            $(this).css("cursor","pointer")
            $(this).css("background-color","red")
      },
        "mouseleave":function(){
    
            $(this).css("background-color","rgb(10, 8, 4)")
    }
  });
  $("#title").on("click","p",function(event){
       
        
       
        $("#text p").remove();
        $('#text p').slideToggle();
        
        event.target.id;
        
        articels.forEach(function(item,index){
            let item2={
              "user":sessionStorage.getItem('user'),
              "article":item.title,
              "action":"read"

            };
            if(item.Title.replace(/\s/g,'')===event.target.id){
                 
                  $("#text").append("<p>"+item.text+"</p>");
                 
                  var xhttp = new XMLHttpRequest();
                  xhttp.open("POST","http://localhost:8080/Article");
                  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                  xhttp.send(JSON.stringify(item2));
            }  

        })
        
  });
  $("#title").on("mouseover","p",function(){
                      $(this).css("cursor","pointer")
                      $(this).css("background-color","red")
                  });
  $("#title").on("mouseleave",'p',function(){
    
                  $(this).css("background-color","rgb(10, 8, 4)")
                  }
  );

$("button").on("click",function(){

  window.location.replace("CreateArticle.html");
  })
});


