package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.CombinationDozens;
import com.luckybox.repository.CombinationDozensRepositoryImpl;

@Service
public class RecomendationService {

	@Inject
	private CombinationDozensRepositoryImpl combinationRepositoryImpl;
	
	public List<CombinationDozens> listCombinations(Long limit){
		return combinationRepositoryImpl.listCombinations(limit);
	}
	
}
