package com.cakirilhan.domain.kunde.repository.service;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Taetigkeit;

public interface TaetigkeitService {
	
	void saveTaetigkeit(Taetigkeit taetigkeit);
	
	boolean deleteTaetigkeit(long id);
	
	boolean deleteTaetigkeiten(long[] ids);
	
	@Transactional
	void updateTaetigkeit(Taetigkeit taetigkeit);

}
