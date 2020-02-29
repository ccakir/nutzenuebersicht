package com.cakirilhan.VorOrtBetreuungTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.user.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VorOBRepositoryTest {
	
	@Autowired
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;
	
	@Test
	public void saveVorOB() {
		
		Kunde kunde= new Kunde();
		kunde.setKundeId(25L);
		
		Ort ort = new Ort();
		ort.setOrtId(4L);
		
		User user =new User();
		user.setId(8L);
		user.setUsername("admin");
		
		VorOrtBetreuung vob = new VorOrtBetreuung();
		//vob.setId(1L);
		vob.setKunde(kunde);
		vob.setOrt(ort);
		vob.setUser(user);
		
		vorOrtBetreuungRepository.save(vob);
		System.out.println(vob);
		VorOrtBetreuung v = vorOrtBetreuungRepository.findByUserId(user.getId());
		System.out.println(v);
		
		assertNotNull(vob);
		//assertEquals(v.getKunde(), vob.getKunde());
		
	}

}
