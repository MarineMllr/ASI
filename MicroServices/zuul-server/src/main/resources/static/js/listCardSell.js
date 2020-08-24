//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
var user;
$(document ).ready(function(){
    var value = getConnectionInfos();
    user = usr_infos;


    if (user != null && cookieObj != null) {
        console.log(user["surname"]);
        $.ajax({
            url : 'localhost:8082/sale-service/inventory/cards/'+user["id"],
            type : 'GET',
            data : {surname: user["surname"], token : cookieObj["token"]},
            success : function(list_to_display){
                console.log(list_to_display);
                $.each(list_to_display, function(key, inventCard) {
                    $.ajax({
                        url: 'localhost:8081/card-service/card/'+inventCard["idCard"],
                        type : 'GET',
                        success : function(card) {
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
                                inventCard["idCard"]
                            )
                        }
                    })
                });
            },

            error : function(resultat, statut, erreur){

            },

            complete : function(resultat, statut){

            }

        });
    }
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


function addCardToList(familyName,imgUrl,name,description,hp,energy,attack,defence,price,id){

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
        <div class='ui vertical animated button' id='item_"+id+"' tabindex='0' onclick='sell_item(this)'>\
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
    document.getElementById("cardNameId").innerHTML = name;
    document.getElementById("cardDescriptionId").innerHTML = description;
    document.getElementById("cardFamilyNameId").innerHTML = family;
    document.getElementById("cardHPId").innerHTML = hp;
    document.getElementById("cardEnergyId").innerHTML = energy;
    document.getElementById("cardDefenceId").innerHTML = defence;
    document.getElementById("cardAttackId").innerHTML = attack;
    document.getElementById("cardPriceId").innerHTML = price;
}

function sell_item(x){
    var item_id = x.id;
    item_id = item_id.replace("item_","");
    console.log(item_id);

    $.ajax({
        url : 'localhost:8082/sale-service/card/sell',
        type : 'POST',
        data : {surname: user["surname"], token : cookieObj["token"], item_id : item_id},
        success : function(data){
            console.log(data);
            if (data==true){
                alert("Vous venez de vendre cette carte");
            } else {
                alert("Erreur lors de la vente de la carte");
            }
            location.reload();
        },

        error : function(resultat, statut, erreur){

        },

        complete : function(resultat, statut){

        }

    });
}