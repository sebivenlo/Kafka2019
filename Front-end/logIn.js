
var users=[{
    "user":"bader",
    "password":"123456"},{
    "user":"Ian",
    "password":"123456"
            }]




$(document).ready(function(){

    $("#login").click(function(){

        
       
       
        users.forEach(user=> {if(user.user==$("#username").val()){
           if(user.password==$("#password").val()){

            sessionStorage.setItem("user",user.user);
            window.location.replace("index.html");
           }  
         

        }})
    });
    
      
});