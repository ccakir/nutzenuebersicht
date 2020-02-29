package com.cakirilhan.domain.kunde.repository.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Kontakt;

public interface KontaktService {
	
	Kontakt saveKontakt(Kontakt kontakt);
	
	@Transactional
	void updateKontakt(Kontakt kontakt);
	
	boolean deleteKontakt(long id);
	
	boolean deleteKontakte(long [] ids);
	
	List<Kontakt> findContactsByLastname(String letter);
	

}
