package com.luckybox.bet.rule;

import static com.luckybox.constants.ConstantsLoto.FIRST_COLUMN;
import static com.luckybox.constants.ConstantsLoto.FIRST_LINE;
import static com.luckybox.constants.ConstantsLoto.FIVETH_COLUMN;
import static com.luckybox.constants.ConstantsLoto.FIVETH_LINE;
import static com.luckybox.constants.ConstantsLoto.FOURTH_COLUMN;
import static com.luckybox.constants.ConstantsLoto.FOURTH_LINE;
import static com.luckybox.constants.ConstantsLoto.SECOND_COLUMN;
import static com.luckybox.constants.ConstantsLoto.SECOND_LINE;
import static com.luckybox.constants.ConstantsLoto.THIRD_COLUMN;
import static com.luckybox.constants.ConstantsLoto.THIRD_LINE;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ColumnLineRule implements RuleChain {

	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules) {
		int firstLine = dozens.stream().filter(el -> FIRST_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int secondLine = dozens.stream().filter(el -> SECOND_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int thirdLine = dozens.stream().filter(el -> THIRD_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int fourthLine = dozens.stream().filter(el -> FOURTH_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int fivethLine = dozens.stream().filter(el -> FIVETH_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		
		int firstColumn = dozens.stream().filter(el -> FIRST_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int secondColumn = dozens.stream().filter(el -> SECOND_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int thirdColumn = dozens.stream().filter(el -> THIRD_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int fourthColumn = dozens.stream().filter(el -> FOURTH_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int fivethColumn = dozens.stream().filter(el -> FIVETH_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();

		if (asList(firstLine, firstColumn, secondLine, secondColumn, thirdLine, thirdColumn, fourthLine,
				fourthColumn, fivethLine, fivethColumn).contains(0))
			rules.add(buildRule(0, RuleType.FIRST_LINE));
		
		this.chain.checkRule(dozens, rules);
	}

}