package com.luckybox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QHistoricDataset;
import com.luckybox.dto.BetDTO;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.HitsDTO;
import com.luckybox.dto.HitsDTOComparator;
import com.luckybox.exception.DozensLimitExceeded;
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

	public List<HitsDTO> listHistByConcurse(BetDTO betDTO) {
		List<HitsDTO> hits = Lists.newArrayList();
		List<Historic> concurses = repository.findAllByType(betDTO.getType());
		concurses.forEach(e -> count(hits, e, betDTO));
		Collections.sort(hits, new HitsDTOComparator());
		return hits;
	}

	public Map<Integer, List<Long>> listGroupedHistByConcurse(BetDTO betDTO) {
		List<HitsDTO> hits = Lists.newArrayList();
		List<Historic> concurses = repository.findAllByType(betDTO.getType());
		concurses.forEach(e -> count(hits, e, betDTO));

		Map<Integer, List<Long>> groupedHits = new TreeMap<Integer, List<Long>>(Collections.reverseOrder());
		for (HitsDTO hitsDTO : hits) {
			Integer key = hitsDTO.getHits();
			if (groupedHits.get(key) == null) {
				groupedHits.put(key, new ArrayList<Long>());
			}
			groupedHits.get(key).add(hitsDTO.getConcurse());
		}
		
		return groupedHits;
	}

	private Object count(List<HitsDTO> hits, Historic e, BetDTO betDTO) {
		validateNumberOfDozens(betDTO);
		List<Integer> dozensDTO = DozenMapper.toList(betDTO);
		List<Integer> historicDozens = DozenMapper.toList(e);
		Collections.sort(dozensDTO);
		Collections.sort(historicDozens);
		dozensDTO.retainAll(historicDozens);
		if (betDTO.getFoundByHits() != null) {
			if (dozensDTO.size() >= betDTO.getFoundByHits()) {
				hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
			}
		} else {
			hits.add(HitsDTO.builder().concurse(e.getConcurse()).hits(dozensDTO.size()).build());
		}
		return hits;
	}

	private void validateNumberOfDozens(BetDTO betDTO) {
		if (betDTO.getType() == LotteryType.LOTOFACIL && betDTO.getDozens().size() < 15
				&& betDTO.getDozens().size() > 18) {
			throw new DozensLimitExceeded("Number of dozens exceeded!!!");
		}
		if (betDTO.getType() == LotteryType.MEGASENA && betDTO.getDozens().size() < 6
				&& betDTO.getDozens().size() > 15) {
			throw new DozensLimitExceeded("Number of dozens exceeded!!!");
		}
		if (betDTO.getType() == LotteryType.QUINA && betDTO.getDozens().size() < 5 && betDTO.getDozens().size() > 15) {
			throw new DozensLimitExceeded("Number of dozens exceeded!!!");
		}
		if (betDTO.getType() == LotteryType.LOTOMANIA && betDTO.getDozens().size() != 50) {
			throw new DozensLimitExceeded("Number of dozens exceeded!!!");
		}
	}
}
