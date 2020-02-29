package com.cakirilhan.domain.kunde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.Kunde;

@Repository
public interface KontaktRepository extends JpaRepository<Kontakt, Long> {
	
	Kontakt findByKontaktId(long id);
	
	Kontakt findByVorname(String vorname);
	
	Kontakt findByKunde(Kunde kunde);
	
	
}
