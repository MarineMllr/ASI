//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
var user;
var selected_card_id;
$(document ).ready(function(){
//     var value = getConnectionInfos();
//     user = usr_infos;
    $.ajax({
        url:"/user-service/user/1",
        type: "GET",
        async: false

    }).done(function(user_received){
        console.log(user_received)
        user = user_received;
    });



    // if (user != null && cookieObj != null) {
        console.log(user["surname"]);
        $("#userNameId").text(user["surname"]);
        $("#currentMoney").text(user["money"]);
        $.ajax({
            url: 'sale-service/inventory/cards',
            type: 'GET',
            data: {user: user["id"]}
        })
            .done(function(list_to_display) {
                console.log(list_to_display);
                $.each(list_to_display, function(key, inventCard) {
                    $.ajax({
                        url: 'card-service/card/' + inventCard["idCard"],
                        type: 'GET'
                    })
                        .done(function(card) {
                            console.log(card);
                            addCardToList(
                                card["family"],
                                card["imgUrl"],
                                card["name"],
                                card["description"],
                                card["hp"],
                                card["energy"],
                                card["attack"],
                                card["defence"],
                                card["price"],
                                card["id"],
                                inventCard["id"]
                            )
                        })
                        .fail(function (data, textStatus, errorThrown) {
                            console.log("laaabis");
                            console.log(textStatus);
                        });
                    })
                })
            .fail(function (data, textStatus, errorThrown) {
                console.log("laaa");
                console.log(textStatus);
            })


    // }
});

function fillCurrentCard(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence,price){
    //FILL THE CURRENT CARD
    $('#cardFamilyNameId')[0].innerText=familyName;
    $('#cardImgId')[0].src=imgUrl;
    $('#cardNameId')[0].innerText=name;
    $('#cardDescriptionId')[0].innerText=description;
    $('#cardHPId')[0].innerText=hp+" HP";
    $('#cardEnergyId')[0].innerText=energy+" Energy";
    $('#cardAttackId')[0].innerText=attack+" Attack";
    $('#cardDefenceId')[0].innerText=defence+" Defence";
    $('#cardPriceId')[0].innerText=price+" $";
};


function addCardToList(familyName,imgUrl,name,description,hp,energy,attack,defence,price,id, idInventory){

    content="\
    <td> \
    <img  class='ui avatar image' src='"+imgUrl+"'> <span>"+name+" </span> \
    </td> \
    <td>"+description+"</td> \
    <td>"+familyName+"</td> \
    <td>"+hp+"</td> \
    <td>"+energy+"</td> \
    <td>"+attack+"</td> \
    <td>"+defence+"</td> \
    <td>"+price+"$</td>\
    <td>\
        <div class='ui vertical animated button' id='item_"+idInventory+"' tabindex='0' onclick='sell_item(this, "+price+")'>\
            <div class='hidden content'>Sell</div>\
    <div class='visible content'>\
        <i class='shop icon'></i>\
    </div>\
    </div>\
    </td>";

    $('#cardListId tr:last').after('<tr onclick="selectElementCardList(this)">'+content+'</tr>');


};

function selectElementCardList(x){
    var tr = document.getElementById("cardListId").getElementsByTagName("tr");
    for(i=1;i<tr.length;i++){
        tr[i].style.backgroundColor = "white";
    }
    x.style.backgroundColor = "red";
    var td = x.getElementsByTagName("td");
    var description = td[1].textContent;
    var family = td[2].textContent;
    var hp = td[3].textContent;
    var energy = td[4].textContent;
    var defence = td[5].textContent;
    var attack = td[6].textContent;
    var price = td[7].textContent;
    var name = td[0].getElementsByTagName("span")[0].textContent;
    var imgUrl = td[0].getElementsByTagName("img")[0].src;
    document.getElementById("cardNameId").innerHTML = name;
    document.getElementById("cardDescriptionId").innerHTML = description;
    document.getElementById("cardFamilyNameId").innerHTML = family;
    document.getElementById("cardHPId").innerHTML = hp;
    document.getElementById("cardEnergyId").innerHTML = energy;
    document.getElementById("cardDefenceId").innerHTML = defence;
    document.getElementById("cardAttackId").innerHTML = attack;
    document.getElementById("cardPriceId").innerHTML = price;
    document.getElementById('cardImgId').src = imgUrl;
    selected_card_id = td[8].getElementsByTagName("div")[0].id;
}

function sell_item(x, price){
    var item_id = x.id;
    item_id = item_id.replace("item_","");
    console.log(item_id);
    console.log(price)

    $.ajax({
        url: 'sale-service/card/sell',
        type: 'POST',
        data: {id: item_id, price: parseInt(price)}
    })
        .done(
            function(data){
                console.log(data);
                location.reload();
            }
        )
        .fail()


}

function sell_item_from_selected(x) {
    var item_id = selected_card_id;
    item_id = item_id.replace("item_","");
    var price = $("#cardPriceId").text();

    $.ajax({
        url: 'sale-service/card/sell',
        type: 'POST',
        data: {id: item_id, price: parseInt(price)}
    })
        .done(
            function(data){
                location.reload();
            }
        )
        .fail()
}