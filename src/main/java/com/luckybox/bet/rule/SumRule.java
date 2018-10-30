package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class SumRule implements RuleChain {

	private RuleChain chain;

	private Integer minMatch;

	private Integer maxMatch;

	public SumRule() {
	}

	public SumRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);

		int sum = dozens.stream().mapToInt(Number::intValue).sum();

		if (sum < this.minMatch)
			rules.add(BetRule.builder().ruleType(RuleType.SUM_LOW).value(sum).historicValue(this.minMatch).build());
		if (sum > this.maxMatch)
			rules.add(BetRule.builder().ruleType(RuleType.SUM_HIGH).value(sum).historicValue(this.maxMatch).build());

		this.chain.checkRule(bet, rules);
	}
}
