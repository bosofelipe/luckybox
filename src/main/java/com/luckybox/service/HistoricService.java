package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class HistoricService {

	@Inject
	private HistoricRepositoryImpl repository;
	
	public List<Historic> findHistoricWithDozens(DozenDTO dozenDTO) {
		return repository.findHistoricByDozensEQConcurse(dozenDTO);
	}
	
	public List<Historic> findHistoricWithDozensNEConcurse(DozenDTO dozenDTO) {
		return repository.findHistoricByDozensNEConcurse(dozenDTO);
	}
	
}
