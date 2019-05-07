package com.luckybox.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.DozenInfoSequence;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.DozenInfoRepositoryImpl;
import com.luckybox.repository.DozenInfoSequenceRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class DozenInfoService {
	private static Logger log = LogManager.getLogger(DozenInfoService.class);

	@Inject
	private HistoricRepositoryImpl historicService;

	@Inject
	private DozenInfoRepository dozenInfoRepository;

	@Inject
	private DozenInfoRepositoryImpl dozenInfoRepositoryImpl;

	@Inject
	private SequenceAnalyser sequenceAnalyser;

	@Inject
	private DozenInfoSequenceRepository dozenInfoSequenceRepository;

	public List<DozenInfo> generateDozenInfo(String type) {
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		List<DozenInfo> dozenInfo = new ArrayList<>();
		if (LotteryType.LOTOFACIL.equals(lotteryType)) {
			for (int i = 1; i <= 25; i++) {
				dozenInfo.add(persistDozenInfo(i, lotteryType));
			}
		}
		if (LotteryType.MEGASENA.equals(lotteryType)) {
			for (int i = 1; i <= 60; i++) {
				dozenInfo.add(persistDozenInfo(i, lotteryType));
			}
		}
		if (LotteryType.QUINA.equals(lotteryType)) {
			for (int i = 1; i <= 80; i++) {
				dozenInfo.add(persistDozenInfo(i, lotteryType));
			}
		}
		if (LotteryType.LOTOMANIA.equals(lotteryType)) {
			for (int i = 0; i <= 99; i++) {
				dozenInfo.add(persistDozenInfo(i, lotteryType));
			}
		}
		return dozenInfo;
	}

	private DozenInfo persistDozenInfo(int dozen, LotteryType lotteryType) {
		if (lotteryType != null) {
			List<Integer> listConcursesWithDozen = historicService.listConcursesWithDozen(dozen, lotteryType);

			DozenInfo createDozenInfo = createDozenInfo(dozen, lotteryType, listConcursesWithDozen);
			List<DozenInfoSequence> sequences = new SequenceAnalyser().sequence(listConcursesWithDozen);

			DozenInfo dozenInfo = dozenInfoRepository.save(createDozenInfo);
			log.info(String.format("Saved sequence to dozen: %s, type: %s", dozenInfo.getNumber(),
					dozenInfo.getType().getName()));

			sequences.forEach(e -> saveSequence(dozenInfo, e));

			return dozenInfo;

		}
		return DozenInfo.builder().build();
	}

	private void saveSequence(DozenInfo dozenInfo, DozenInfoSequence sequence) {
		sequence.setDozenInfo(dozenInfo);
		log.info(String.format("Save sequence of dozen: %s, type: %s", dozenInfo.getNumber(),
				dozenInfo.getType().getName()));
		//dozenInfoSequenceRepository.save(sequence);
	}

	private DozenInfo createDozenInfo(int dozen, LotteryType lotteryType, List<Integer> listConcursesWithDozen) {
		DozenInfo info = dozenInfoRepositoryImpl.findByDozenAndType(dozen, lotteryType);
		Long lastDrawByNumber = historicService.findByNumber(dozen, lotteryType);
		Long countNumberDraw = historicService.countNumberDraw(dozen, lotteryType);
		List<Integer> greaterSequence = sequenceAnalyser.getGreaterSequence(listConcursesWithDozen);

		if (info == null)
			return DozenInfo.builder().type(lotteryType).number(dozen).countDrawNumber(countNumberDraw)
					.currentSequenceDrawn(findCurrentSequenceDrawn(dozen, lotteryType))
					.lastDrawNumber(getLastDrawNumber(lotteryType, lastDrawByNumber))
					.lastEfetiveConcurseNumber(lastDrawByNumber).maxSequenceDrawn(greaterSequence.get(0).longValue())
					.qtSequenceDrawn(greaterSequence.get(1).longValue()).build();
		else {
			info.setCountDrawNumber(countNumberDraw);
			info.setCurrentSequenceDrawn(findCurrentSequenceDrawn(dozen, lotteryType));
			info.setLastDrawNumber(getLastDrawNumber(lotteryType, lastDrawByNumber));
			info.setLastEfetiveConcurseNumber(lastDrawByNumber);
			info.setMaxSequenceDrawn(greaterSequence.get(0).longValue());
			info.setQtSequenceDrawn(greaterSequence.get(1).longValue());
			return info;
		}

	}

	private long getLastDrawNumber(LotteryType lotteryType, Long lastDrawByNumber) {
		if (lastDrawByNumber == null)
			return 0L;
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
