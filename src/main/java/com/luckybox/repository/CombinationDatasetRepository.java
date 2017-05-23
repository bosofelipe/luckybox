package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.CombinationDataset;

public interface CombinationDatasetRepository extends JpaRepository<CombinationDataset, Long>{
	
}
