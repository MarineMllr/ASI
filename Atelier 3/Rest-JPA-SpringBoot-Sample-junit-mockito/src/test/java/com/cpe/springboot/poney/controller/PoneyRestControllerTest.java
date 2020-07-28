package com.cpe.springboot.poney.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.cpe.springboot.poney.model.Poney;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PoneyRestController.class, secure = false)
public class PoneyRestControllerTest {
	
	@Autowired
	private PoneyRestController rest;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PoneyService poneyService;

	Poney mockPoney=new Poney("jo", "blue", "super blue", "https:\\\\fakeSite\\data.jpg");
	
	
	@Test
	public void retrievePoney() throws Exception {
		Mockito.when(
				poneyService.getPoneyByColor(Mockito.anyString())).thenReturn(Arrays.asList(mockPoney));
				

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poneys/color/blue").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"id\":null,\"color\":\"blue\",\"superPower\":\"super blue\",\"name\":\"jo\",\"imgUrl\":\"https:\\\\\\\\fakeSite\\\\data.jpg\"}]";


		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	
	//TO BE COMPLETED
	
}
