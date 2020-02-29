package com.cakirilhan.domain.user.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.user.Nutzen;
import com.cakirilhan.domain.user.User;

public interface NutzenService {
	
	void save(Nutzen nutzen);
	
	boolean deleteNutzen(long id);
	
	boolean deleteMehrereNutzen(long[] ids);
	
	@Transactional
	void updateNutzen(Nutzen nutzen);
	
	void listById(long id);
	
	@Transactional
	List<Nutzen> findByUser(long id);
	
	List<Nutzen> findAllOrderById();
	
	Page<Nutzen> findPaginated(Pageable pageable, boolean authorized, User user);
	
	Page<Nutzen> sucheMitKunde(Pageable pageable, boolean authorized, User user, Optional<Kunde> kunde);

	Page<Nutzen> sucheMitMitarbeiter(Pageable pageable, long mitarbeiterId);
	
	Page<Nutzen> sucheMitDatum(Pageable pageable, LocalDate vonDatum, LocalDate bisDatum);
}
