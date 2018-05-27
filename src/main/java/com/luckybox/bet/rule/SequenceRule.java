package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.PAIR_NUMBERS;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.service.SequenceAnalyser;

@Component
public class SequenceRule implements RuleChain {

	private RuleChain chain;

	private Integer maxMatch;

	private Integer countMatch;

	public SequenceRule() {
	}

	public SequenceRule(Integer maxMatch, Integer countMatch) {
		this.maxMatch = maxMatch;
		this.countMatch = countMatch;
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
		int dozensMatch = dozens.stream().filter(el -> PAIR_NUMBERS.stream().anyMatch(el::equals)).collect(toList())
				.size();
		if (greater > this.maxMatch)
			rules.add(buildRule(dozensMatch, RuleType.GREATER_SEQUENCE, lotteryType));
		if (qtdSequences > this.countMatch)
			rules.add(buildRule(dozensMatch, RuleType.QTD_SEQUENCE, lotteryType));
		this.chain.checkRule(dozens, rules, lotteryType);
	}

}
