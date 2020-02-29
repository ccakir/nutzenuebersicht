package com.cakirilhan.domain.kunde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.Taetigkeit;


@Repository
public interface TaetigkeitRepository extends JpaRepository<Taetigkeit, Long> {
	
	Optional<Taetigkeit> findByName(String name);
	
	Optional<Taetigkeit> findById(long id);
	
	
}
