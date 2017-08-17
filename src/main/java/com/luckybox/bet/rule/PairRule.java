package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.PAIR_NUMBERS;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class PairRule implements RuleChain {

	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleType> rules) {
		int dozensMatch = dozens.stream().filter(el -> PAIR_NUMBERS.stream().anyMatch(el::equals)).collect(toList()).size();
		if (dozensMatch < 6 || dozensMatch > 8 )
			rules.add(RuleType.PAIR);
		this.chain.checkRule(dozens, rules);
	}

	
}
