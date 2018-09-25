package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Component
public class SumRule implements RuleChain {

	private RuleChain chain;
	
	private Integer minMatch;

	private Integer maxMatch;

	public SumRule() {}
	
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
		
		DozenDTO dozenDTO = DozenMapper.toDTO(numbers, lotteryType);
		
		if (sum < this.minMatch)
			rules.add(buildRule(sum, RuleType.SUM_LOW, lotteryType, dozenDTO));
		if (sum > this.maxMatch)
			rules.add(buildRule(sum, RuleType.SUM_HIGH, lotteryType, dozenDTO));
		
		this.chain.checkRule(numbers, rules, lotteryType);
	}
}
