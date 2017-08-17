package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.bet.rule.BetValidationChain;
import com.luckybox.bet.rule.RuleType;
import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.BetRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class BetService {

	@Inject
	private BetRepository betRepository;

	@Inject
	private HistoricRepositoryImpl historicRepository;
	
	@Inject
	private BetValidationChain chainOfRules;

	public Bet save(DozenDTO dozenDTO) {
		Bet bet = DozenMapper.toBet(dozenDTO);
		return betRepository.save(bet);
	}

	public boolean isAlreadyDrawn(DozenDTO dozenDTO) {
		List<Historic> historic = historicRepository
				.findHistoricByDozens(dozenDTO);
		return historic.isEmpty() ? false : true;

	}

	public List<RuleType> checkRules(DozenDTO dozenDTO) {
		return chainOfRules.validationChain(dozenDTO);
	}

}
