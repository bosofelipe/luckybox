package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.repository.HistoricRepository;

@Service
public class HistoricService {

	@Inject
	private HistoricRepository repository;
	
	public List<Historic> findHistoricWithDozens(Historic historic) {
		return repository.findHistoricWithDozens(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(), historic.getDozen4(), historic.getDozen5(),
				historic.getDozen6(), historic.getDozen7(), historic.getDozen8(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15(), historic.getConcurse());
	}
	
	public List<Historic> findHistoricWithDozensNEConcurse(Historic historic) {
		return repository.findHistoricByDozensNEConcurse(historic.getDozen1(), historic.getDozen2(), historic.getDozen3(), historic.getDozen4(), historic.getDozen5(),
				historic.getDozen6(), historic.getDozen7(), historic.getDozen8(),historic.getDozen9(), historic.getDozen10(), historic.getDozen11(),
				historic.getDozen12(), historic.getDozen13(), historic.getDozen14(), historic.getDozen15(), historic.getConcurse());
	}
}
