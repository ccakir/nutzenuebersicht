package com.cakirilhan.domain.kunde.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.repository.service.DatabaseDateiService;


@Service
public class DatabaseDateiImpl implements DatabaseDateiService {
	
	@Autowired
	EntityManager entityManager;

	@Override
	public List<String> getAllFileName() {

		@SuppressWarnings("unchecked")
		TypedQuery<String> query = (TypedQuery<String>) entityManager.createQuery("SELECT d.fileName FROM DatabaseDatei d");
		
		List<String> listFileNames = query.getResultList();
		
		return listFileNames;
	}

	@Override
	public boolean findByFileName(String fileName) {
		
		Query query = entityManager.createQuery("SELECT COUNT(d.id) FROM DatabaseDatei d WHERE d.fileName=:fileName")
				.setParameter("fileName", fileName);
		
		long count = (long) query.getSingleResult();
		
		if(count > 0 ) return true;
		else return false;
	}

}
