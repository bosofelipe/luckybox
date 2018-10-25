package com.luckybox.bet.rule;

import static com.luckybox.mapper.DozenMapper.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.BetRuleSettingsRepository;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class BetValidationChain {

	private RuleChain chain;
	
	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	@Inject
	private DozenInfoRepository dozenInfoRepo;
	
	@Inject
	private BetRuleSettingsRepository betRuleSettingsRepository;
	
	public List<RuleDTO> validationChain(List<DozenDTO> dozens, LotteryType type) {
		
		List<RuleDTO> rules = Lists.newArrayList();
		
		getRules(type);
		
		dozens.stream().forEach(e -> loadRules(rules, e));
		
		return rules;
	}

	private void loadRules(List<RuleDTO> rules, DozenDTO e) {
		chain.checkRule(toList(e), rules, e.getType());
	}

	private void getRules(LotteryType type) {
		BetRuleSettings settings = betRuleSettingsRepository.findByType(type);
		RuleChain columnLine = new ColumnLineRule();
		
		
		this.chain = new PrimeRule(settings.getMinPrime(),settings.getMaxPrime());
		RuleChain pair = new PairRule(settings.getMinPair(),settings.getMaxPair());
		RuleChain sum = new SumRule(settings.getMinSum(),settings.getMaxSum());
		RuleChain sequence = new SequenceRule(settings.getMaxGreatherSequence(),settings.getMaxQuantitySequence(),settings.getMinGreatherSequence(),settings.getMinQuantitySequence());
		RuleChain dozenInfo = new DozenInfoRule(dozenInfoRepo);
		RuleChain fibonacci = new FibonacciRule(settings.getMinFibonacci(),settings.getMaxFibonacci());
		RuleChain alreadyDrawn = new AlreadyDrawnRule(historicRepositoryImpl);
		RuleChain lastRaffle = new LastRaffleRule(historicRepositoryImpl, settings.getMinDozensLastRaffle() ,settings.getMaxDozensLastRaffle());

		chain.setNextChain(pair);
		pair.setNextChain(sum);
		sum.setNextChain(lastRaffle);
		lastRaffle.setNextChain(dozenInfo);
		dozenInfo.setNextChain(sequence);
		sequence.setNextChain(alreadyDrawn);
		alreadyDrawn.setNextChain(fibonacci);
		fibonacci.setNextChain(columnLine);
	}
}
