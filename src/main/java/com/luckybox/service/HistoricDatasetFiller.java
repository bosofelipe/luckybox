package com.luckybox.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class HistoricDatasetFiller {
	private static Logger LOGGER = LogManager.getLogger(HistoricDatasetFiller.class);

	@Inject
	private HistoricRepository historicRepository;
	
	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	@Inject
	private HistoricService historicService;
	
	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepoImpl;
	
	public void fillDataSet(LotteryType type) {
		LOGGER.info(String.format("Start Fill dataset %s", type.getName()));
		List<Historic> allConcurses = historicRepository.findAllByTypeOrderByConcurse(type);
		allConcurses.stream().forEach(c-> fillFields(c, type));
		LOGGER.info(String.format("Finish Fill dataset %s", type.getName()));
	}
	
	public void fillAlreadyDrawnField(LotteryType type) {
		LOGGER.info(String.format("Start already drawn %s", type.getName()));
		List<Historic> allConcurses = historicRepositoryImpl.findAllByAlreadyDrawnIsNullAndType(type);
		allConcurses.stream().forEach(c-> updateWhenHistoricAlreadyDrawn(c, type));
		LOGGER.info(String.format("Finish already drawn %s", type.getName()));
	}
	
	public Integer dozensLastConcurse(Long concurse, LotteryType type) {
		if (concurse == 1)
			return 0;
		else{
			Historic previousConcurse = historicRepository.findByConcurseAndType(concurse-1, type);
			Historic currentConcurse = historicRepository.findByConcurseAndType(concurse, type);
			return getDozensAtHistoric(DozenMapper.toList(previousConcurse), DozenMapper.toList(currentConcurse)).size();
		}
	}

	public List<Integer> getDozensAtHistoric(List<Integer> historic, List<Integer> dozens) {
		List<Integer> collect = historic.stream().filter(c -> dozens.contains(c)).collect(toList());
		return collect;
	}

	public Integer calculateVariationSum(Long concurse, LotteryType type) {
		if (concurse == 1)
			return 0;
		else
			return calculateVariationWhenNotFirstConcurse(concurse, type);
	}

	private void fillFields(Historic historic, LotteryType type) {
		Long concurse = historic.getConcurse();
		Integer variationSum = calculateVariationSum(concurse, type);
		Integer dozensLastConcurse = dozensLastConcurse(concurse, type);
		HistoricDataset dataset = historicDatasetRepoImpl.findByConcurseAndType(concurse, type);
		if(dataset != null) {
			LOGGER.info(String.format("Fill fields to concurse: %s, type: %s", concurse, type.getName()));
			dataset.setDozensLastRaffle(dozensLastConcurse);
			dataset.setVariationSum(variationSum);
			historicDatasetRepoImpl.updateVariationAndDozensLastRaffle(dataset, type);
		}
	}
	
	private void updateWhenHistoricAlreadyDrawn(Historic historic, LotteryType type) {
		DozenDTO dozenDTO = DozenMapper.toDTO(historic);
		if(historicService.findHistoricWithDozensNEConcurse(dozenDTO).isEmpty());{
			historicRepositoryImpl.updateAlreadyDrawn(historic.getConcurse(), type);
			LOGGER.info(String.format("Update already drawn: %s, type: %s", historic.getConcurse(), type.getName()));
		}
	}
	
	private Integer calculateVariationWhenNotFirstConcurse(Long concurse, LotteryType type) {
		Historic previousConcurse = historicRepository.findByConcurseAndType(concurse -1, type);
		Historic currentConcurse = historicRepository.findByConcurseAndType(concurse, type);
		List<Integer> listOfNumbersPreviousConcurse = DozenMapper.toList(previousConcurse);
		List<Integer> listOfNumbersCurrentConcurse = DozenMapper.toList(currentConcurse);
		int variation = sumDozens(listOfNumbersCurrentConcurse) - sumDozens(listOfNumbersPreviousConcurse);
		return variation;
	}
	
	private Integer sumDozens(List<Integer> dozens) {
		return dozens.stream().mapToInt(Number::intValue).sum();
	}
}
