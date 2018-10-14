package com.luckybox.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Bet;
import com.luckybox.domain.LotteryType;

public interface BetRepository extends JpaRepository<Bet, Long>{

	public List<Bet> findAllByAlreadyChecked(Boolean alreadyChecked);
	
	Page<Bet> findAllByType(LotteryType type, Pageable pageable);
	
}
