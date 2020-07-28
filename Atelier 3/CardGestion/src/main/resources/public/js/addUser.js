$(document ).ready(function() {
    $("#addUserButton").click(function () {
        var data = { name: $("#name").val(), surname: $("#surname").val(), password: $("#password").val()};
        console.log(data)
        console.log("blabla");
        $.post({
            url: "/user/add",
            data: JSON.stringify(data),
            success: function(data, status, xhr){console.log(status)},
            dataType : "json",
            contentType : "application/json"
        });
    });
});