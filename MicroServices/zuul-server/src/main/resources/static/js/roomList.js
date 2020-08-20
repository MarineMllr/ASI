$(document ).ready(function(){
    
   
    
    for(i=0;i<5;i++){
        addRoomToList(i,"room "+ i,"user" +i, 500);
    }
    
     $("#createRoomButtonId").click(function(){
        alert("Create Card button clicked ");
        //TO DO
    }); 
    
});


function addRoomToList(id,name, user, bet){
    
    content="<td> "+name+" </td> \
                            <td> "+user+" </td> \
                            <td> "+bet+" $</td> \
                            <td> \
                                <div class='center aligned'> \
                                    <div class='ui  vertical animated button' tabindex='0' onClick='onRoomSelected("+id+")'> \
                                        <div class='hidden content'>Play</div> \
                                        <div class='visible content'> \
                                            <i class='play circle icon'></i> \
                                        </div> \
                                    </div> \
                                </div> \
                            </td>";
    
    $('#roomListId tr:last').after('<tr>'+content+'</tr>');
    
    
};

function onRoomSelected(id){
    alert("Room selected : " +id);
}