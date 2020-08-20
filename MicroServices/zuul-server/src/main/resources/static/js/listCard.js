//TODO : A delete ?
$(document ).ready(function(){

    /*$.get(
        'CardBeanRestCtrl',
        'false',
        affiche_cartes,
        'text'
    );*/

    $.ajax({
        url : '/cardBean/cardListBuy',
        type : 'GET',
        data : 'surname=pass',
        dataType : 'html',
        success : function(code_html, statut){
            $(code_html).appendTo("#commentaires"); // On passe code_html à jQuery() qui va nous créer l'arbre DOM !
        },

        error : function(resultat, statut, erreur){

        },

        complete : function(resultat, statut){

        }

    });

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


function addCardToList(imgUrlFamily,familyName,imgUrl,name,description,hp,energy,attack,defence,price){
    
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
        <div class='ui vertical animated button' tabindex='0'>\
            <div class='hidden content'>Sell</div>\
    <div class='visible content'>\
        <i class='shop icon'></i>\
    </div>\
    </div>\
    </td>";
    
    $('#cardListId tr:last').after('<tr>'+content+'</tr>');
    
    
};