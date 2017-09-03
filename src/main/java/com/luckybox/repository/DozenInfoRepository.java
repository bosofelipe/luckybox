package com.luckybox.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.luckybox.domain.DozenInfo;

@Component
public interface DozenInfoRepository extends CrudRepository<DozenInfo, Integer> {

	public List<DozenInfo> findAllByOrderByCountDrawNumberDesc();
	
	public List<DozenInfo> findAllByOrderByCountDrawNumberAsc();
	
}
