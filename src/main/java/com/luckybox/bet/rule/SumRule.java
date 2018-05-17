package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;

@Component
public class SumRule implements RuleChain {

	private RuleChain chain;
	
	private Integer minMatch;

	private Integer maxMatch;

	public SumRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> numbers, List<RuleDTO> rules, LotteryType lotteryType) {
		int sum = numbers.stream().mapToInt(Number::intValue).sum();
		if (sum < this.minMatch)
			rules.add(buildRule(sum, RuleType.SUM_LOW, lotteryType));
		if (sum > this.maxMatch)
			rules.add(buildRule(sum, RuleType.SUM_HIGH, lotteryType));
		
		this.chain.checkRule(numbers, rules, lotteryType);
	}
}
