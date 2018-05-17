package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.PAIR_NUMBERS;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;

@Component
public class PairRule implements RuleChain {

	private RuleChain chain;

	private Integer minMatch;

	private Integer maxMatch;

	public PairRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules, LotteryType lotteryType) {
		int dozensMatch = dozens.stream().filter(el -> PAIR_NUMBERS.stream().anyMatch(el::equals)).collect(toList())
				.size();
		if (dozensMatch < this.minMatch)
			rules.add(buildRule(dozensMatch, RuleType.PAIR_LOW, lotteryType));
		if (dozensMatch > this.maxMatch)
			rules.add(buildRule(dozensMatch, RuleType.PAIR_HIGH, lotteryType));
		this.chain.checkRule(dozens, rules, lotteryType);
	}

}
