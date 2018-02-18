package com.luckybox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
	
	boolean exists(Long concurse);
	
	Historic findByConcurse(Long concurse);
	
	List<Historic> findAllByType(LotteryType type);
	
	List<Historic> findAllByAlreadyDrawnIsNullAndType(LotteryType type);
}
