package com.luckybox.scheduler;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.DozenInfoService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class DozenInfoScheduler {

	@Inject
	private DozenInfoService dozenInfoService;

	@Scheduled(fixedRate=360000)
	public void updateAlreadyDrawn() {
		log.info("Generate number info...");
		dozenInfoService.generateDozenInfo();
		log.info("Finished generate number info...");
	}
	
}
