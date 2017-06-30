package com.luckybox.service;

import static com.luckybox.mapper.HistoricMapper.toList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.mapper.HistoricMapper;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;

@Service
public class HistoricDatasetFiller {

	@Inject
	private HistoricService historicService;
	
	@Inject
	private HistoricDatasetRepository historicDatasetRepo;
	
	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepoImpl;
	
	public void fillDataSet() {
		List<Historic> allConcurses = historicService.findAll();
		allConcurses.stream().forEach(c-> fillFields(c));
	}
	
	public void fillAlreadyDrawnField() {
		List<Historic> allConcurses = historicService.findAll();
		allConcurses.stream().forEach(c-> updateWhenHistoricAlreadyDrawn(c));
	}
	
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

	private void fillFields(Historic historic) {
		Long concurse = historic.getConcurse();
		Integer variationSum = calculateVariationSum(concurse);
		Integer dozensLastConcurse = dozensLastConcurse(concurse);
		HistoricDataset dataset = historicDatasetRepo.findByConcurse(concurse);
		dataset.setDozensLastRaffle(dozensLastConcurse);
		dataset.setVariationSum(variationSum);
		historicDatasetRepoImpl.updateVariationAndDozensLastRaffle(dataset);
	}
	
	private void updateWhenHistoricAlreadyDrawn(Historic historic) {
		if(!historicService.findHistoricWithDozensNEConcurse(historic).isEmpty());
			historicService.updateAlreadyDrawn(historic.getConcurse());
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
