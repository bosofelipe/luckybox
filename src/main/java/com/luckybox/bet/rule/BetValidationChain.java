package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.dto.DozenDTO;

import jersey.repackaged.com.google.common.collect.Lists;
@Component
public class BetValidationChain {

	public List<RuleType> validationChain(DozenDTO dozenDTO) {
		
		List<RuleType> rules = Lists.newArrayList();
		
		getRules().checkRule(toList(dozenDTO), rules);
		
		return rules;
	}

	private RuleChain getRules() {
		RuleChain rule = new PrimeRule();
		RuleChain pair = new PairRule();
		RuleChain firstLine = new FirstLineRule();

		rule.setNextChain(pair);
		pair.setNextChain(firstLine);
		return rule;
	}

}
