package com.cakirilhan.domain.kunde.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.repository.OrtRepository;
import com.cakirilhan.domain.kunde.repository.service.OrtService;
import com.cakirilhan.web.dto.OrtDto;


@Service
public class OrtServiceImpl implements OrtService {
	
	@Autowired
	OrtRepository ortRepository;
	
	@Autowired
	OrtService ortService;
	
	@Autowired
	EntityManager entityManager;

	
	@Override
	public boolean deleteOrt(long id) {
		
		Optional<Ort> ort = ortRepository.findById(id);
		if(ort.isPresent()) {
			try {
				ortRepository.delete(ort.get());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			
		} else {
		return false;
		}
	}

	@Override
	public Ort findByOrtname(String ortname) {
		return ortRepository.findByOrtname(ortname);
	}

	@Override
	public Ort save(Ort ort) {
		return ortRepository.save(ort);
	}

	@Override
	public void deleteOrts(long[] ids) {
		int i=0;
		while(i<ids.length) {
			Optional<Ort> ort = ortRepository.findById(ids[i]);
			if(ort.isPresent()) {
				ortRepository.delete(ort.get());
			}
			i++;
		}
	}

	

	@Override
	public void updateOrt(Ort ort) {
		try {
			entityManager.createQuery("UPDATE Ort o SET o.ortname=?1, o.adresse=?2,"
					+ "o.plz=?3, o.land=?4 WHERE o.ortId=?5")
					.setParameter(1, ort.getOrtname())
					.setParameter(2, ort.getAdresse())
					.setParameter(3, ort.getPlz())
					.setParameter(4, ort.getLand())
					.setParameter(5, ort.getOrtId())
					.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
