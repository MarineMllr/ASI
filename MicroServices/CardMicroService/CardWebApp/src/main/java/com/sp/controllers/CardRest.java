package com.sp.controllers;

import com.sp.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sp.services.CardService;

import java.util.List;

@RestController
public class CardRest {
	@Autowired
	CardService cardService;

	//Ceci est un test
	@RequestMapping(value = "/echoStudentName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name)
	{
		return "hello  <strong style=\"color: red;\">" + name + " </strong>";
	}

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

	@RequestMapping(method= RequestMethod.GET, value="/card/{id}")
	public ResponseEntity<Object> getCard(@PathVariable int id) {
		Card card = cardService.getCardById(id);
		return new ResponseEntity<>(card, HttpStatus.OK);
	}

	
}
