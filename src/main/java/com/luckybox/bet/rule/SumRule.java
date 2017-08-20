package com.luckybox.bet.rule;

import java.util.List;

public class SumRule implements RuleChain{


	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> numbers, List<RuleType> types) {
		if(numbers.stream().mapToInt(Number::intValue).sum()< 141 && 
				numbers.stream().mapToInt(Number::intValue).sum()>247)
			types.add(RuleType.SUM);
		this.chain.checkRule(numbers, types);
	}

}
