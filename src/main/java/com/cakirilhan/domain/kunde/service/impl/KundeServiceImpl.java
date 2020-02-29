package com.cakirilhan.domain.kunde.service.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.repository.KundeRepository;
import com.cakirilhan.domain.kunde.repository.service.KundeService;

@Service
public class KundeServiceImpl implements KundeService{
	
	@Autowired
	KundeRepository kundeRepository;
	
	@Autowired
	EntityManager entityManager;
	

	
	@Override
	public Kunde saveKunde(Kunde kunde) {
		return kundeRepository.save(kunde);
	}

	@Override
	public Kunde findByName(String name) {
		return kundeRepository.findByName(name);
		
	}

	@Override
	public Optional<Kunde> findByKundeId(long kundeId) {
		
		return kundeRepository.findByKundeId(kundeId);
	}

	@Override
	public Kunde updateKunde(Kunde kunde) {



		return kundeRepository.save(kunde);

	}

	@Override
	public boolean deleteKunde(long id) {
		
		Optional<Kunde> kunde = kundeRepository.findByKundeId(id);
		
		if(kunde.isPresent()) {
			
			try {
				kundeRepository.deleteByKundeId(kunde.get().getKundeId());
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			
		} 
		else {
			
			return false;
		}
		
		
	}

	@Override
	public boolean deleteKunde(long[] ids) {
		
		int i=0;
		while(i<ids.length) {
			
			Optional<Kunde> kunde = kundeRepository.findByKundeId(ids[i]);
			if(kunde.isPresent()) {
				kundeRepository.delete(kunde.get());
			}
			i++;
			
		} 
		
		int k=0;
		while(k<ids.length) {
			
			Optional<Kunde> kunde = kundeRepository.findByKundeId(ids[k]);
			if(kunde.isPresent()) {
				return false;
			}
			k++;
		}
		
		return true;
	}

	

	

}
