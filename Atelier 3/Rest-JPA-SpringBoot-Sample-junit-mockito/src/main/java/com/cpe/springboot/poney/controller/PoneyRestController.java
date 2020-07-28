package com.cpe.springboot.poney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpe.springboot.poney.model.Poney;

@RestController
public class PoneyRestController {
	
	@Autowired
	private PoneyService poneyService;
	
	@RequestMapping("/poneys")
	private List<Poney> getAllCourses() {
		return poneyService.getAllPoneys();

	}
	
	@RequestMapping("/poneys/{id}")
	private Poney getPoney(@PathVariable String id) {
		return poneyService.getPoney(id);

	}
	
	@RequestMapping(method=RequestMethod.POST,value="/poneys")
	public void addPoney(@RequestBody Poney poney) {
		poneyService.addPoney(poney);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/poneys/{id}")
	public void updatePoney(@RequestBody Poney poney,@PathVariable String id) {
		poney.setId(Integer.valueOf(id));
		poneyService.updatePoney(poney);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/poneys/{id}")
	public void deletePoney(@PathVariable String id) {
		poneyService.deletePoney(id);
	}
	
	@RequestMapping("/poneys/color/{color}")
	private List<Poney> getAllCourses(@PathVariable String color) {
		return poneyService.getPoneyByColor(color);

	}
	

}
