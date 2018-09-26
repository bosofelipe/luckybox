package com.luckybox.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.CombinationDozen;
import com.luckybox.domain.CombinationDozenData;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.CombinationDozenDataRepository;
import com.luckybox.repository.CombinationDozenRepository;
import com.luckybox.repository.HistoricRepository;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CombinationDozenService {
	private static final String SEPARATOR = "-";

	@Inject
	private HistoricRepository historicRepository;

	@Inject
	private CombinationDozenRepository combinationDozenRepository;
	
	@Inject
	private CombinationDozenDataRepository combinationDozenDataRepository;

	public List<CombinationDozen> generateCombination(Integer combinations, String type)
			throws InterruptedException, IOException {
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		List<CombinationDozen> combinationDozen = Lists.newArrayList();

		List<Historic> concurses = historicRepository.findAllByTypeOrderByConcurse(lotteryType);

		for (int i = 2; i < combinations; i++) {
			Integer comb = new Integer(i);
			CombinationDozenData findByKeyValuesAndType = combinationDozenDataRepository.findByKeyValuesAndType(i, lotteryType);
			if(findByKeyValuesAndType == null){
				concurses.forEach(e -> load(e, comb, lotteryType));
				combinationDozenDataRepository.save(CombinationDozenData.builder().keyValues((i)).type(lotteryType).build());
			}
		}

		return combinationDozen;

	}

	private Object load(Historic e, Integer combinations, LotteryType type) {
		List<Integer> values = DozenMapper.toList(e);
		Integer[] finalResult = values.toArray(new Integer[values.size()]);
		Integer[] saida = null;
		Combination comb1 = new Combination(finalResult, combinations);
		while (comb1.hasNext()) {
			saida = comb1.next();
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < saida.length; i++) {
				b.append(saida[i]).append(SEPARATOR);
			}
			String key = b.substring(0, b.length() - 1).toString();
			log.info("Key: "+ key + " Combinações: " + combinations + " Concurse: " + e.getConcurse());
			save(type, key, combinations, e.getConcurse());
		}
		return e;
	}

	private CombinationDozen save(LotteryType type, String key, Integer combinations, Long concurse) {

		CombinationDozen dozen = combinationDozenRepository.findByKeyAndType(key, type);
		if (dozen != null && dozen.getId() != null) {
			dozen.setKeyValues(combinations);
			dozen.setType(type);
			dozen.setQuantity(dozen.getQuantity() + 1);
			dozen.setLastConcurse(concurse.intValue());
			return combinationDozenRepository.save(dozen);

		} else {
			CombinationDozen combinationDozen = CombinationDozen.builder().type(type)//
					.keyValues(combinations)//
					.key(key)//
					.quantity(1)//
					.lastConcurse(concurse.intValue())//
					.build();
			return combinationDozenRepository.save(combinationDozen);
		}
	}
}
