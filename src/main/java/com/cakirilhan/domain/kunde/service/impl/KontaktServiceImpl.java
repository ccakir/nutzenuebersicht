package com.cakirilhan.domain.kunde.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cakirilhan.domain.kunde.Kontakt;
import com.cakirilhan.domain.kunde.repository.KontaktRepository;
import com.cakirilhan.domain.kunde.repository.service.KontaktService;

@Service
public class KontaktServiceImpl implements KontaktService {

	@Autowired
	KontaktRepository kontaktRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public Kontakt saveKontakt(Kontakt kontakt) {
		return kontaktRepository.save(kontakt);
	}

	@Override
	public void updateKontakt(Kontakt kontakt) {

		try {
			entityManager
					.createQuery(
							"UPDATE Kontakt k SET  k.anrede=?2,"
									+ "k.titel=?3, k.vorname=?4, k.nachname=?5, k.telefon=?6, k.mobil=?7,"
									+ "k.fax=?8, k.email=?9, k.kunde=?10 WHERE k.kontaktId=?1")
					.setParameter(1, kontakt.getKontaktId())
					.setParameter(2, kontakt.getAnrede())
					.setParameter(3, kontakt.getTitel())
					.setParameter(4, kontakt.getVorname())
					.setParameter(5, kontakt.getNachname())
					.setParameter(6, kontakt.getTelefon())
					.setParameter(7, kontakt.getMobil())
					.setParameter(8, kontakt.getFax())
					.setParameter(9, kontakt.getEmail())
					.setParameter(10, kontakt.getKunde()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteKontakt(long id) {

		Optional<Kontakt> kontakt = kontaktRepository.findById(id);

		if (kontakt.isPresent()) {

			try {

				kontaktRepository.delete(kontakt.get());

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}

		if (kontaktRepository.findById(id).isPresent()) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean deleteKontakte(long[] ids) {
		int row = ids.length;
		int i = 0;

		while (i < row) {

			Optional<Kontakt> kontakt = kontaktRepository.findById(ids[i]);
			if (kontakt.isPresent()) {
				try {
					kontaktRepository.delete(kontakt.get());
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

			}
			i++;
		}

		int k = 0;
		while (k < row) {

			Optional<Kontakt> kontakt = kontaktRepository.findById(ids[k]);
			if (kontakt.isPresent()) {
				return false;
			}
			k++;
		}

		return true;

	}

	@Override
	public List<Kontakt> findContactsByLastname(String letter) {
		
		System.out.println(letter);
		TypedQuery<Kontakt> query = entityManager.createNamedQuery("Kontakt.findContactByLastname", Kontakt.class)
				.setParameter("letter", letter + "%");
				
		
		
		return query.getResultList();
	}

}
