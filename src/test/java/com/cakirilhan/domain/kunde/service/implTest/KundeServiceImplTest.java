package com.cakirilhan.domain.kunde.service.implTest;

import java.util.Optional;

import org.apache.jasper.tagplugins.jstl.core.When;
import org.junit.Before;
import org.junit.Test;

import static  org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.service.KundeService;
import com.cakirilhan.domain.kunde.service.impl.KundeServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class KundeServiceImplTest {

	@TestConfiguration
	static class KundeServiceImplTestContextConfiguration {

		@Bean
		public KundeService kundeService() {
			return new KundeServiceImpl();
		}
	}

	@Autowired
	private KundeService kundeService;

	@MockBean
	private KundeRepository kundeRepository;

	

	@Before
	public void setUp() throws Exception {

		Kunde kunde = new Kunde();
		kunde.setKundeId(1L);
		kunde.setName("Test-HalberGuss");
		kunde.setLand("Test-Deutschland");

		Mockito.when(kundeRepository.findByName(kunde.getName())).thenReturn(
				kunde);

		Mockito.when(kundeRepository.findByKundeId(kunde.getKundeId()))
				.thenReturn(Optional.of(kunde));
		
		
		

	}

	@Test
	public void deleteKunde_Test() {
		Kunde kunde1 = new Kunde();
		kunde1.setKundeId(1L);
		kunde1.setName("Test-HalberGuss");
		kunde1.setLand("Test-Deutschland");
		
		Mockito.when(kundeRepository.save(any(Kunde.class))).thenReturn(kunde1);
		
		Kunde savedKunde = kundeRepository.save(kunde1);
		
		System.out.println("Saved Kunde : "+savedKunde);
		
		Mockito.when(kundeRepository.findByKundeId(kunde1.getKundeId())).thenReturn(Optional.of(kunde1));
		Optional<Kunde> findKunde = kundeRepository.findByKundeId(savedKunde.getKundeId());
		
		System.out.println("Finde Kunde : "+findKunde.get());
		
		Assertions.assertNotNull(findKunde);
		Mockito.when(kundeRepository.findByKundeId(kunde1.getKundeId())).thenReturn(Optional.of(kunde1));
		
		
		
		Boolean result = kundeService.deleteKunde(findKunde.get().getKundeId());
		
		
		Assertions.assertTrue(result);
		
		Optional<Kunde> findAfterDelete = kundeRepository.findByKundeId(1L);
		
		Assertions.assertNull(findAfterDelete);
		
	}
	
	@Test
	public void findByName_Test() {

		Kunde kunde1 = new Kunde();
		kunde1.setKundeId(1L);
		kunde1.setName("Test-HalberGuss");
		kunde1.setLand("Test-Deutschland");

		Kunde savedKunde = kundeService.findByName(kunde1.getName());

		assertEquals(savedKunde.getName(), kunde1.getName());

	}

	@Test
	public void findByKundeId_Test() {

		Kunde kunde1 = new Kunde();
		kunde1.setKundeId(1L);
		kunde1.setName("Test-HalberGuss");
		kunde1.setLand("Test-Deutschland");

		
		Mockito.when(kundeRepository.save(any(Kunde.class))).thenReturn(kunde1);
		
		Kunde savedKunde = kundeRepository.save(kunde1);

		Optional<Kunde> findKunde = kundeService.findByKundeId(savedKunde.getKundeId());

		assertEquals(1L, findKunde.get().getKundeId());

	}
	
	@Test
	public void update_Test() {
		Kunde kunde1 = new Kunde();
		kunde1.setKundeId(1L);
		kunde1.setName("Test-HalberGuss");
		kunde1.setLand("Test-Deutschland");

		
		Mockito.when(kundeRepository.save(any(Kunde.class))).thenReturn(kunde1);
		
		Kunde savedKunde = kundeRepository.save(kunde1);

		
		
		savedKunde.setEmail("ilhan_cakir@gmx.at");
		savedKunde.setName("Test-YeongWa");
		savedKunde.setLand("Test-Korea");
		
		Mockito.when(kundeRepository.save(any(Kunde.class))).thenReturn(savedKunde);
		
		Kunde updatedKunde = kundeService.updateKunde(savedKunde);
		
		assertEquals(updatedKunde.getKundeId(), 1L);
		Assertions.assertNotNull(updatedKunde.getEmail());;
		
		
	}

}
