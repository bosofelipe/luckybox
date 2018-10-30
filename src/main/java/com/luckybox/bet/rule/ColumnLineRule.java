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

	private RuleChain chain;
	
	@Override
	public void setNextChain(RuleChain chain) {
		this.chain = chain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<Integer> dozens = DozenMapper.toList(bet);
		int firstLine = dozens.stream().filter(el -> ConstantsLoto.FIRST_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int secondLine = dozens.stream().filter(el -> ConstantsLoto.SECOND_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int thirdLine = dozens.stream().filter(el -> ConstantsLoto.THIRD_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int fourthLine = dozens.stream().filter(el -> ConstantsLoto.FOURTH_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		int fivethLine = dozens.stream().filter(el -> ConstantsLoto.FIVETH_LINE.stream().anyMatch(el::equals)).collect(toList()).size();
		
		int firstColumn = dozens.stream().filter(el -> ConstantsLoto.FIRST_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int secondColumn = dozens.stream().filter(el -> ConstantsLoto.SECOND_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int thirdColumn = dozens.stream().filter(el -> ConstantsLoto.THIRD_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int fourthColumn = dozens.stream().filter(el -> ConstantsLoto.FOURTH_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();
		int fivethColumn = dozens.stream().filter(el -> ConstantsLoto.FIVETH_COLUMN.stream().anyMatch(el::equals)).collect(toList()).size();

		/*if (asList(firstLine, firstColumn, secondLine, secondColumn, thirdLine, thirdColumn, fourthLine,
				fourthColumn, fivethLine, fivethColumn).contains(0))
			rules.add(buildRule(0, RuleType.FIRST_LINE, lotteryType, dozenDTO));*/
	}

}