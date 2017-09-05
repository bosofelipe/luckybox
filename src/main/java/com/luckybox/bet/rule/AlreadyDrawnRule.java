package com.luckybox.bet.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.mapper.DozenMapper;
import com.luckybox.service.HistoricService;

@Component
public class AlreadyDrawnRule implements RuleChain {

	private RuleChain chain;
	
	@Inject
	private HistoricService historicService;
	
	public AlreadyDrawnRule(){
	}
	
	public AlreadyDrawnRule(HistoricService historicService) {
		this.historicService = historicService;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> numbers, List<RuleType> rules) {
		if(!historicService.findHistoricWithDozensNEConcurse(DozenMapper.toDTO(numbers)).isEmpty())
			rules.add(RuleType.ALREADY_DRAWN);
		this.chain.checkRule(numbers, rules);
	}

}
