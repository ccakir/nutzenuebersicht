package com.cakirilhan.domain.kunde.repository.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.web.dto.OrtDto;



public interface OrtService {
	
	
	Ort save(Ort ort);
	
	boolean deleteOrt(long id);
	
	Ort findByOrtname(String ortname);
	
	void deleteOrts(long[] ids);
	
	@Transactional
	void updateOrt(Ort ort);
	
	
	
	

}
