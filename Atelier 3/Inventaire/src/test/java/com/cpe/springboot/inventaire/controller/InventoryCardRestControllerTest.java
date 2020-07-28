package com.cpe.springboot.inventaire.controller;

import com.cpe.springboot.inventaire.model.InventoryCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InventoryCardRestController.class, secure = false)
public class InventoryCardRestControllerTest {

    @Autowired
    private InventoryCardRestController inventoryCardRestController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryCardService inventoryCardService;

    InventoryCard mockInventoryCard = new InventoryCard("name", "Description", "family", 200,
            100, 20, 20, "imgUrl", 30, 1, "jdoe", "jdoe");

    @Test
    public void retrieveInventoryCard() throws Exception {
        Mockito.when(inventoryCardService.getAllInventoryCardByUser(Mockito.anyInt())).thenReturn(Arrays.asList(mockInventoryCard));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/inventory/cards/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"id\":0,\"name\":\"name\",\"description\":\"Description\",\"family\":\"family\"," +
                "\"hp\":200,\"energy\":100,\"defence\":20,\"attack\":20,\"imgUrl\":\"imgUrl\"," +
                "\"price\":30,\"idUser\":1,\"userName\":\"jdoe\",\"userSurname\":\"jdoe\"}]";


        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
