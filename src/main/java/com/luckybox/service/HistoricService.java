package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.dto.HistoricDTO;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class HistoricService {

	@Inject
	private HistoricRepositoryImpl repository;
	
	public List<Historic> findHistoricWithDozens(HistoricDTO historicDTO) {
		return repository.findHistoricByDozensEQConcurse(historicDTO);
	}
	
	public List<Historic> findHistoricWithDozensNEConcurse(HistoricDTO historicDTO) {
		return repository.findHistoricByDozensNEConcurse(historicDTO);
	}
}
