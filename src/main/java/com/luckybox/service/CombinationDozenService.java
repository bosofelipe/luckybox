package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.CombinationDozen;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.CombinationDozenRepository;
import com.luckybox.repository.HistoricRepository;

@Service
public class CombinationDozenService {
	private static Logger logger = LogManager.getLogger(CombinationDozenService.class);
	
	private static final String SEPARATOR = "-";

	@Inject
	private HistoricRepository historicRepository;

	@Inject
	private CombinationDozenRepository combinationDozenRepository;

	public List<CombinationDozen> generateCombination(Integer combinations, String type){
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		List<CombinationDozen> combinationDozen = Lists.newArrayList();

		List<Historic> concurses = historicRepository.findAllByTypeOrderByConcurse(lotteryType);

		concurses.forEach(hist -> saveCombination(hist, combinations, lotteryType));

		return combinationDozen;

	}

	private void saveCombination(Historic historic, Integer combinations, LotteryType lotteryType) {
		for (int i = 2; i < combinations; i++) {
			load(historic, Integer.valueOf(i), lotteryType);
		}
	}

	private Object load(Historic e, Integer keyLength, LotteryType type) {
		List<Integer> values = DozenMapper.toList(e);
		Integer[] finalResult = values.toArray(new Integer[values.size()]);
		Integer[] saida = null;
		Combination comb1 = new Combination(finalResult, keyLength);
		while (comb1.hasNext()) {
			saida = comb1.next();
			StringBuilder value = new StringBuilder();
			for (int i = 0; i < saida.length; i++) {
				value.append(saida[i]).append(SEPARATOR);
			}
			String key = value.substring(0, value.length() - 1);
			save(type, key, keyLength, e.getConcurse());
		}
		return e;
	}

	private CombinationDozen save(LotteryType type, String key, Integer combinations, Long concurse) {
		CombinationDozen dozen = combinationDozenRepository.findByConcurseAndKeyAndType(concurse, key, type);
		if (dozen == null) {
			CombinationDozen combinationDozen = CombinationDozen.builder().type(type)//
					.keyValues(combinations)//
					.key(key)//
					.concurse(concurse)//
					.build();
			logger.info(String.format("Save combination key: %s, concurse: %s ", key, concurse.toString()));
			return combinationDozenRepository.save(combinationDozen);
		} else {
			logger.info(String.format("Save combination key: %s, concurse: %s ", dozen.getKey(), dozen.getConcurse().toString()));
			return dozen;
		}
	}
}
