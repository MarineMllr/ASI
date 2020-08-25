//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
$(document).ready(function () {

    $("button").click(function (e) {
        e.preventDefault();
        var data = $("#register").serialize();
        $.ajax({
            url: '/user-service/user/add',
            type: 'POST',
            data: {"name": $("#name").val(), "surname": $("#surname").val(), "password": $("#password").val()}
        })
            .done(function (data) {
                console.log(data);
                $("#check").text("Inscription OK");
                window.location = "/login.html";
            })

            .fail(function (data, textStatus, errorThrown) {
                console.log("laaa");
                console.log(textStatus);
                if (data.status === 209) {
                    $("#check").text("surname already used");
                } else {
                    $("#check").text("Une erreur est survenue, merci de recommencer");
                }

            });


    })

});

