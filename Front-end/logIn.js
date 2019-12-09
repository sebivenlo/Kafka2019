
var users=[{
    "user":"bader",
    "password":"123456"},{
    "user":"Ian",
    "password":"123456"
            }]


            let category=["Sport","Politic","Finincial","Science","Health"];
            let articels=[{"category":"Science","Title":"How real-world science sets The Expanse apart from other sci-fi shows","text":"On 13 December, Amazon Prime will air the fourth season of The Expanse, a hardboiled space drama renowned for its working-class characters and real-world space physics. Showrunner Naren Shankar is part of the reason the science checks out. The veteran writer and producer for programs such as Star Trek: The Next Generation, Farscape, and the police procedural CSI: Crime Scene Investigation, has a doctorate in applied physics and electrical engineering."},
            {"category":"Science","Title":"Stem cells reshape a lymphatic niche","text":"Adult stem cells can both self-renew and regenerate new tissue upon demand. They reside in microenvironments (niches) that balance these decisions to avoid tissue overgrowth, cancer, and aging. Using murine skin as a model, Gur-Cohen et al. uncovered a lymphatic network of capillaries associated with the stem cell niche of hair follicles (see the Perspective by Harvey). Stem cells reshaped their lymphatic environment by switching their secretome to coordinate lymphatic-niche association"},
            {"category":"Sport","Title":"Champions League permutations","text":"We already know eight of the teams that have qualified - City, Spurs, Paris St-Germain, Bayern Munich, Juventus, Real Madrid, Barcelona and RB Leipzig - with another eight places still on offer."},
            {"category":"Sport","Title":"Premier League stats","text":"It's official. Jurgen Klopp has landed another Christmas number one with Liverpool's 2019 title challenge entry."},
            {"category":"Politic","Title":"Donald Trump now has an obvious path to a second term","text":"Yes, you can quibble about whether the strong November report or the upward revisions in job gains for the last two months or the broader upswing in the economy is due to Trump and his policies. But what we know -- both from our political history and more recent polling -- is that presidents get credit when the economy is strong and blame when it is weak.."},                      
            {"category":"Finincial","Title":"When Volcker ruled the Fed, ‘people thought they’d never buy a home again’","text":"Volcker allowed the fed funds rate, now topped out at 1.75%, to rise over 20%, and with it went the interest on home mortgages and everything else. The 30-year mortgage rate spiked into the high teens in late 1981 and continued at double digits until 1990."},     
            {"category":"Health","Title":"Probiotics: Benefits for immunity may depend on sex","text":"For the first time, a new study shows that Probiotics and prebiotics can have different effects on the immune system in male, compared [with] female, piglets.The findings have important implications for research on the effects of these supplements, as well as for personalized, probiotics-based treatments."},  ]
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