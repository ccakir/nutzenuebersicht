package com.cakirilhan.domain.kunde.service.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.VorOrtBetreuung;
import com.cakirilhan.domain.kunde.repository.VorOrtBetreuungRepository;
import com.cakirilhan.domain.kunde.repository.service.VortOrtBetreuungService;


@Service
public class VortOrtBetreuungImpl implements VortOrtBetreuungService {
	
	@Autowired
	private VorOrtBetreuungRepository vorOrtBetreuungRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public VorOrtBetreuung saveVorOrtBetreuung(VorOrtBetreuung vorOB) {
		
		return vorOrtBetreuungRepository.save(vorOB);
		
	}

	@Override
	public boolean deleteVorOB(long id) {
		
		Optional<VorOrtBetreuung> vorOrtBetreuung = vorOrtBetreuungRepository.findById(id);
		
		if(vorOrtBetreuung.isPresent()) {
			
			try {
				vorOrtBetreuungRepository.delete(vorOrtBetreuung.get());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		if(vorOrtBetreuungRepository.findById(id) != null) return false;
		else return true;
		
	}

	@Override
	public boolean deleteVorOBs(long[] ids) {
		
		int i=0;
		
		while(i < ids.length) {
			
			Optional<VorOrtBetreuung> vorOB = vorOrtBetreuungRepository.findById(ids[i]);
			
			if(vorOB.isPresent()) {
				try {
					
					vorOrtBetreuungRepository.delete(vorOB.get());
					
					
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} i++;
				
			}
		}
		return true;
	}

	@Override
	public void updateVorOB(VorOrtBetreuung vorOB) {
		vorOrtBetreuungRepository.save(vorOB);
	}

	@Override
	public List<VorOrtBetreuung> findAllOrderById() {
		
		TypedQuery<VorOrtBetreuung> query = entityManager.createNamedQuery("VorOB.findAllOrderById", VorOrtBetreuung.class);
		List<VorOrtBetreuung > vorOBs = query.getResultList();
		return vorOBs;
	}

	@Override
	public boolean doppelVorOB(long userId, long kundeId, long ortId) {
		
		int count = ((Number)entityManager.createNamedQuery("VorOB.doppelKontrolle")
				.setParameter("user", userId)
				.setParameter("kunde", kundeId)
				.setParameter("ort", ortId).getSingleResult()).intValue();
		
		
		if(count==0) return false;
		else return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VorOrtBetreuung> findOrtWithLocation(long id) {

		try {
			TypedQuery<VorOrtBetreuung> query = (TypedQuery<VorOrtBetreuung>) entityManager.createQuery("SELECT v FROM VorOrtBetreuung v INNER JOIN Ort o ON v.ort.ortId=o.ortId WHERE v.kunde.kundeId=:kundeId ")
					.setParameter("kundeId", id);
			
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	
	}

	@Override
	public List<VorOrtBetreuung> findAllDistinctLocationWhereCustomer() throws HibernateException {
		
		TypedQuery<VorOrtBetreuung> query = entityManager.createNamedQuery("VorOB.findAllGroupByLocationAndCustomer", VorOrtBetreuung.class);
		
		
		
		
		return query.getResultList();
		
	}

	
}
