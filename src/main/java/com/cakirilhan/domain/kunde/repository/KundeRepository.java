package com.cakirilhan.domain.kunde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.Kunde;


@Repository
public interface KundeRepository extends JpaRepository<Kunde, Long>{
	
	Kunde findByName(String name);
	Optional<Kunde> findByKundeId(long kundeId);
	
	void deleteByKundeId(long id);
	
	
}
