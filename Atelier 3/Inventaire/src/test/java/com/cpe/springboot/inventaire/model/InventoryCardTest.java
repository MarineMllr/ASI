package com.cpe.springboot.inventaire.model;

import com.cpe.springboot.inventaire.controller.InventoryCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventoryCardTest {

    @Autowired
    private InventoryCardRepository inventoryCardRepository;

    @Test
    public void createInventoryCard(){
        inventoryCardRepository.save(new InventoryCard("name", "Description", "family", 200,
                100, 20, 20, "imgUrl", 30, 1, "jdoe", "jdoe"));
        List<InventoryCard> inventoryCardList = inventoryCardRepository.findAllByIdUser(1);
        assertEquals(1, inventoryCardList.size());
        assertEquals("name", inventoryCardList.get(0).getName());
    }
}
