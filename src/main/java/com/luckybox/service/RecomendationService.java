package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Combination;
import com.luckybox.repository.CombinationRepositoryImpl;

@Service
public class RecomendationService {

	@Inject
	private CombinationRepositoryImpl combinationRepositoryImpl;
	
	public List<Combination> listCombinations(Long limit){
		return combinationRepositoryImpl.listCombinations(limit);
	}
	
}
