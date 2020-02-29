package com.cakirilhan.domain.kunde.service.implTest;

import lombok.AllArgsConstructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.KundeManagementApplication;
import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.repository.KontaktRepository;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.service.KontaktService;


@RunWith(SpringRunner.class)
@DataJpaTest
public class KontaktServiceImplTest {
	
	@Autowired
	private KontaktService kontaktService;
	
	@Autowired
	private KundeRepository KundeRepository;
	
	@Autowired
	private KontaktRepository kontaktRepository;
	
	
	@Test
	public void testSaveKontakt() {
		
		
		
		
		Kunde kunde = new Kunde();
		kunde.setName("Test-New Customer");
		kunde.setKundeId(5L);
		
		
		Kontakt kontakt = new Kontakt();
		//kontakt.setKontaktId(1L);
		kontakt.setAnrede("Test-Herr");
		kontakt.setEmail("Test-ilhan@gmx.at");
		kontakt.setFax("Test-0156587458");
		kontakt.setMobil("Test-05458565895");
		kontakt.setNachname("Test-cakir");
		kontakt.setTelefon("Test-05468523658");
		kontakt.setTitel("Test-Mag");
		kontakt.setVorname("Test-ilhan");
		kontakt.setKunde(kunde);
		
		//Kontakt kontaktMock = Mockito.mock(Kontakt.class);
		
		System.out.println(kontakt);
		
		//Mockito.when(kontaktRepository.save(ArgumentMatchers.any(Kontakt.class))).thenReturn(kontakt);
		
		kontaktService.saveKontakt(kontakt);
		
		//System.out.println("Saved :" +savedKontakt);
		
		
		
		
		
		
	}

}
