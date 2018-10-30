package com.luckybox.bet.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.domain.Historic;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class AlreadyDrawnRule implements RuleChain {

	private RuleChain chain;
	
	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	public AlreadyDrawnRule(){
	}
	
	public AlreadyDrawnRule(HistoricRepositoryImpl historicRepository) {
		this.historicRepositoryImpl = historicRepository;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		
		List<Historic> historic = historicRepositoryImpl.findHistoricByDozens(DozenMapper.toDTO(bet));
		boolean isAlreadyDown = historic.isEmpty() ? false : true;
		
		if(isAlreadyDown)
			rules.add(
					BetRule.builder().bet(bet)
					.ruleType(RuleType.ALREADY_DRAWN)
							.build());
		this.chain.checkRule(bet, rules);
	}

}
