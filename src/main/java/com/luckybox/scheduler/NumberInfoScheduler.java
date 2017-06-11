package com.luckybox.scheduler;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.NumberInfoService;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class NumberInfoScheduler {

	@Inject
	private NumberInfoService numberInfoService;

	@Scheduled(fixedRate = 1000)
	public void updateAlreadyDrawn() {
		log.info("Generate number info");
		numberInfoService.generateNumberInfo();
	}
	
}
