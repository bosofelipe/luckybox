package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.PRIME;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PrimeRule implements RuleChain {

	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleType> rules) {
		int dozensMatch = dozens.stream().filter(el -> PRIME.stream().anyMatch(el::equals)).collect(toList()).size();
		if (dozensMatch < 4 || dozensMatch > 6 )
			rules.add(RuleType.PRIME);
		this.chain.checkRule(dozens, rules);
	}
}
