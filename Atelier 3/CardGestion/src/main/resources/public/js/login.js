$(document ).ready(function() {
    $("#loginButton").click(function () {
        var data = {surname: $("#surname").val(), password: $("#password").val()};
        console.log(data);
        $.post({
            url: "/user/login",
            data: data,
            success: function(data, status, xhr){console.log(status)},
        });
    });
});