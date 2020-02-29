package com.cakirilhan.domain.kunde.repository.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Kunde;



public interface KundeService {
	
	Kunde saveKunde(Kunde kunde);
	
	Kunde findByName(String name);
	
	Optional<Kunde> findByKundeId(long kundeId);
	
	boolean deleteKunde(long id);
	
	boolean deleteKunde(long[] ids);
	
	@Transactional
	Kunde updateKunde(Kunde kunde);
	
	

}
