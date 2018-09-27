package com.luckybox.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
	
	boolean exists(Long concurse);
	
	Historic findByConcurseAndType(Long concurse, LotteryType type);
	
	List<Historic> findAllByTypeOrderByConcurse(LotteryType type);
	
	Page<Historic> findAllByTypeOrderByConcurse(LotteryType type, Pageable pageable);
	
	List<Historic> findAllByAlreadyDrawnIsNullAndType(LotteryType type);
}
