package com.cakirilhan.domain.kunde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.user.User;

@Repository
public interface VorOrtBetreuungRepository extends JpaRepository<VorOrtBetreuung, Long> {
	
	VorOrtBetreuung findByUserId(long userId);
	
	

}
