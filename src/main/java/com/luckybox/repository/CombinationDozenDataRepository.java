package com.luckybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckybox.domain.CombinationDozenData;
import com.luckybox.domain.LotteryType;

public interface CombinationDozenDataRepository extends JpaRepository<CombinationDozenData, Long>{
	
	public CombinationDozenData findByKeyValuesAndType(Integer keyValues, LotteryType type);

}