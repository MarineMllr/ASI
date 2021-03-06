package com.sp.services;

import com.sp.model.InventoryCard;
import com.sp.repository.InventoryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void removeInventoryCard(int id) {
        inventoryCardRepository.delete(inventoryCardRepository.findById(id));
    }

    public List<InventoryCard> getAllInventoryCardByUser(int idUser){ return inventoryCardRepository.findAllByIdUser(idUser);}

    public boolean inventoryCardExists(InventoryCard inventoryCard) {
        return inventoryCardRepository.findById(inventoryCard.getId())!= null;
    }

    public boolean inventoryCardExistsById(int id) {
        return inventoryCardRepository.findById(id) != null;
    }

    public InventoryCard findById(int id){
        return inventoryCardRepository.findById(id);
    }


}
