package com.cpe.springboot.poney.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cpe.springboot.poney.model.Poney;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PoneyServiceTest {
	
	@Autowired
	private PoneyRepository poneyRepository;
	
	
	@Test
    public void savePoney() {
		poneyRepository.save(new Poney("jo", "blue", "super blue", "https:\\\\fakeSite\\data.jpg"));
		List<Poney> poneys = new ArrayList<>();
		poneyRepository.findAll().forEach(poneys::add); 
		 assertTrue(poneys.size() ==1);
		 assertTrue(poneys.get(0).getColor().equals("blue"));
		 assertTrue(poneys.get(0).getSuperPower().equals("super blue"));
		 assertTrue(poneys.get(0).getImgUrl().equals("https:\\\\fakeSite\\data.jpg"));
	}

	 @Test
	    public void findColorPoney() {
		 poneyRepository.save(new Poney("jo", "blue", "super blue", "https:\\\\fakeSite\\data.jpg"));
		 List<Poney> poneyList= poneyRepository.findByColor("blue");
		 assertTrue(poneyList.size() ==1);
		 assertTrue(poneyList.get(0).getName().equals("jo"));
	    }
	 
		@Test
	    public void findAllPoney() {
			for(int i=0;i<100;i++) {
				poneyRepository.save(new Poney("jo"+i, "blue"+i, "super blue"+i, "https:\\\\fakeSite\\data"+i+".jpg"));
			}
			
			List<Poney> poneys = new ArrayList<>();
			poneyRepository.findAll().forEach(poneys::add); 
			
			assertTrue(poneys.size() ==100);
			
			for(int i=0;i<100;i++) {
				String name=poneys.get(i).getName();
				name.split("joe");
				int index=Integer.valueOf(name.split("jo")[1]);
				 assertTrue(poneys.get(i).getName().equals("jo"+index));
				 assertTrue(poneys.get(i).getColor().equals("blue"+index));
				 assertTrue(poneys.get(i).getSuperPower().equals("super blue"+index));
				 assertTrue(poneys.get(i).getImgUrl().equals("https:\\\\fakeSite\\data"+index+".jpg"));
			}
			
			//TO COMPLETE
			
		}
}
