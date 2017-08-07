package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.Historic;

public interface HistoricRepository extends JpaRepository<Historic, Long> {
	
	boolean exists(Long concurse);
	
	Historic findByConcurse(Long concurse);
}
