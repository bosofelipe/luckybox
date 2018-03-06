package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;

@Component
public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(List<Integer> numbers, List<RuleDTO> rules, LotteryType lotteryType);
	
	default RuleDTO buildRule(int dozensMatch, RuleType type, LotteryType lotteryType) {
		return RuleDTO.builder()//
				.type(type)//
				.lotterType(lotteryType)//
				.value(dozensMatch)//
				.build();
	}
	
}
