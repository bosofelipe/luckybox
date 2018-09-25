package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.BetRuleSettingsRepository;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

import jersey.repackaged.com.google.common.collect.Lists;

@Component
public class BetValidationChain {

	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	@Inject
	private DozenInfoRepository dozenInfoRepo;
	
	@Inject
	private BetRuleSettingsRepository betRuleSettingsRepository;
	
	public List<RuleDTO> validationChain(List<DozenDTO> dozens) {
		
		List<RuleDTO> rules = Lists.newArrayList();
		
		dozens.stream().forEach(e -> getRules(e.getType()).checkRule(toList(e), rules, e.getType()));
		
		return rules;
	}

	private RuleChain getRules(LotteryType type) {
		BetRuleSettings settings = betRuleSettingsRepository.findByType(type);
		RuleChain rule = new PrimeRule(settings.getMinPrime(),settings.getMaxPrime());
		RuleChain pair = new PairRule(settings.getMinPair(),settings.getMaxPair());
		RuleChain columnLine = new ColumnLineRule();
		RuleChain sum = new SumRule(settings.getMinSum(),settings.getMaxSum());
		RuleChain dozenInfo = new DozenInfoRule(dozenInfoRepo);
		RuleChain fibonacci = new FibonacciRule(settings.getMinFibonacci(),settings.getMaxFibonacci());
		RuleChain alreadyDrawn = new AlreadyDrawnRule(historicRepositoryImpl);
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl, settings.getMinDozensLastRaffle() ,settings.getMaxDozensLastRaffle());

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
