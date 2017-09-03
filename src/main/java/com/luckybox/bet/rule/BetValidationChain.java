package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.HistoricRepositoryImpl;

import jersey.repackaged.com.google.common.collect.Lists;
@Component
public class BetValidationChain {

	private HistoricRepositoryImpl historicRepositoryImpl;
	
	public List<RuleType> validationChain(DozenDTO dozenDTO) {
		
		List<RuleType> rules = Lists.newArrayList();
		
		getRules().checkRule(toList(dozenDTO), rules);
		
		return rules;
	}

	private RuleChain getRules() {
		RuleChain rule = new PrimeRule();
		RuleChain pair = new PairRule();
		RuleChain columnLine = new ColumnLineRule();
		RuleChain sum = new SumRule();
		RuleChain dozenInfo = new DozenInfoRule();
		RuleChain fibonacci = new FibonacciRule();
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl);

		rule.setNextChain(pair);
		pair.setNextChain(sum);
		sum.setNextChain(lastRaffle);
		lastRaffle.setNextChain(dozenInfo);
		dozenInfo.setNextChain(columnLine);
		columnLine.setNextChain(fibonacci);
		return rule;
	}

}
