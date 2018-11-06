package com.luckybox.bet.rule;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class PrimeRule implements RuleChain {
	protected static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
			67, 71, 73, 79, 83, 89, 97);
	
	private RuleChain chain;

	private Integer minMatch;

	private Integer maxMatch;

	public PrimeRule() {
	}

	public PrimeRule(Integer minMatch, Integer maxMatch) {
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

		int dozensMatch = dozens.stream().filter(el -> PRIME.stream().anyMatch(el::equals)).collect(toList()).size();
		if (dozensMatch < this.minMatch)
			rules.add(BetRule.builder().ruleType(RuleType.PRIME_LOW).value(dozensMatch).historicValue(this.minMatch)
					.build());
		if (dozensMatch > this.maxMatch)
			rules.add(BetRule.builder().ruleType(RuleType.PRIME_HIGH).value(dozensMatch).historicValue(this.maxMatch)
					.build());
		this.chain.checkRule(bet, rules);
	}
}
