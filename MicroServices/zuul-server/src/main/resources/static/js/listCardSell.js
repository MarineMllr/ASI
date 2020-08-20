//TODO : changer les url ajax : maintenant elle suivent la logique suivante => localhost:portMS/nomMS/nomduRequestMapping
var user;
$(document ).ready(function(){
    var value = getConnectionInfos();
    user = usr_infos;


    if (user != null && cookieObj != null) {
        console.log(user["surname"]);
        $.ajax({
            url : '/cardBean/cardListSell',
            type : 'GET',
            data : {surname: user["surname"], token : cookieObj["token"]},
            //dataType : 'html',
            success : function(list_to_display){
                console.log(list_to_display);
                for(i=0;i<list_to_display.length;i++){
                    addCardToList("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DC_Comics_logo.png/280px-DC_Comics_logo.png",
                        list_to_display[i]["family"],
                        list_to_display[i]["imgUrl"],
                        list_to_display[i]["name"],
                        list_to_display[i]["description"],
                        list_to_display[i]["hp"],
                        list_to_display[i]["energy"],
                        list_to_display[i]["attack"],
                        list_to_display[i]["defence"],
                        list_to_display[i]["price"],
                        list_to_display[i]["id"]);
                    //(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence,price)
                }
            },

            error : function(resultat, statut, erreur){

            },

            complete : function(resultat, statut){

            }

        });
    }




    //fillCurrentCard("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DC_Comics_logo.png/280px-DC_Comics_logo.png","DC comics","http://www.guinnessworldrecords.com/images/superlative/superheroes/GWR-Superheroes-SUPERMAN.svg","SUPERMAN","The origin story of Superman relates that he was born Kal-El on the planet Krypton, before being rocketed to Earth as an infant by his scientist father Jor-El, moments before Krypton's destruction. Discovered and adopted by a farm couple from Kansas, the child is raised as Clark Kent and imbued with a strong moral compass. Early in his childhood, he displays various superhuman abilities, which, upon reaching maturity, he resolves to use for the benefit of humanity through a 'Superman' identity.",50,100,17,8,100);


    /*for(i=0;i<5;i++){
        addCardToList("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DC_Comics_logo.png/280px-DC_Comics_logo.png","DC comics","http://www.guinnessworldrecords.com/images/superlative/superheroes/GWR-Superheroes-SUPERMAN.svg","SUPERMAN","The origin story of Superman relates that he was born Kal-El on the planet Krypton, before being rocketed to Earth as an infant by his scientist father Jor-El, moments before Krypton's destruction. Discovered and adopted by a farm couple from Kansas, the child is raised as Clark Kent and imbued with a strong moral compass. Early in his childhood, he displays various superhuman abilities, which, upon reaching maturity, he resolves to use for the benefit of humanity through a 'Superman' identity.",50,100,17,80,100);
    }*/


});

function affiche_cartes(text_recu) {

}


function fillCurrentCard(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence,price){
    //FILL THE CURRENT CARD
    $('#cardFamilyImgId')[0].src=imgUrlFamily;
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


function addCardToList(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence,price,id){

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
        url : '/cardTransaction/doSellCard',
        type : 'GET',
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