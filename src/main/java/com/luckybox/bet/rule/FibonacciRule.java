package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.luckybox.constants.ConstantsLoto;

public class FibonacciRule implements RuleChain {

	@SuppressWarnings("unused")
	private RuleChain chain;

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleType> rules) {
		int dozensMatch = dozens.stream().filter(el -> ConstantsLoto.FIBONACCI_SEQUENCE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (dozensMatch < 3 || dozensMatch > 6)
			rules.add(RuleType.FIBONACCI);
	}
}