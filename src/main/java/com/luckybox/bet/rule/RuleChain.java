package com.luckybox.bet.rule;

import java.util.List;

public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(List<Integer> numbers, List<RuleType> types);
	
}
