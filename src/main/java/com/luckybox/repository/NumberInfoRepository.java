package com.luckybox.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luckybox.domain.NumberInfo;

public interface NumberInfoRepository extends CrudRepository<NumberInfo, Integer> {

	public List<NumberInfo> findAllByOrderByCountDrawNumberDesc();
	
	public List<NumberInfo> findAllByOrderByCountDrawNumberAsc();
	
}
