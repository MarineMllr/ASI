package com.sp.rest;


import com.sp.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sp.service.CardService;

import java.util.List;

@RestController
public class CardRest {
	@Autowired
	CardService cardService;



	@RequestMapping(method = RequestMethod.GET, value = "/cards")
	public ResponseEntity<List<Card>> getAllCards() {
		List<Card> cards = cardService.allCards();
		if (cards.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	

	@RequestMapping(method= RequestMethod.POST,value="/card/add")
	public void addCard(@RequestBody Card card) {
		cardService.addCard(card);
	}
	
	@RequestMapping(method= RequestMethod.POST,value="/card/delete")
	public void DeleteCard(@RequestBody Card card) {
		cardService.DeleteCard(card);
	}

	
}
