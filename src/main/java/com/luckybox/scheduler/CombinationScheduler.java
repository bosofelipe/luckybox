package com.luckybox.scheduler;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
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
	
	@Scheduled(fixedRate=360000)
	public void isCombinationDrawn(){
		combinationService.checkCombinationDrawn();
	}
	
}
