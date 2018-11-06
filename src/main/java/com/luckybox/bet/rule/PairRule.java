package com.luckybox.bet.rule;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class PairRule implements RuleChain {
	private static final List<Integer> PAIR_NUMBERS = asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24);
	
	private RuleChain chain;

	private Integer minMatch;

	private Integer maxMatch;

	public PairRule() {
	}

	public PairRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);
		int dozensMatch = dozens.stream().filter(el -> PAIR_NUMBERS.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (dozensMatch < this.minMatch)
			rules.add(BetRule.builder().ruleType(RuleType.PAIR_LOW).value(dozensMatch).historicValue(this.minMatch)
					.build());
		if (dozensMatch > this.maxMatch)
			rules.add(BetRule.builder().ruleType(RuleType.PAIR_HIGH).value(dozensMatch).historicValue(this.maxMatch)
					.build());
		this.chain.checkRule(bet, rules);
	}

}
