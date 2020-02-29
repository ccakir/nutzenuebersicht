package com.cakirilhan.VorOrtBetreuungTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;







import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.kunde.service.impl.VortOrtBetreuungImpl;
import com.cakirilhan.domain.user.User;




@RunWith(SpringRunner.class)

public class VorOrtBetreuungTest {

	
	
	@Mock
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;
	
	@InjectMocks
	VortOrtBetreuungImpl vortOrtBetreuungService;
	
	@Test
	public void wenn_speichern_vorOB_return_vorOB() {
		
		Kunde kunde= new Kunde();
		kunde.setKundeId(1L);
		
		Ort ort = new Ort();
		ort.setOrtId(2L);
		
		User user =new User();
		user.setId(4L);
		
		VorOrtBetreuung vob = new VorOrtBetreuung();
		//vob.setId(1L);
		vob.setKunde(kunde);
		vob.setOrt(ort);
		vob.setUser(user);
		
		System.out.println(vob);
		
		VorOrtBetreuung savedVOB=vorOrtBetreuungRepository.save(vob);
		
		System.out.println(savedVOB);
		assertThat(savedVOB.getKunde().getKundeId()).isSameAs(kunde.getKundeId());
		
		
	
		
	}
	
	
	
}
