package com.cakirilhan.domain.user.service.impl;


import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.user.Nutzen;
import com.cakirilhan.domain.user.User;
import com.cakirilhan.domain.user.service.NutzenService;
import com.cakirilhan.user.repository.NutzenRepository;

import org.springframework.data.domain.Pageable;

@Service
public class NutzenServiceImpl implements NutzenService {
	
	@Autowired
	private NutzenRepository nutzenRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private NutzenService nutzenService;
	
	
	
	

	@Override
	public void save(Nutzen nutzen) {
		
		
		nutzenRepository.save(nutzen);
	}

	@Override
	public boolean deleteNutzen(long id) {
		
		Optional<Nutzen> nutzen = nutzenRepository.findById(id);
		
		if(nutzen.isPresent()) {
			
			try {
				nutzenRepository.delete(nutzen.get());
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		if(nutzenRepository.findById(id).isPresent()) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public boolean deleteMehrereNutzen(long[] ids) {
		
		int row = ids.length;
		int i = 0;
		int k = 0;
		
		while( row > i ) {
			Optional<Nutzen> nutzen = nutzenRepository.findById(ids[i]);
			if(nutzen.isPresent()) {
				try {
					nutzenRepository.delete(nutzen.get());
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}
			
			i++;
		}
		
		
		while( row > k ) {
			Optional<Nutzen> nutzen = nutzenRepository.findById(ids[k]);
			if(nutzen.isPresent()) {
				return false;
			}
			k++;
		}
		
		return true;
		
	}

	@Override
	public void updateNutzen(Nutzen nutzen) {
		
		try {
			
			entityManager.createQuery("UPDATE Nutzen n SET n.datum=?1, n.details=?2, n.freigabe=?3,"
					+ "n.sondernleistung=?4, n.stunde=?5, n.kunde=?6, n.ort=?7, n.taetigkeit=?8,"
					+ "n.user=?9 WHERE n.id=?10")
					.setParameter(1, nutzen.getDatum())
					.setParameter(2, nutzen.getDetails())
					.setParameter(3, nutzen.isFreigabe())
					.setParameter(4, nutzen.getSondernleistung())
					.setParameter(5, nutzen.getStunde())
					.setParameter(6, nutzen.getKunde())
					.setParameter(7, nutzen.getOrt())
					.setParameter(8, nutzen.getTaetigkeit())
					.setParameter(9, nutzen.getUser())
					.setParameter(10, nutzen.getId())
					.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listById(long id) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Nutzen> findByUser(long id) {
		try {
			Query query = entityManager.createQuery("SELECT n FROM Nutzen n WHERE n.user.id=:userId ORDER BY n.id DESC");
			query.setParameter("userId", id);
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public Page<Nutzen> findPaginated(Pageable pageable, boolean authorized, User user) {
		
		List<Nutzen> nutzens;
		if(authorized) {
			
			nutzens = nutzenService.findAllOrderById();
			
		} else {
			
			 nutzens = nutzenService.findByUser(user.getId());
			
		}
		
		
		
		int pagesize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pagesize;
		
		List<Nutzen> list;
		
		if(nutzens.size() < startItem ) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem  + pagesize, nutzens.size());
			list = nutzens.subList(startItem, toIndex);
		}
		
		Page<Nutzen> nutzenPage = new PageImpl<Nutzen>(list, PageRequest.of(currentPage, pagesize), nutzens.size() );
		
		
		
		return nutzenPage;
	}

	@Override
	public Page<Nutzen> sucheMitKunde(Pageable pageable, boolean authorized,
			User user, Optional<Kunde> selectedKunde) {
		List<Nutzen> nutzens;
		
		if(authorized) {
			
			
			
			TypedQuery<Nutzen> query = entityManager.createNamedQuery("Nutzen.sucheMitKunde", Nutzen.class)
					.setParameter("kundeId", selectedKunde.get().getKundeId());
			nutzens = query.getResultList();
			
		} else {
			
			TypedQuery<Nutzen> query = entityManager.createNamedQuery("Nutzen.sucheMitKundeundUser", Nutzen.class)
					.setParameter("kundeId", selectedKunde.get().getKundeId())
					.setParameter("userId", user.getId());
			 nutzens = query.getResultList();
			
		}
		
		
		int pagesize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pagesize;
		
		List<Nutzen> list;
		
		if(nutzens.size() < startItem ) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem  + pagesize, nutzens.size());
			list = nutzens.subList(startItem, toIndex);
		}
		
		Page<Nutzen> nutzenPage = new PageImpl<Nutzen>(list, PageRequest.of(currentPage, pagesize), nutzens.size() );
		
		
		
		return nutzenPage;
	}

	@Override
	public Page<Nutzen> sucheMitMitarbeiter(Pageable pageable,
			long mitarbeiterId) {
		
		TypedQuery<Nutzen> query = entityManager.createNamedQuery("Nutzen.sucheMitUser", Nutzen.class)
				.setParameter("mitarbeiterId", mitarbeiterId);
		List<Nutzen> nutzens = query.getResultList();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		List<Nutzen> list;
		
		if(nutzens.size() < startItem ){
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem  + pageSize, nutzens.size());
			list = nutzens.subList(startItem, toIndex);
		}
		
		Page<Nutzen> nutzenPage = new PageImpl<Nutzen>(list, PageRequest.of(currentPage, pageSize), nutzens.size() );
		
		
		return nutzenPage;
	}

	

	@Override
	public Page<Nutzen> sucheMitDatum(Pageable pageable, LocalDate vonDatum,
			LocalDate bisDatum) {

		TypedQuery<Nutzen> query =entityManager.createNamedQuery("Nutzen.sucheMitDatum", Nutzen.class)
				.setParameter("vonDatum", vonDatum)
				.setParameter("bisDatum", bisDatum);
		List<Nutzen> nutzens = query.getResultList();
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		
		List<Nutzen> list;
		
		if(nutzens.size() < startItem ){
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem  + pageSize, nutzens.size());
			list = nutzens.subList(startItem, toIndex);
		}
		
		Page<Nutzen> nutzenPage = new PageImpl<Nutzen>(list, PageRequest.of(currentPage, pageSize), nutzens.size() );
		
		
		return nutzenPage;
	}

	@Override
	public List<Nutzen> findAllOrderById() {
		
		TypedQuery<Nutzen> query=entityManager.createNamedQuery("Nutzen.allOrderById", Nutzen.class);
			
		
		return query.getResultList();
	}

	
	

	
	

}
