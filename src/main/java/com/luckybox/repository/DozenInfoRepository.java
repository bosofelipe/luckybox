package com.luckybox.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luckybox.domain.DozenInfo;

public interface DozenInfoRepository extends CrudRepository<DozenInfo, Integer> {

	public List<DozenInfo> findAllByOrderByCountDrawNumberDesc();
	
	public List<DozenInfo> findAllByOrderByCountDrawNumberAsc();
	
}
