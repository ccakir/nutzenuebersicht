package com.cakirilhan.domain.kunde.service.impl;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cakirilhan.domain.kunde.Taetigkeit;
import com.cakirilhan.domain.kunde.repository.TaetigkeitRepository;
import com.cakirilhan.domain.kunde.repository.service.TaetigkeitService;

@Service
public class TaetigkeitServiceImpl implements TaetigkeitService {

	@Autowired
	private TaetigkeitRepository taetigkeitRepository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public void saveTaetigkeit(Taetigkeit taetigkeit) {
		taetigkeitRepository.save(taetigkeit);

	}

	@Override
	public boolean deleteTaetigkeit(long id) {

		Optional<Taetigkeit> taetigkeit = taetigkeitRepository.findById(id);

		if (taetigkeit.isPresent()) {
			try {
				taetigkeitRepository.delete(taetigkeit.get());

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}

		if (taetigkeitRepository.findById(id).isPresent()) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean deleteTaetigkeiten(long[] ids) {
		int row = ids.length;
		int i = 0;

		while (i < row) {

			Optional<Taetigkeit> taetigkeit = taetigkeitRepository
					.findById(ids[i]);

			if (taetigkeit.isPresent()) {
				try {
					taetigkeitRepository.delete(taetigkeit.get());
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			i++;
		}

		int k = 0;
		while (k < row) {
			Optional<Taetigkeit> taetigkeit = taetigkeitRepository
					.findById(ids[k]);
			if (taetigkeit.isPresent()) {
				return false;
			}
			k++;
		}

		return true;
	}

	@Override
	@Transactional
	public void updateTaetigkeit(Taetigkeit taetigkeit) {
		try {

			entityManager
					.createQuery(
							"UPDATE Taetigkeit t SET t.name=?1, t.beschreibung=?2"
									+ "WHERE t.id=?3")
					.setParameter(1, taetigkeit.getName())
					.setParameter(2, taetigkeit.getBeschreibung())
					.setParameter(3, taetigkeit.getId()).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
