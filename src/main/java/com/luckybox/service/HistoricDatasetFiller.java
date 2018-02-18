package com.luckybox.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.CombinationDozens;
import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.CombinationDozensRepositoryImpl;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class HistoricDatasetFiller {

	@Inject
	private HistoricRepository historicRepository;
	
	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	@Inject
	private HistoricService historicService;
	
	@Inject
	private HistoricDatasetRepository historicDatasetRepo;
	
	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepoImpl;
	
	@Inject
	private CombinationDozensRepositoryImpl combinationRepositoryImpl;
	
	public void fillDataSet(LotteryType type) {
		List<Historic> allConcurses = historicRepository.findAllByType(type);
		allConcurses.stream().forEach(c-> fillFields(c));
	}
	
	public void fillAlreadyDrawnField(LotteryType type) {
		List<Historic> allConcurses = historicRepository.findAllByAlreadyDrawnIsNullAndType(type);
		allConcurses.stream().forEach(c-> updateWhenHistoricAlreadyDrawn(c));
	}
	
	public Integer dozensLastConcurse(Long concurse) {
		if (concurse == 1)
			return 0;
		else{
			Historic previousConcurse = historicRepository.findByConcurse(concurse-1);
			Historic currentConcurse = historicRepository.findByConcurse(concurse);
			return getDozensAtHistoric(DozenMapper.toList(previousConcurse), DozenMapper.toList(currentConcurse)).size();
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
		log.info("Check concurse " + historic.getConcurse());
		if(historicService.findHistoricWithDozensNEConcurse(DozenMapper.toDTO(historic)).isEmpty());
			historicRepositoryImpl.updateAlreadyDrawn(historic.getConcurse());
		CombinationDozens combination = combinationRepositoryImpl.findCombinationWithHistoric(historic);
		if (combination != null)
			combinationRepositoryImpl.markWithDrawn(combination.getId());
	}
	
	private Integer calculateVariationWhenNotFirstConcurse(Long concurse) {
		Historic previousConcurse = historicRepository.findByConcurse(concurse -1);
		Historic currentConcurse = historicRepository.findByConcurse(concurse);
		List<Integer> listOfNumbersPreviousConcurse = DozenMapper.toList(previousConcurse);
		List<Integer> listOfNumbersCurrentConcurse = DozenMapper.toList(currentConcurse);
		int variation = sumDozens(listOfNumbersCurrentConcurse) - sumDozens(listOfNumbersPreviousConcurse);
		return variation < 0 ? variation * -1 : variation;
	}
	
	private Integer sumDozens(List<Integer> dozens) {
		return dozens.stream().mapToInt(Number::intValue).sum();
	}
}
