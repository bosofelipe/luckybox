package com.luckybox.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.NumberInfo;
import com.luckybox.repository.NumberInfoRepository;

@Component
public class NumberInfoService {

	@Inject
	private HistoricService historicService;
	
	@Inject
	private NumberInfoRepository numberInfoRepository;
	
	public NumberInfo persistNumberInfo(int number) {
		return numberInfoRepository.save(createNumberInfo(number));
	}

	private NumberInfo createNumberInfo(int number) {
		Long lastDrawByNumber = historicService.findByNumber(number);
		Long countNumberDraw = historicService.countNumberDraw(number);
		return NumberInfo.builder().number(number).countDrawNumber(countNumberDraw).lastDrawNumber(historicService.getLastIndexRaffle() - lastDrawByNumber)
				.lastEfetiveConcurseNumber(lastDrawByNumber).build();
		
	}

	public List<NumberInfo> generateNumberInfo() {
		List<NumberInfo> numberInfos = new ArrayList<>();
		for(int i=1;i<=25;i++)
			numberInfos.add(persistNumberInfo(i));
		return numberInfos;
	}
}
