package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.HistoricDataset;

public interface HistoricDatasetRepository extends JpaRepository<HistoricDataset, Long>{
	
	HistoricDataset findByConcurse(Long concurse);

}
