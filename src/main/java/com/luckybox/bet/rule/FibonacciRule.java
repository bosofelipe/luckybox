package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Component
public class FibonacciRule implements RuleChain {

	private Integer minMatch;

	private Integer maxMatch;

	public FibonacciRule() {}
	
	public FibonacciRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}
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
		DozenDTO dozenDTO = DozenMapper.toDTO(dozens, lotteryType);
		if (dozensMatch < this.minMatch)
			rules.add(buildRule(dozensMatch, RuleType.FIBONACCI_LOW, lotteryType, dozenDTO));
		if(dozensMatch > this.maxMatch)
			rules.add(buildRule(dozensMatch,RuleType.FIBONACCI_HIGH, lotteryType, dozenDTO));
	}
}