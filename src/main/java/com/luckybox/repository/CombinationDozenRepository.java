package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.CombinationDozen;
import com.luckybox.domain.LotteryType;

public interface CombinationDozenRepository extends JpaRepository<CombinationDozen, Long>{
	
	public CombinationDozen findByKeyAndType(String key, LotteryType type);

}