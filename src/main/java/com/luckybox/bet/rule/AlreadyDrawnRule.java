package com.luckybox.bet.rule;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
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
	public void checkRule(List<Integer> numbers, List<RuleDTO> rules, LotteryType lotteryType) {
		
		DozenDTO dozenDTO = DozenMapper.toDTO(numbers, lotteryType);
				
		List<Historic> historic = historicRepositoryImpl.findHistoricByDozens(dozenDTO);
		boolean isAlreadyDown = historic.isEmpty() ? false : true;
		
		if(isAlreadyDown)
			rules.add(
					RuleDTO.builder()//
							.dozens(dozenDTO)
						    .type(RuleType.ALREADY_DRAWN)//
							.build());
		this.chain.checkRule(numbers, rules, lotteryType);
	}

}
