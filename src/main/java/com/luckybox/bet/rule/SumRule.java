package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SumRule implements RuleChain {

	private RuleChain chain;

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> numbers, List<RuleDTO> rules) {
		int sum = numbers.stream().mapToInt(Number::intValue).sum();
		if (sum < 141)
			rules.add(buildRule(sum, RuleType.SUM_LOW));
		if (sum > 247)
			rules.add(buildRule(sum, RuleType.SUM_HIGH));
		
		this.chain.checkRule(numbers, rules);
	}
}
