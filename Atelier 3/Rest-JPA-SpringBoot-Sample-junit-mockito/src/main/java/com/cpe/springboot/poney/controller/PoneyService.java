package com.cpe.springboot.poney.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpe.springboot.poney.model.Poney;

@Service
public class PoneyService {

	@Autowired
	private PoneyRepository poneyRepository;

	public List<Poney> getAllPoneys() {
		List<Poney> poneys = new ArrayList<>();
		poneyRepository.findAll().forEach(poneys::add);
		return poneys;
	}

	public Poney getPoney(String id) {
		return poneyRepository.findOne(Integer.valueOf(id));
	}

	public void addPoney(Poney poney) {
		poneyRepository.save(poney);
	}

	public void updatePoney(Poney poney) {
		poneyRepository.save(poney);

	}

	public void deletePoney(String id) {
		poneyRepository.delete(Integer.valueOf(id));
	}

	public List<Poney> getPoneyByColor(String color) {
		return poneyRepository.findByColor(color);
	}

}
