package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class LastRaffleRule implements RuleChain {

	private RuleChain chain;

	private Integer minMatch;

	private Integer maxMatch;

	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;

	public LastRaffleRule(HistoricRepositoryImpl historicRepositoryImpl, Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
		this.historicRepositoryImpl = historicRepositoryImpl;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Historic> lastRaffles = historicRepositoryImpl.getLastRaffles(1, bet.getType());
		List<Integer> dozens = DozenMapper.toList(bet);
		checkLastDozens(dozens, rules, lastRaffles, bet.getType());
		this.chain.checkRule(bet, rules);
	}

	private void checkLastDozens(List<Integer> dozens, List<BetRule> rules, List<Historic> lastRaffles,
			LotteryType lotteryType) {
		if (!lastRaffles.isEmpty()) {
			Historic historic = lastRaffles.get(0);
			List<Integer> lastDozens = DozenMapper.toList(historic);
			int dozensMatch = dozens.stream().filter(el -> lastDozens.stream().anyMatch(el::equals)).collect(toList())
					.size();
			if (dozensMatch < this.minMatch)
				rules.add(
						BetRule.builder().ruleType(RuleType.LAST_RAFFLE_LOW).value(dozensMatch)
						.historicValue(this.minMatch).build());
			if (dozensMatch > this.maxMatch)
				rules.add(
						BetRule.builder().ruleType(RuleType.LAST_RAFFLE_HIGH).value(dozensMatch)
						.historicValue(this.maxMatch).build());
		}
	}

}
