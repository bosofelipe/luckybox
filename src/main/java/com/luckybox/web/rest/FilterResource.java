package com.luckybox.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.filter.ObjectFilter;
import com.luckybox.service.HistoricDatasetFilterService;

@RestController
@RequestMapping("/filter")
public class FilterResource {

	
	@Inject
	private HistoricDatasetFilterService historicDatasetFilterService;

	@GetMapping(path = "/sum", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<ObjectFilter> filterSum() {
		return historicDatasetFilterService.filterSum();
	} 
}
