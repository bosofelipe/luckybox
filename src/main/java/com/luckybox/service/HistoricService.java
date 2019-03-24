package com.luckybox.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.HistoricDataSetDTO;
import com.luckybox.dto.HitsDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;
import com.querydsl.core.types.dsl.NumberPath;

@Service
public class HistoricService {
	QHistoricDataset qHistoricDataset = QHistoricDataset.historicDataset;

	@Inject
	private HistoricRepository repository;

	@Inject
	private HistoricRepositoryImpl repositoryImpl;

	@Inject
	private HistoricDatasetRepositoryImpl dataSetRepository;

	public List<Historic> findHistoricWithDozens(DozenDTO dozenDTO) {
		return repositoryImpl.findHistoricByDozensEQConcurse(dozenDTO);
	}

	public List<Historic> findHistoricWithDozensNEConcurse(DozenDTO dozenDTO) {
		return repositoryImpl.findHistoricByDozensNEConcurse(dozenDTO);
	}

	private NumberPath<Integer> getFieldPath(String fieldName) {
		Map<String, NumberPath<Integer>> fields = new HashMap<>();
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
		if (path == null) {
			throw new IllegalArgumentException("Invalid field name");
		}
		return dataSetRepository.countFieldByValue(lotteryType, path);
	}

	public List<HitsDTO> listHistByConcurse(DozenDTO dozenDTO) {
		List<HitsDTO> hits = Lists.newArrayList();
		List<Historic> concurses = repository.findAllByType(dozenDTO.getType());
		concurses.forEach(e -> count(hits, e, dozenDTO));
		return hits;
	}

	private Object count(List<HitsDTO> hits, Historic e, DozenDTO dozenDTO) {
		List<Integer> dozensDTO = DozenMapper.toList(dozenDTO);
		List<Integer> historicDozens = DozenMapper.toList(e);
		Collections.sort(dozensDTO);
		Collections.sort(historicDozens);
		dozensDTO.retainAll(historicDozens);
		if (dozenDTO.getFoundByHits() != null) {
			if(dozensDTO.size() >= dozenDTO.getFoundByHits()) {
				hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
			}
		} else {
			hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
		}

		return hits;
	}
}
