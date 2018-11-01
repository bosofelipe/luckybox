package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class ColumnLineRule implements RuleChain {

	@Override
	public void setNextChain(RuleChain chain) {
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);
		int firstLine = dozens.stream().filter(el -> ConstantsLoto.FIRST_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (firstLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIRST_LINE).value(firstLine).build());
		
		int secondLine = dozens.stream().filter(el -> ConstantsLoto.SECOND_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (secondLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.SECOND_LINE).value(secondLine).build());
		
		int thirdLine = dozens.stream().filter(el -> ConstantsLoto.THIRD_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (thirdLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.THIRD_LINE).value(thirdLine).build());
		
		int fourthLine = dozens.stream().filter(el -> ConstantsLoto.FOURTH_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fourthLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FOURTH_LINE).value(fourthLine).build());
		
		int fivethLine = dozens.stream().filter(el -> ConstantsLoto.FIVETH_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fivethLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIVETH_LINE).value(fivethLine).build());

		
		int firstColumn = dozens.stream().filter(el -> ConstantsLoto.FIRST_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (firstColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIRST_COLUMN).value(firstColumn).build());
		
		int secondColumn = dozens.stream().filter(el -> ConstantsLoto.SECOND_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (secondColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.SECOND_COLUMN).value(secondColumn).build());
		
		int thirdColumn = dozens.stream().filter(el -> ConstantsLoto.THIRD_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (thirdColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.THIRD_COLUMN).value(thirdColumn).build());
		
		int fourthColumn = dozens.stream().filter(el -> ConstantsLoto.FOURTH_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fourthColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FOURTH_COLUMN).value(fourthColumn).build());
		
		int fivethColumn = dozens.stream().filter(el -> ConstantsLoto.FIVETH_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fivethColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIVETH_COLUMN).value(fivethColumn).build());
	}

}