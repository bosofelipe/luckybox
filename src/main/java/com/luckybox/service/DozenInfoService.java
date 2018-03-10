package com.luckybox.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class DozenInfoService {

	@Inject
	private HistoricRepositoryImpl historicService;

	@Inject
	private DozenInfoRepository dozenInfoRepository;

	@Inject
	private SequenceAnalyser sequenceAnalyser;

	public List<DozenInfo> generateDozenInfo(String type) {
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		List<DozenInfo> dozenInfo = new ArrayList<>();
		int maxDozens = 100;
		if(LotteryType.LOTOFACIL.equals(lotteryType))
			maxDozens = 25;
		for (int i = 1; i <= maxDozens; i++) {
			int dozen = i;
			if(i == 100)
				dozen = 0;
			dozenInfo.add(persistDozenInfo(dozen, lotteryType));
		}
		return dozenInfo;
	}
	
	private DozenInfo persistDozenInfo(int dozen, LotteryType lotteryType) {
		if(LotteryType.LOTOFACIL.equals(lotteryType)) {
			return dozenInfoRepository.save(createDozenInfo(dozen, LotteryType.LOTOFACIL));
		}
		if(LotteryType.LOTOMANIA.equals(lotteryType)) {
			return dozenInfoRepository.save(createDozenInfo(dozen, LotteryType.LOTOMANIA));
		}
		return DozenInfo.builder().build();
	}

	private DozenInfo createDozenInfo(int dozen, LotteryType lotteryType) {
		Long lastDrawByNumber = historicService.findByNumber(dozen,lotteryType);
		Long countNumberDraw = historicService.countNumberDraw(dozen,lotteryType);
		List<Integer> listConcursesWithDozen = historicService.listConcursesWithDozen(dozen,lotteryType);
		List<Integer> greaterSequence = sequenceAnalyser.getGreaterSequence(listConcursesWithDozen);

		return DozenInfo.builder().type(lotteryType).number(dozen).countDrawNumber(countNumberDraw)
				.currentSequenceDrawn(findCurrentSequenceDrawn(dozen, lotteryType))
				.lastDrawNumber(getLastDrawNumber(lotteryType, lastDrawByNumber))
				.lastEfetiveConcurseNumber(lastDrawByNumber).maxSequenceDrawn(greaterSequence.get(0).longValue())
				.qtSequenceDrawn(greaterSequence.get(1).longValue()).build();

	}

	private long getLastDrawNumber(LotteryType lotteryType, Long lastDrawByNumber) {
			return historicService.getLastIndexRaffle(lotteryType) - lastDrawByNumber;
	}

	private long findCurrentSequenceDrawn(int dozen, LotteryType lotteryType) {
		List<Historic> lastRaffles = historicService.getLastRaffles(30, lotteryType);
		for (Historic hist : lastRaffles) {
			if (!DozenMapper.toList(hist).contains(dozen)) {
				return lastRaffles.get(0).getConcurse() - hist.getConcurse();
			}
		}
		return 0;
	}
}
