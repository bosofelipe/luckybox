package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Bet;

public interface BetRepository extends JpaRepository<Bet, Long>{

	public List<Bet> findAllByAlreadyChecked(Boolean alreadyChecked);
	
}
