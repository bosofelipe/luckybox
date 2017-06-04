package com.luckybox.service;

import static com.luckybox.mapper.HistoricMapper.toList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.mapper.HistoricMapper;

@Service
public class HistoricDatasetFiller {

	@Inject
	private HistoricService historicService;
	
	public Integer dozensLastConcurse(Long concurse) {
		if (concurse == 1)
			return 0;
		else{
			Historic previousConcurse = historicService.findByConcurse(concurse-1);
			Historic currentConcurse = historicService.findByConcurse(concurse);
			return getDozensAtHistoric(toList(previousConcurse), toList(currentConcurse)).size();
		}
	}

	public List<Integer> getDozensAtHistoric(List<Integer> historic, List<Integer> dozens) {
		List<Integer> collect = historic.stream().filter(c -> dozens.contains(c)).collect(toList());
		return collect;
	}

	public Integer calculateVariationSum(Long concurse) {
		if (concurse == 1)
			return 0;
		else
			return calculateVariationWhenNotFirstConcurse(concurse);
	}

	private Integer calculateVariationWhenNotFirstConcurse(Long concurse) {
		Historic previousConcurse = historicService.findByConcurse(concurse -1);
		Historic currentConcurse = historicService.findByConcurse(concurse);
		List<Integer> listOfNumbersPreviousConcurse = HistoricMapper.toList(previousConcurse);
		List<Integer> listOfNumbersCurrentConcurse = HistoricMapper.toList(currentConcurse);
		int variation = sumDozens(listOfNumbersCurrentConcurse) - sumDozens(listOfNumbersPreviousConcurse);
		return variation < 0 ? variation * -1 : variation;
	}
	
	private Integer sumDozens(List<Integer> dozens) {
		return dozens.stream().mapToInt(Number::intValue).sum();
	}
}
