package com.cakirilhan.Ort;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cakirilhan.KundeManagementApplication;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.service.impl.OrtServiceImpl;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KundeManagementApplication.class)
@AutoConfigureTestDatabase
@ExtendWith(MockitoExtension.class)
public class OrtTest {

	@Autowired
	private OrtRepository ortRepository;
	
	@Autowired
	private OrtServiceImpl ortService;
	
	
	
	@Test
	public void test() {


		Ort ort = new Ort();
		ort.setOrtname("Wien");
		ort.setLand("Österreich");
		ort.setPlz("1220");
		
		ortRepository.save(ort);
		
		Ort ort1 = new Ort();
		ort1.setOrtname("Berlin");
		ort1.setLand("Deutschland");
		
		
		
		
		Ort savedOrt = ortRepository.save(ort1);
		
		System.out.println("Ort1 : " + savedOrt);
		Ort newOrt = ortRepository.findByOrtname(ort.getOrtname());
		
		Ort newOrt2 = ortRepository.findByOrtname(ort1.getOrtname());
		
		System.out.println("newOrt : " + newOrt);
		System.out.println("newOrt2: " + newOrt2);
		
		Assertions.assertEquals(newOrt.getOrtname(), ort.getOrtname());
		
		//Assertions.assertEquals(newOrt2.getOrtname(), ort1.getOrtname());
		
		
	}

}
