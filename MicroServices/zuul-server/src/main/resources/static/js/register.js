//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
$(document).ready(function(){

    $("button").click(function(e) {
        e.preventDefault();
        var data = $("#register").serialize();
        $.ajax({
            url : '/user-service/user/add',
            type : 'POST',
            data : {"name": $("#name").val(), "surname": $("#surname").val(), "password": $("#password").val()},
            dataType : 'JSON',

        success : function(data, statut){
                if(data == "true")
                {
                    $("#check").text("Inscription OK");
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
             console.log(statut);
             $("#check").text("Une erreur est survenue, merci de recommencer");

         }

        })

    })

});

