package com.cakirilhan.domain.kunde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.Vertrag;


@Repository
public interface VertragRepository extends JpaRepository<Vertrag, Long> {
	

}
