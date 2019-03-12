package com.luckybox.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.BetRule;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
	
	Historic findByConcurseAndType(Long concurse, LotteryType type);
	
	List<Historic> findAllByTypeOrderByConcurse(LotteryType type);
	
	List<Historic> findAllByType(LotteryType type);
	
	Page<Historic> findAllByType(LotteryType type, Pageable pageable);
	
	List<Historic> findAllByAlreadyDrawnIsNullAndType(LotteryType type);
}
