package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.service.SequenceAnalyser;

@Component
public class SequenceRule implements RuleChain {

	private RuleChain chain;

	private Integer maxMatch;

	private Integer countMatchMax;
	
	private Integer minMatch;
	
	private Integer countMatchMin;

	public SequenceRule() {
	}

	public SequenceRule(Integer maxMatch, Integer countMatchMax,Integer minMatch, Integer countMatchMin) {
		this.maxMatch = maxMatch;
		this.minMatch = minMatch;
		this.countMatchMax = countMatchMax;
		this.countMatchMin = countMatchMin;
	}

	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules, LotteryType lotteryType) {
		List<Integer> greaterSequence = new SequenceAnalyser().getGreaterSequence(dozens);
		Integer greater = greaterSequence.get(0);
		Integer qtdSequences = greaterSequence.get(1);
		DozenDTO dozenDTO = DozenMapper.toDTO(dozens, lotteryType);
		
		if (greater > this.maxMatch)
			rules.add(buildRule(0, RuleType.GREATER_SEQUENCE_HIGH, lotteryType, dozenDTO));
		if (qtdSequences > this.countMatchMax)
			rules.add(buildRule(0, RuleType.QTD_SEQUENCE_HIGH, lotteryType, dozenDTO));
		if (greater < this.minMatch)
			rules.add(buildRule(0, RuleType.GREATER_SEQUENCE_LOW, lotteryType, dozenDTO));
		if (qtdSequences < this.countMatchMin)
			rules.add(buildRule(0, RuleType.QTD_SEQUENCE_LOW, lotteryType, dozenDTO));
		//this.chain.checkRule(dozens, rules, lotteryType);
	}

}
