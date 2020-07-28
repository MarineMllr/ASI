package com.cpe.springboot.poney.model;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cpe.springboot.poney.controller.PoneyRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PoneyTest {
	
	@Autowired
	private PoneyRepository poneyRepository;

	 @Test
	    public void createPoney() {
		 poneyRepository.save(new Poney("jo", "blue", "super blue", "https:\\\\fakeSite\\data.jpg"));
		 List<Poney> poneyList= poneyRepository.findByColor("blue");
		 assertTrue(poneyList.size() ==1);
		 assertTrue(poneyList.get(0).getName().equals("jo"));
	    }
}
