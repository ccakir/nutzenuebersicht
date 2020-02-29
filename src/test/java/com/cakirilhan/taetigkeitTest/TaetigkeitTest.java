package com.cakirilhan.taetigkeitTest;

import java.util.Optional;

import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.kunde.repository.TaetigkeitRepository;
import com.cakirilhan.domain.kunde.service.impl.TaetigkeitServiceImpl;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
public class TaetigkeitTest {

	@Autowired
	private TaetigkeitRepository taetigkeitRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private TaetigkeitServiceImpl taetigkeitService;
	
	@Test
	public void test_addTaetigkeit() {
		
		Taetigkeit taetigkeit1 = new Taetigkeit("Ausschuss/Nacharbeit", "Materialausschuss aufnehmen/Teile Nacharbeiten");
		testEntityManager.persist(taetigkeit1);
		testEntityManager.flush();
		System.out.println(taetigkeit1.getId());
		
		Taetigkeit taetigkeit2 = new Taetigkeit("Statistik", "Wöchentliche Statistik");
		testEntityManager.persist(taetigkeit2);
		testEntityManager.flush();
		System.out.println(taetigkeit2.getId());
		
		Query query = testEntityManager.getEntityManager().createQuery("SELECT count(*) FROM Taetigkeit");
		long count = (long) query.getSingleResult();
		
		Assert.assertEquals(count, 2);
	}
	
	
	
}
