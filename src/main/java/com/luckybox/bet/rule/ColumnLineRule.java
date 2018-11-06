package com.luckybox.bet.rule;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.mapper.DozenMapper;

@Component
public class ColumnLineRule implements RuleChain {
	private static final List<Integer> FIRST_COLUMN = asList(1, 6, 11, 16, 21);
	private static final List<Integer> SECOND_COLUMN = asList(2, 7, 12, 17, 22);
	private static final List<Integer> THIRD_COLUMN = asList(3, 8, 13, 18, 23);
	private static final List<Integer> FOURTH_COLUMN = asList(4, 9, 14, 19, 24);
	private static final List<Integer> FIVETH_COLUMN = asList(5, 10, 15, 20, 25);
	private static final List<Integer> FIRST_LINE = asList(1, 2, 3, 4, 5);
	private static final List<Integer> SECOND_LINE = asList(6, 7, 8, 9, 10);
	private static final List<Integer> THIRD_LINE = asList(11, 12, 13, 14, 15);
	private static final List<Integer> FOURTH_LINE = asList(16, 17, 18, 19, 20);
	private static final List<Integer> FIVETH_LINE = asList(21, 22, 23, 24, 25);
	
	@SuppressWarnings("unused")
	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);
		int firstLine = dozens.stream().filter(el -> FIRST_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (firstLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIRST_LINE).value(firstLine).build());
		
		int secondLine = dozens.stream().filter(el -> SECOND_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (secondLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.SECOND_LINE).value(secondLine).build());
		
		int thirdLine = dozens.stream().filter(el -> THIRD_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (thirdLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.THIRD_LINE).value(thirdLine).build());
		
		int fourthLine = dozens.stream().filter(el -> FOURTH_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fourthLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FOURTH_LINE).value(fourthLine).build());
		
		int fivethLine = dozens.stream().filter(el -> FIVETH_LINE.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fivethLine == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIVETH_LINE).value(fivethLine).build());

		
		int firstColumn = dozens.stream().filter(el -> FIRST_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (firstColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIRST_COLUMN).value(firstColumn).build());
		
		int secondColumn = dozens.stream().filter(el -> SECOND_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (secondColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.SECOND_COLUMN).value(secondColumn).build());
		
		int thirdColumn = dozens.stream().filter(el -> THIRD_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (thirdColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.THIRD_COLUMN).value(thirdColumn).build());
		
		int fourthColumn = dozens.stream().filter(el -> FOURTH_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fourthColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FOURTH_COLUMN).value(fourthColumn).build());
		
		int fivethColumn = dozens.stream().filter(el -> FIVETH_COLUMN.stream().anyMatch(el::equals))
				.collect(toList()).size();
		if (fivethColumn == 0)
			rules.add(BetRule.builder().bet(bet).ruleType(RuleType.FIVETH_COLUMN).value(fivethColumn).build());
	}

}