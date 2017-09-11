package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(List<Integer> numbers, List<RuleDTO> rules);
	
	default RuleDTO buildRule(int dozensMatch, RuleType type) {
		return RuleDTO.builder()//
				.type(type)//
				.value(dozensMatch)//
				.build();
	}
	
}
