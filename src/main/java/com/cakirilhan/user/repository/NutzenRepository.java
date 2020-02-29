package com.cakirilhan.user.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.user.Nutzen;


@Repository
public interface NutzenRepository extends JpaRepository<Nutzen, Long> {
	
	
	
	Optional<Nutzen> findById(long id);
	
	Optional<Nutzen> findByKunde(Kunde kunde);

}
