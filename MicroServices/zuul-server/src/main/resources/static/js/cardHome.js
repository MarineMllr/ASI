//TODO : Changer les href en fonction du bon port. Les
$(document ).ready(function(){

    var value = getConnectionInfos();
    console.log(usr_infos);

    $("#playButtonId").click(function(){
        alert("Play button clicked ");
        //TO DO
    });    
    $("#buyButtonId").click(function(){
        window.location.href = '/cardListBuy.html';
        //TO DO
    });    
    $("#sellButtonId").click(function(){
        window.location.href = '/cardListSell.html';
        //TO DO
    });    
});

