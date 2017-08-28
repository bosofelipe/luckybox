package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import com.luckybox.domain.DozenInfo;
import com.luckybox.repository.DozenInfoRepository;

public class DozenInfoRule implements RuleChain {

	private RuleChain chain;
	
	@Inject
	private DozenInfoRepository dozenInfoRepository;

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> numbers, List<RuleType> rules) {
		List<DozenInfo> dozenInfos = (List<DozenInfo>) dozenInfoRepository.findAll();
		if (dozenInfos.isEmpty())
			return;
		else {
			List<DozenInfo> withCurrent = dozenInfos.stream().filter(dozen -> dozen.getCurrentSequenceDrawn() > 3).collect(toList());
			int dozensMatch = withCurrent.stream().filter(el -> numbers.stream().anyMatch(el.getNumber()::equals)).collect(toList()).size();
			if(dozensMatch == 0)
				rules.add(RuleType.CURRENT_SEQUENCE);
		}
		this.chain.checkRule(numbers, rules);
	}

}
