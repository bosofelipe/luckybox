package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Bet;

public interface BetRepository extends JpaRepository<Bet, Long>{

}
