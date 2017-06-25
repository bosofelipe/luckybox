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

	@Scheduled(fixedRate=360000)
	public void checkCombinationAlreadyDrawn(){
		log.info("Checking combinations drawn");
		combinationService.checkCombinationDrawn();
		log.info("Checking Combinations drawn finished...");
	}
	
}
