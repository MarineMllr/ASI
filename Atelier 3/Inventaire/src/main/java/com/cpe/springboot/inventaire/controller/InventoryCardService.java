package com.cpe.springboot.inventaire.controller;

import com.cpe.springboot.inventaire.model.InventoryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryCardService {
    private final InventoryCardRepository inventoryCardRepository;

    @Autowired
    public InventoryCardService(InventoryCardRepository inventoryCardRepository) {
        this.inventoryCardRepository = inventoryCardRepository;
    }

    public InventoryCard addInventoryCard(InventoryCard inventoryCard){
        InventoryCard createdCard = inventoryCardRepository.save(inventoryCard);
        return createdCard;
    }

    public void removeInventoryCard(InventoryCard inventoryCard) {
        inventoryCardRepository.delete(inventoryCard);
    }

    public List<InventoryCard> getAllInventoryCardByUser(int idUser){ return inventoryCardRepository.findAllByIdUser(idUser);}

    public boolean inventoryCardExists(InventoryCard inventoryCard) {
        return inventoryCardRepository.findById(inventoryCard.getId()).isPresent();
    }
}
