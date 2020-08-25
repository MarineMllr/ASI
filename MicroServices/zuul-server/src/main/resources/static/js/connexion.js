//TODO : Changer les href en fonction du bon port. Les
var cookieObj = null;
var usr_infos = null;
function getConnectionInfos() {
    var cookies = document.cookie;

    console.log(cookies);
    if(cookies.includes("session=")){
        cookieObj = cookies.replace("session=","");
        cookieObj = JSON.parse(cookieObj);

        if(cookieObj!=null){
            cookieObj=cookieObj["session"];
        }
        console.log(cookieObj);
    }

    if(cookieObj!=null){
        console.log(cookieObj["token"]);
        $.ajax({url : "/user-service/getUserInfoWithToken",
            data : {surname: cookieObj["surname"],token: cookieObj["token"]},
            method : 'POST',
            async: false}).done(function(data,status){
                if(data!=""){
                    usr_infos = JSON.parse(data);
                    console.log(usr_infos);
                    if(usr_infos!=null) {
                        $("#userNameId").text(usr_infos["surname"]);
                        $("#currentMoney").text(usr_infos["solde"]);
                    }
                    return usr_infos;
                } else{
                    alert("Session invalide ou expirée");

                }
        });

    } else {
        alert("Vous n'êtes pas connecté, vous allez être redirigé");
        window.location.href = '/login.html';
    }
}