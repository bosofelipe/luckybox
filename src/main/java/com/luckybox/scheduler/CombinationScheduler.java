package com.luckybox.scheduler;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.service.CombinationService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CombinationScheduler {

	@Inject
	private CombinationService combinationService;

	public void generateCombinations() throws InterruptedException {
		log.info("Start combination scheduler");
		combinationService.generateCombination(25, 15);
	}
	
}
