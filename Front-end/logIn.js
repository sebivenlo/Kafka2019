
var users=[{
    "user":"bader",
    "password":"123456"},{
    "user":"Ian",
    "password":"123456"
            }]


            let category=["Sport","News","Egonomy","Finincial","Sience","Health"];
            let articels=[{"category":"Sport","Title":"Ronaldo Sign contract withe Juvantues","text":"dvgdfgfgrfgrgrgrgbrbrgt"},{"category":"Sport","Title":"Barcalona have been beaten among his audience and statuim","text":"dvgdfgfgrfgrgrgrgbrbrgt"},
            {"category":"Sport","Title":"kaka came back to mailan","text":"kjhkjhkjh"},{"category":"Health","Title":"the human can extend thier life twice","text":"kjhjkhjhl;xx"}]
;
$(document).ready(function(){

    $("#login").click(function(){

        
       
       
        users.forEach(user=> {if(user.user==$("#username").val()){
           if(user.password==$("#password").val()){

            sessionStorage.setItem("user",user.user);
            sessionStorage.setItem("articels",(JSON.stringify(articels)));
            window.location.replace("index.html");
           }  
         

        }})
    });
    
      
});