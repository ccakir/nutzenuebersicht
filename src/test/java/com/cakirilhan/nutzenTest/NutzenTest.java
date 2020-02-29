package com.cakirilhan.nutzenTest;

import java.time.LocalDate;

import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.user.Nutzen;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.user.repository.NutzenRepository;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
public class NutzenTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private NutzenRepository nutzenRepository;
	
	
	
	@Test
	public void testSpeichern() {
		
		LocalDate datum = null;
		User user = new User();
		Kunde kunde = new Kunde();
		Ort ort = new Ort();
		Taetigkeit taetigkeit = new Taetigkeit();
		
		Nutzen nutzen = new Nutzen(datum, user , kunde, ort, taetigkeit, "ffffffffffff", "hhhhhhhhhhhh", 20.5 , true);
		
		testEntityManager.persist(nutzen);
		testEntityManager.flush();
		Query query = testEntityManager.getEntityManager().createQuery("SELECT count(*) FROM Nutzen");
		long count = (long) query.getSingleResult();
		
		Assert.assertEquals(count, 0);
		
		
	}

}
