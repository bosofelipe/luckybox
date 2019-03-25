package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.QuadrantDataset;

public interface QuadrantDatasetRepository extends JpaRepository<QuadrantDataset, Long>{
	
	public QuadrantDataset findByConcurse(Long concurse);
}
