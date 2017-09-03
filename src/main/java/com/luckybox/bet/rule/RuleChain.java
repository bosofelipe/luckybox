package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(List<Integer> numbers, List<RuleType> types);
	
}
