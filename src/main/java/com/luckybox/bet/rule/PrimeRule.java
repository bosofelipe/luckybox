package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.PRIME;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Component
public class PrimeRule implements RuleChain {

	private RuleChain chain;
	
	private Integer minMatch;
	
	private Integer maxMatch;
	
	public PrimeRule() {}
	
	public PrimeRule(Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
	}
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules, LotteryType lotteryType) {
		int dozensMatch = dozens.stream().filter(el -> PRIME.stream().anyMatch(el::equals)).collect(toList()).size();
		DozenDTO dozenDTO = DozenMapper.toDTO(dozens, lotteryType);
		if (dozensMatch < this.minMatch)
			rules.add(buildRule(dozensMatch, RuleType.PRIME_LOW, lotteryType, dozenDTO));
		if(dozensMatch > this.maxMatch )
			rules.add(buildRule(dozensMatch, RuleType.PRIME_HIGH, lotteryType, dozenDTO));
		this.chain.checkRule(dozens, rules, lotteryType);
	}
}
