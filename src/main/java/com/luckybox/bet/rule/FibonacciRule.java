package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.LotteryType;

@Component
public class FibonacciRule implements RuleChain {

	@SuppressWarnings("unused")
	private RuleChain chain;

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules, LotteryType lotteryType) {
		int dozensMatch = dozens.stream().filter(el -> ConstantsLoto.FIBONACCI_SEQUENCE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (dozensMatch < 3)
			rules.add(buildRule(dozensMatch, RuleType.FIBONACCI_LOW, lotteryType));
		if(dozensMatch > 6)
			rules.add(buildRule(dozensMatch,RuleType.FIBONACCI_HIGH, lotteryType));
	}
}