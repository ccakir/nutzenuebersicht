package com.cakirilhan.domain.kunde.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.DatabaseDatei;
import com.cakirilhan.domain.kunde.Vertrag;


@Repository
public interface DatabaseDateiRepository extends JpaRepository<DatabaseDatei, Long> {
	
	Optional<DatabaseDatei> findByFileName(String fileName);
	
	List<DatabaseDatei> findByVertrag(Vertrag vertrag);
	
	
}
