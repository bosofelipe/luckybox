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
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules) {
		int dozensMatch = dozens.stream().filter(el -> PRIME.stream().anyMatch(el::equals)).collect(toList()).size();
		if (dozensMatch < 4)
			rules.add(buildRule(dozensMatch, RuleType.PRIME_LOW));
		if(dozensMatch > 6 )
			rules.add(buildRule(dozensMatch, RuleType.PRIME_HIGH));
		this.chain.checkRule(dozens, rules);
	}
}
