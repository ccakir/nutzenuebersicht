package com.cakirilhan.domain.kunde.repository.service;

import java.util.List;



public interface DatabaseDateiService {
	
	List<String> getAllFileName();
	
	boolean findByFileName(String fileName);

}
