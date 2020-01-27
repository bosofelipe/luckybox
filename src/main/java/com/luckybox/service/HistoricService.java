package com.luckybox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.Historic;
import com.luckybox.domain.QHistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.HitsDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class HistoricService {
	QHistoricDataset qHistoricDataset = QHistoricDataset.historicDataset;

	@Inject
	private HistoricRepository repository;

	@Inject
	private HistoricRepositoryImpl repositoryImpl;

	public List<Historic> findHistoricWithDozens(DozenDTO dozenDTO) {
		return repositoryImpl.findHistoricByDozensEQConcurse(dozenDTO);
	}

	public List<Historic> findHistoricWithDozensNEConcurse(DozenDTO dozenDTO) {
		return repositoryImpl.findHistoricByDozensNEConcurse(dozenDTO);
	}

	public List<HitsDTO> listHistByConcurse(DozenDTO dozenDTO) {
		List<HitsDTO> hits = Lists.newArrayList();
		List<Historic> concurses = repository.findAllByType(dozenDTO.getType());
		concurses.forEach(e -> count(hits, e, dozenDTO));
		return hits;
	}

	public Map<Integer, List<Long>> listGroupedHistByConcurse(DozenDTO dozenDTO) {
		List<HitsDTO> hits = Lists.newArrayList();
		List<Historic> concurses = repository.findAllByType(dozenDTO.getType());
		concurses.forEach(e -> count(hits, e, dozenDTO));

		Map<Integer, List<Long>> groupedHits = new HashMap<Integer, List<Long>>();
		for (HitsDTO hitsDTO : hits) {
			Integer key = hitsDTO.getHits();
			if (groupedHits.get(key) == null) {
				groupedHits.put(key, new ArrayList<Long>());
			}
			groupedHits.get(key).add(hitsDTO.getConcurse());
		}

		return groupedHits;
	}

	private Object count(List<HitsDTO> hits, Historic e, DozenDTO dozenDTO) {
		List<Integer> dozensDTO = DozenMapper.toList(dozenDTO);
		List<Integer> historicDozens = DozenMapper.toList(e);
		Collections.sort(dozensDTO);
		Collections.sort(historicDozens);
		dozensDTO.retainAll(historicDozens);
		if (dozenDTO.getFoundByHits() != null) {
			if (dozensDTO.size() >= dozenDTO.getFoundByHits()) {
				hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
			}
		} else {
			hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
		}

		return hits;
	}
}
