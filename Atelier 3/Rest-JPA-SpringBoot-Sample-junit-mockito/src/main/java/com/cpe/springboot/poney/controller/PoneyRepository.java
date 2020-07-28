package com.cpe.springboot.poney.controller;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.poney.model.Poney;

public interface PoneyRepository extends CrudRepository<Poney, Integer> {
	
	public List<Poney> findByColor(String color);

}
