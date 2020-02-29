package com.cakirilhan.domain.kunde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakirilhan.domain.kunde.Ort;

public interface OrtRepository extends JpaRepository<Ort, Long>{
	
	Ort findByOrtname(String ortname);
	Optional<Ort> findByOrtId(long id);
	
	

}
