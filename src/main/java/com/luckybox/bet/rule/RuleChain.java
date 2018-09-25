package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;

@Component
public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(List<Integer> numbers, List<RuleDTO> rules, LotteryType lotteryType);
	
	default RuleDTO buildRule(int dozensMatch, RuleType type, LotteryType lotteryType, DozenDTO dozens) {
		return RuleDTO.builder()//
				.type(type)//
				.dozens(dozens)
				.lotterType(lotteryType)//
				.value(dozensMatch)//
				.build();
	}
	
}
