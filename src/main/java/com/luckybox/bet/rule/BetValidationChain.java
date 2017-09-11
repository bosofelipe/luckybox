package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;
import com.luckybox.service.HistoricService;

import jersey.repackaged.com.google.common.collect.Lists;

@Component
public class BetValidationChain {

	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	@Inject
	private DozenInfoRepository dozenInfoRepo;
	
	@Inject
	private HistoricService historicService;
	
	public List<RuleDTO> validationChain(DozenDTO dozenDTO) {
		
		List<RuleDTO> rules = Lists.newArrayList();
		
		getRules().checkRule(toList(dozenDTO), rules);
		
		return rules;
	}

	private RuleChain getRules() {
		RuleChain rule = new PrimeRule();
		RuleChain pair = new PairRule();
		RuleChain columnLine = new ColumnLineRule();
		RuleChain sum = new SumRule();
		RuleChain dozenInfo = new DozenInfoRule(dozenInfoRepo);
		RuleChain fibonacci = new FibonacciRule();
		RuleChain alreadyDrawn = new AlreadyDrawnRule(historicService);
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl);

		rule.setNextChain(pair);
		pair.setNextChain(sum);
		sum.setNextChain(lastRaffle);
		lastRaffle.setNextChain(dozenInfo);
		dozenInfo.setNextChain(columnLine);
		columnLine.setNextChain(alreadyDrawn);
		alreadyDrawn.setNextChain(fibonacci);
		return rule;
	}

}
