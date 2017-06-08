package com.luckybox.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.filter.ObjectFilter;
import com.luckybox.repository.HistoricDatasetRepository;

@Service
public class HistoricDatasetFilterService {

	@Inject
	private HistoricDatasetRepository historicDatasetRepository;
	
	public List<ObjectFilter> filterSum(){
		return historicDatasetRepository.filterSum();
	}
}
