package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Historic;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class LastRaffleRule implements RuleChain {

	private RuleChain chain;

	@Inject
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
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules) {
		List<Historic> lastRaffles = historicRepositoryImpl.getLastRaffles(1);
		if (!lastRaffles.isEmpty())
			checkLastDozens(dozens, rules, lastRaffles);
		chain.checkRule(dozens, rules);
	}

	private void checkLastDozens(List<Integer> dozens, List<RuleDTO> rules, List<Historic> lastRaffles) {
		Historic historic = lastRaffles.get(0);
		List<Integer> lastDozens = DozenMapper.toList(historic);
		int dozensMatch = dozens.stream().filter(el -> lastDozens.stream().anyMatch(el::equals)).collect(toList())
				.size();
		if (dozensMatch < 7)
			rules.add(buildRule(dozensMatch, RuleType.LAST_RAFFLE_LOW));
		if(dozensMatch > 11)
			rules.add(buildRule(dozensMatch, RuleType.LAST_RAFFLE_LOW));
	}

}
