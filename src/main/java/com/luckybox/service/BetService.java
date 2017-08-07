package com.luckybox.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Bet;
import com.luckybox.dto.BetDTO;
import com.luckybox.dto.HistoricDTO;
import com.luckybox.mapper.BetMapper;
import com.luckybox.repository.BetRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

@Service
public class BetService {

	@Inject
	private BetRepository betRepository;

	@Inject
	private HistoricRepositoryImpl historicRepository;

	public Bet save(BetDTO betDTO) {
		Bet bet = BetMapper.toEntity(betDTO);
		return betRepository.save(bet);
	}

	public boolean isAlreadyDrawn(HistoricDTO historicDTO) {
		return historicRepository
				.findHistoricByDozens(historicDTO).isEmpty() ? false : true;

	}

}
