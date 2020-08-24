package com.sp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepository;

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

	public Card getCardById(int id) {
		return cardRepository.findById(id);
	}
	
}
