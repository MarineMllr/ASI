package com.sp.controllers;

import com.sp.dto.MoneyExchangeDTO;
import com.sp.model.InventoryCard;
import com.sp.services.InventoryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class InventoryCardRestController {
    InventoryCardService inventoryCardService;
    RestTemplate restTemplate;

    @Autowired
    public InventoryCardRestController(InventoryCardService inventoryCardService, RestTemplate restTemplate) {
        this.inventoryCardService = inventoryCardService;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inventory/cards")
    public ResponseEntity<List<InventoryCard>> getAllCardsByUser(@RequestParam (name = "user") int idUser) {
        List<InventoryCard> inventoryCardsList = inventoryCardService.getAllInventoryCardByUser(idUser);
        if(inventoryCardsList.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(inventoryCardsList, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST,value="/inventory/add")
    public ResponseEntity<?> addCard(@RequestParam (name = "card") int idCard, @RequestParam (name = "user") int user){
        InventoryCard inventoryCard = new InventoryCard();
        inventoryCard.setIdCard(idCard);
        inventoryCard.setIdUser(user);
        InventoryCard createdCard = inventoryCardService.addInventoryCard(inventoryCard);

        return new ResponseEntity<>(createdCard, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/card/sell")
    public ResponseEntity<?> sellCard(@RequestParam (name = "id") int id, @RequestParam (name = "price") int price) {
        InventoryCard inventoryCard = inventoryCardService.findById(id);
System.out.println(inventoryCard.getId());
        if (!inventoryCardService.inventoryCardExistsById(id)) {
            return new ResponseEntity<>("La carte n'existe pas", HttpStatus.CONFLICT);
        } else {
            MoneyExchangeDTO request = new MoneyExchangeDTO(inventoryCard.getIdUser(), price);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<MoneyExchangeDTO> entity = new HttpEntity<MoneyExchangeDTO>(request,headers);

            if(restTemplate.exchange("http://localhost:8083/user/money/add", HttpMethod.POST, entity, String.class).getStatusCode() == HttpStatus.OK);

            inventoryCardService.removeInventoryCard(id);
            return new ResponseEntity<>("Vente effectuée", HttpStatus.OK);
        }
    }
}
