package com.luckybox.scheduler;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.importer.CombinationService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CombinationScheduler {

	@Inject
	private CombinationService combinationService;

	//@Scheduled(cron = "0 0/1 * * * ?")
	@Scheduled(fixedRate=3600000)
	public void generateCombinations() throws InterruptedException {
		log.info("Start combination scheduler");
		combinationService.generateCombination(25, 15);
	}
	
}
