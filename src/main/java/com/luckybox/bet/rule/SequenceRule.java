package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
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

	public SequenceRule(Integer maxMatch, Integer countMatchMax, Integer minMatch, Integer countMatchMin) {
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
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);
		List<Integer> greaterSequence = new SequenceAnalyser().getGreaterSequence(dozens);
		Integer greater = greaterSequence.get(0);
		Integer qtdSequences = greaterSequence.get(1);

		if (greater > this.maxMatch)
			rules.add(BetRule.builder().ruleType(RuleType.GREATER_SEQUENCE_HIGH).value(greater)
					.historicValue(this.maxMatch).build());
		if (qtdSequences > this.countMatchMax)
			rules.add(BetRule.builder().ruleType(RuleType.QTD_SEQUENCE_HIGH).value(qtdSequences)
					.historicValue(this.countMatchMax).build());
		if (greater <= this.minMatch)
			rules.add(BetRule.builder().ruleType(RuleType.GREATER_SEQUENCE_LOW).value(greater)
					.historicValue(this.minMatch).build());
		if (qtdSequences < this.countMatchMin)
			rules.add(BetRule.builder().ruleType(RuleType.QTD_SEQUENCE_LOW).value(qtdSequences)
					.historicValue(this.countMatchMin).build());
		this.chain.checkRule(bet, rules);
	}

}
