package com.cakirilhan.domain.kunde.repository.service;

import java.util.List;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.VorOrtBetreuung;



public interface VortOrtBetreuungService {
	
	//vorOB ----> VorOrtBetreuung
	VorOrtBetreuung saveVorOrtBetreuung(VorOrtBetreuung vorOB);
	
	boolean deleteVorOB(long id);
	
	boolean deleteVorOBs(long[] ids);
	
	void updateVorOB(VorOrtBetreuung vorOB);
	
	List<VorOrtBetreuung> findAllOrderById();
	
	boolean doppelVorOB(long userId, long kundeId, long ortId);
	
	List<VorOrtBetreuung> findOrtWithLocation(long id);
	
	List<VorOrtBetreuung> findAllDistinctLocationWhereCustomer();
	

}
