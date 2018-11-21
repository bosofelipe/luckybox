package com.luckybox.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.HistoricDataSetDTO;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepositoryImpl;
import com.querydsl.core.types.dsl.NumberPath;

@Service
public class HistoricService {
	QHistoricDataset qHistoricDataset = QHistoricDataset.historicDataset;
	
	@Inject
	private HistoricRepositoryImpl repository;
	
	@Inject
	private HistoricDatasetRepositoryImpl dataSetRepository;
	
	public List<Historic> findHistoricWithDozens(DozenDTO dozenDTO) {
		return repository.findHistoricByDozensEQConcurse(dozenDTO);
	}
	
	public List<Historic> findHistoricWithDozensNEConcurse(DozenDTO dozenDTO) {
		return repository.findHistoricByDozensNEConcurse(dozenDTO);
	}
	
	private NumberPath<Integer> getFieldPath(String fieldName){
		Map<String,NumberPath<Integer>> fields = new HashMap<>();
		fields.put("pair", qHistoricDataset.pair);
		fields.put("average", qHistoricDataset.pair);
		fields.put("fibonacci", qHistoricDataset.pair);
		fields.put("prime", qHistoricDataset.pair);
		fields.put("fibonacciPrime", qHistoricDataset.pair);
		fields.put("dozensLastRaffle", qHistoricDataset.pair);
		fields.put("dozensLastRaffle", qHistoricDataset.pair);
		fields.put("qtdSequences", qHistoricDataset.pair);
		return fields.get(fieldName);
	}
	
	public List<HistoricDataSetDTO> findFieldByType(LotteryType lotteryType, String field) {
		NumberPath<Integer> path = getFieldPath(field);
		if(path == null) {
			throw new IllegalArgumentException("Invalid field name");
		}
		return dataSetRepository.countFieldByValue(lotteryType, path);
	}
}
