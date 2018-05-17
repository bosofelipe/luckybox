package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
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
		
		if(dozenDTO.getType() == LotteryType.LOTOFACIL)
			getLotofacilRules().checkRule(toList(dozenDTO), rules, dozenDTO.getType());
		if(dozenDTO.getType() == LotteryType.LOTOMANIA)
			getLotomaniaRules().checkRule(toList(dozenDTO), rules, dozenDTO.getType());
		return rules;
	}

	private RuleChain getLotofacilRules() {
		RuleChain rule = new PrimeRule(4,6);
		RuleChain pair = new PairRule(6,8);
		RuleChain columnLine = new ColumnLineRule();
		RuleChain sum = new SumRule(141,247);
		RuleChain dozenInfo = new DozenInfoRule(dozenInfoRepo);
		RuleChain fibonacci = new FibonacciRule(3,6);
		RuleChain alreadyDrawn = new AlreadyDrawnRule(historicService);
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl, 7 ,10);

		rule.setNextChain(pair);
		pair.setNextChain(sum);
		sum.setNextChain(lastRaffle);
		lastRaffle.setNextChain(dozenInfo);
		dozenInfo.setNextChain(columnLine);
		columnLine.setNextChain(alreadyDrawn);
		alreadyDrawn.setNextChain(fibonacci);
		return rule;
	}
	
	private RuleChain getLotomaniaRules() {
		RuleChain rule = new PrimeRule(4,6);
		RuleChain pair = new PairRule(6,8);
		RuleChain columnLine = new ColumnLineRule();
		RuleChain sum = new SumRule(141,247);
		RuleChain dozenInfo = new DozenInfoRule(dozenInfoRepo);
		RuleChain fibonacci = new FibonacciRule(3,6);
		RuleChain alreadyDrawn = new AlreadyDrawnRule(historicService);
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl, 7 ,10);

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
