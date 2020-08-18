package com.sp.controllers;

import com.sp.model.InventoryCard;
import com.sp.services.InventoryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryCardRestController {
    InventoryCardService inventoryCardService;

    @Autowired
    public InventoryCardRestController(InventoryCardService inventoryCardService) {
        this.inventoryCardService = inventoryCardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/inventory/cards/{idUser}")
    public ResponseEntity<List<InventoryCard>> getAllCardsByUser(@PathVariable int idUser) {
        List<InventoryCard> inventoryCardsList = inventoryCardService.getAllInventoryCardByUser(idUser);
        if(inventoryCardsList.isEmpty()){return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        return new ResponseEntity<>(inventoryCardsList, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST,value="/inventory/add")
    public ResponseEntity<?> addCard(int idCard, int idUser){
        int id = -1;
        InventoryCard inventoryCard = new InventoryCard(id, idCard, idUser);
        InventoryCard createdCard = inventoryCardService.addInventoryCard(inventoryCard);

        return new ResponseEntity<>(createdCard, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/card/sell")
    public ResponseEntity<?> sellCard(@RequestBody InventoryCard inventoryCard) {
        if (!inventoryCardService.inventoryCardExists(inventoryCard)) {
            return new ResponseEntity<>("La carte n'existe pas", HttpStatus.CONFLICT);
        } else {
            inventoryCardService.removeInventoryCard(inventoryCard);
            return new ResponseEntity<>("Vente effectuée", HttpStatus.OK);
        }
    }
}
