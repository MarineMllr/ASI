//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
$(document).ready(function() {
    var cookies = document.cookie;
    var cookieObj = null;

    console.log(cookies);
    if(cookies.includes("session=")){
        cookieObj = cookies.replace("session=","");
        cookieObj = JSON.parse(cookieObj);

        if(cookieObj!=null){
            cookieObj=cookieObj["session"];
        }
    }

    if(cookieObj!=null){
        $.post( "/userApplication/getUserInfoWithToken", {surname: cookieObj["surname"], token: cookieObj["token"]},function(data,status) {
            console.log(data);
            if(data!=""){
                window.location.href = '/cardHome.html'; //one level up
            }
        });

    }
});

$("#Login").click(function() {
        var surname = $("#surname").val();
        var password = $("#password").val();
        $.post( "/userApplication/getUser", {surname: surname, password: password},function( data,status ) {
            if(data!=""){
                var jsonObject = JSON.parse(data);
                var strData = "{\"session\":{\"surname\":\""+jsonObject["surname"]+"\",\"token\":\""+jsonObject["token"]+"\"}}";
                document.cookie = "session="+strData;//jsonObject["surname"]+";token="+jsonObject["token"];
                window.location.href = '/cardHome.html'; //one level up
            } else{
                alert("Mauvais surname ou mot de passe");
            }
        });
});