package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.luckybox.domain.Historic;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepositoryImpl;

public class LastRaffleRule implements RuleChain {

	private RuleChain chain;

	private HistoricRepositoryImpl historicRepositoryImpl;
	
	public LastRaffleRule() {
	}
	
	public LastRaffleRule(HistoricRepositoryImpl historicRepositoryImpl) {
		this.historicRepositoryImpl = historicRepositoryImpl;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleType> rules) {
		List<Historic> lastRaffles = historicRepositoryImpl.getLastRaffles(1);
		if (!lastRaffles.isEmpty())
			checkLastDozens(dozens, rules, lastRaffles);
		chain.checkRule(dozens, rules);
	}

	private void checkLastDozens(List<Integer> dozens, List<RuleType> rules, List<Historic> lastRaffles) {
		Historic historic = lastRaffles.get(0);
		List<Integer> lastDozens = DozenMapper.toList(historic);
		int dozensMatch = dozens.stream().filter(el -> lastDozens.stream().anyMatch(el::equals)).collect(toList())
				.size();
		if (dozensMatch < 7 || dozensMatch > 11)
			rules.add(RuleType.LAST_RAFFLE);
	}

}
