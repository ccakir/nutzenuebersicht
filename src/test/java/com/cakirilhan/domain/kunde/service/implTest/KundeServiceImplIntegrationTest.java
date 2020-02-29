package com.cakirilhan.domain.kunde.service.implTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.App;
import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.service.KundeService;
import com.cakirilhan.domain.kunde.service.impl.KundeServiceImpl;


@SpringBootTest(classes=App.class)
public class KundeServiceImplIntegrationTest {
	
	@Autowired
	private KundeService kundeservice;
	
	@Autowired
	KundeRepository kundeRepository;
	
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		Kunde kunde1 = new Kunde();
		kunde1.setKundeId(1L);
		kunde1.setName("Test-HalberGuss");
		kunde1.setLand("Test-Deutschland");
		System.out.println(kunde1);
		Kunde savedKunde = kundeRepository.save(kunde1);
		System.out.println("Saved kunde: "  +savedKunde);
		Assertions.assertTrue(savedKunde.getKundeId() > 0L);
		
		
		
	}

}
