package com.cakirilhan.domain.kunde.service.impl;

import java.util.List;

import javax.persistence.EntityManager;


import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Vertrag;
import com.cakirilhan.domain.kunde.repository.VertragRepository;
import com.cakirilhan.domain.kunde.repository.service.VertragService;


@Service
public class VertragServiceImpl implements VertragService {
	
	@Autowired
	private VertragRepository vertragRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<Vertrag> findAllOrderById() {
		
		TypedQuery<Vertrag> query = entityManager.createNamedQuery("Vertrag.findAllOrderById", Vertrag.class);
		
		return query.getResultList();
	}


	@Override
	public boolean findVertragByKundeAndOrt(long kundeId, long ortId) {
		
		Query query = entityManager.createQuery("SELECT COUNT(v.id) FROM Vertrag v WHERE v.kunde.kundeId=:kundeId AND v.ort.ortId=:ortId")
				.setParameter("kundeId", kundeId)
				.setParameter("ortId", ortId);
		
		long count = (long) query.getSingleResult();
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}


	

}
