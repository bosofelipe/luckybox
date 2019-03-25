package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LineColumnDataset;

public interface LineColumnDatasetRepository extends JpaRepository<LineColumnDataset, Long>{
	
	public HistoricDataset findByConcurse(Long concurse);
}
