package com.luckybox.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.Historic;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class DozenInfoService {

	@Inject
	private HistoricRepositoryImpl historicService;

	@Inject
	private DozenInfoRepository DozenInfoRepository;

	@Inject
	private SequenceAnalyser sequenceAnalyser;

	public DozenInfo persistDozenInfo(int dozen) {
		return DozenInfoRepository.save(createDozenInfo(dozen));
	}

	private DozenInfo createDozenInfo(int dozen) {
		Long lastDrawByNumber = historicService.findByNumber(dozen);
		Long countNumberDraw = historicService.countNumberDraw(dozen);
		List<Integer> listConcursesWithDozen = historicService.listConcursesWithDozen(dozen);
		List<Integer> greaterSequence = sequenceAnalyser.getGreaterSequence(listConcursesWithDozen);

		return DozenInfo.builder().number(dozen).countDrawNumber(countNumberDraw)
				.currentSequenceDrawn(findCurrentSequenceDrawn(dozen))
				.lastDrawNumber(historicService.getLastIndexRaffle() - lastDrawByNumber)
				.lastEfetiveConcurseNumber(lastDrawByNumber).maxSequenceDrawn(greaterSequence.get(0).longValue())
				.qtSequenceDrawn(greaterSequence.get(1).longValue()).build();

	}

	private long findCurrentSequenceDrawn(int dozen) {
		List<Historic> lastRaffles = historicService.getLastRaffles(30);
		for (Historic hist : lastRaffles) {
			if (!DozenMapper.toList(hist).contains(dozen)) {
				return lastRaffles.get(0).getConcurse() - hist.getConcurse();
			}
		}
		return 0;
	}

	public List<DozenInfo> generateDozenInfo() {
		List<DozenInfo> DozenInfos = new ArrayList<>();
		for (int i = 1; i <= 25; i++)
			DozenInfos.add(persistDozenInfo(i));
		return DozenInfos;
	}
}
