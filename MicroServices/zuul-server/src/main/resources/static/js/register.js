//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
$(document).ready(function(){

    $("button").click(function(e) {
        e.preventDefault();
        var data = $("#register").serialize();
        console.log(data);
        $.ajax({
            url : 'localhost:8085/user-service/user/add',
            type : 'POST',
            data : data,
            dataType : 'text',

        success : function(data, statut){
                if(data == "true")
                {
                    $("p").text("Inscription OK");
                    window.location = "/login.html";
                }
                else
                {
                    console.log(data);
                    $("#check").text("surname already used");
                }
            },

         error : function(data, statut) {
             console.log(data);
             $("#check").text("error try again");

         }

        })

    })

});

