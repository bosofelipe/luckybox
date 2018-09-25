package com.luckybox.bet.rule;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepositoryImpl;

@Component
public class LastRaffleRule implements RuleChain {

	private RuleChain chain;
	
	private Integer minMatch;

	private Integer maxMatch;

	@Inject
	private HistoricRepositoryImpl historicRepositoryImpl;
	
	public LastRaffleRule() {
	}
	
	public LastRaffleRule(HistoricRepositoryImpl historicRepositoryImpl, Integer minMatch, Integer maxMatch) {
		this.minMatch = minMatch;
		this.maxMatch = maxMatch;
		this.historicRepositoryImpl = historicRepositoryImpl;
	}

	@Override
	public void setNextChain(RuleChain nextChain) {
		this.chain = nextChain;
	}

	@Override
	public void checkRule(List<Integer> dozens, List<RuleDTO> rules, LotteryType lotteryType) {
		List<Historic> lastRaffles = historicRepositoryImpl.getLastRaffles(1, lotteryType);
		if (!lastRaffles.isEmpty())
			checkLastDozens(dozens, rules, lastRaffles,lotteryType);
		chain.checkRule(dozens, rules, lotteryType);
	}

	private void checkLastDozens(List<Integer> dozens, List<RuleDTO> rules, List<Historic> lastRaffles, LotteryType lotteryType) {
		Historic historic = lastRaffles.get(0);
		List<Integer> lastDozens = DozenMapper.toList(historic);
		int dozensMatch = dozens.stream().filter(el -> lastDozens.stream().anyMatch(el::equals)).collect(toList())
				.size();
		DozenDTO dozenDTO = DozenMapper.toDTO(dozens, lotteryType);
		if (dozensMatch < this.minMatch)
			rules.add(buildRule(dozensMatch, RuleType.LAST_RAFFLE_LOW, lotteryType, dozenDTO));
		if(dozensMatch > this.maxMatch)
			rules.add(buildRule(dozensMatch, RuleType.LAST_RAFFLE_LOW, lotteryType, dozenDTO));
	}

}
