package com.luckybox.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Bet;
import com.luckybox.dto.BetDTO;
import com.luckybox.mapper.BetMapper;
import com.luckybox.repository.BetRepository;

@Service
public class BetService {

	@Inject
	private BetRepository betRepository;
	
	public Bet save(BetDTO betDTO) {
		Bet bet = BetMapper.toEntity(betDTO);
		return betRepository.save(bet);
	}
	
}
