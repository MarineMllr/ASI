package com.cpe.springboot.inventaire.controller;

import com.cpe.springboot.inventaire.model.InventoryCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventoryCardServiceTest {

    @Autowired
    private InventoryCardRepository inventoryCardRepository;

    InventoryCard mockInventoryCard = new InventoryCard("name", "Description", "family", 200,
            100, 20, 20, "imgUrl", 30, 1, "jdoe", "jdoe");

    @Test
    public void addInventoryCard() {
        inventoryCardRepository.save(mockInventoryCard);
        List<InventoryCard> inventoryCards = new ArrayList<>();
        inventoryCardRepository.findAll().forEach(inventoryCards::add);
        assertTrue(inventoryCards.size()==1);
        assertTrue(inventoryCards.get(0).getName().equals("name"));
        assertTrue(inventoryCards.get(0).getFamily().equals("family"));
        assertTrue(inventoryCards.get(0).getDescription().equals("Description"));
    }
}
