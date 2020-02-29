package com.cakirilhan.domain.kunde.repository.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Vertrag;

public interface VertragService {
	
	List<Vertrag> findAllOrderById();
	
	boolean findVertragByKundeAndOrt(long kundeId, long ortId);
	
	

}
