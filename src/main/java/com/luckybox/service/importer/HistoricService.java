package com.luckybox.service.importer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.repository.HistoricRepository;

@Service
public class HistoricService {

	@Inject
	private HistoricRepository repository;

	public Historic findByConcurse(Long concurse) {
		return repository.findByConcurse(concurse - 1);
	}

	public boolean findHistoricWithDozens(Historic historic) {
		return !repository.findHistoricWithDozens(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(), historic.getDozen4(), historic.getDozen5(),
				historic.getDozen6(), historic.getDozen7(), historic.getDozen8(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15(), historic.getConcurse()).isEmpty();
	}
	
	public List<Historic> findAll() {
		return repository.findAll();
	}
}
