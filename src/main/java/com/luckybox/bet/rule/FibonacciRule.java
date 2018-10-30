package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class FibonacciRule implements RuleChain {

	private Integer minMatch;

	private Integer maxMatch;

	public FibonacciRule() {
	}

	public FibonacciRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}

	private RuleChain chain;

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);

		int dozensMatch = dozens.stream().filter(el -> ConstantsLoto.FIBONACCI_SEQUENCE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (dozensMatch < this.minMatch)
			rules.add(BetRule.builder().ruleType(RuleType.FIBONACCI_LOW).value(dozensMatch).historicValue(this.minMatch)
					.build());
		if (dozensMatch > this.maxMatch)
			rules.add(BetRule.builder().ruleType(RuleType.FIBONACCI_HIGH).value(dozensMatch)
					.historicValue(this.maxMatch).build());
		this.chain.checkRule(bet, rules);
	}
}