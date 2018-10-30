package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.domain.DozenInfo;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.DozenInfoRepository;

@Component
public class DozenInfoRule implements RuleChain {

	private RuleChain chain;
	
	@Inject
	private DozenInfoRepository dozenInfoRepository;
	
	public DozenInfoRule(){
	}
	
	public DozenInfoRule(DozenInfoRepository dozenInfoRepository) {
		this.dozenInfoRepository = dozenInfoRepository;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(Bet bet, List<BetRule> rules) {
		List<DozenInfo> dozenInfos = (List<DozenInfo>) dozenInfoRepository.findAll();
		if (dozenInfos == null) {
			this.chain.checkRule(bet, rules);
			return;
		}
		else {
			List<Integer> numbers = DozenMapper.toList(bet);
			List<DozenInfo> withCurrent = dozenInfos.stream().filter(dozen -> dozen.getCurrentSequenceDrawn() > 3).collect(toList());
			int dozensMatch = withCurrent.stream().filter(el -> numbers.stream().anyMatch(el.getNumber()::equals)).collect(toList()).size();
			if(dozensMatch == 0){
				rules.add(
						BetRule.builder().bet(bet).ruleType(RuleType.CURRENT_SEQUENCE).value(dozensMatch).build());
			}
		}
		this.chain.checkRule(bet, rules);
	}

}
