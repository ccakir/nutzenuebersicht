package com.cakirilhan.kundeTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.repository.KontaktRepository;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
public class KontaktTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
		
	@Autowired
	private KontaktRepository kontaktRepository;
	
	@Test
	public void test_findByVorname() {
		
		Kontakt kontakt1 = new Kontakt("Herr", "MAA", "Bernhard", "EDER", "2323433534", "67667868", "01235455", "sss@gmail.com", null);
		testEntityManager.persist(kontakt1);
		testEntityManager.flush();
		
		Kontakt finden = kontaktRepository.findByVorname(kontakt1.getVorname());
		
		
		Assert.assertEquals(kontakt1.getVorname(), finden.getVorname());
	}
	
	@Test
	public void test_findByKunde() {
		Kunde kunde = new Kunde("Halberg", null, null, "Saarbrücken", "Deutschland", "0495673848", "www.halber.de", null, "office@halberg.de");
		testEntityManager.persist(kunde);
		testEntityManager.flush();
		
		Kontakt kontakt2 = new Kontakt("Herr", "MAA", "Bernhard", "EDER", "2323433534", "67667868", "01235455", "sss@gmail.com", kunde);
		testEntityManager.persist(kontakt2);
		testEntityManager.flush();
		
		Kontakt finde = kontaktRepository.findByKunde(kunde);
		
		Assert.assertEquals(finde.getEmail(), kontakt2.getEmail());
		
	}
	
	

}
