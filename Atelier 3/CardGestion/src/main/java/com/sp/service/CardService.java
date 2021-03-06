package com.sp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
	private final CardRepository cardRepository;
	

	@Autowired
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
		
	}


	public void addCard(Card c) {
		Card createdCard = cardRepository.save(c);
		System.out.println(createdCard);
	}
	
	public void DeleteCard(Card c) {
		
		cardRepository.delete(c);
		
	}
	


	public List<Card> allCards() {
		Iterable<Card> cards = cardRepository.findAll();
		List<Card> result = new ArrayList<>();
		cards.forEach(result::add);
		return result;
	}

	
}
