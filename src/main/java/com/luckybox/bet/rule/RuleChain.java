package com.luckybox.bet.rule;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;

@Component
public interface RuleChain {

	void setNextChain(RuleChain nextChain);
	
	void checkRule(Bet bet, List<BetRule> rules);
}
