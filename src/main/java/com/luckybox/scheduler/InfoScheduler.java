package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.service.ImporterDataService;

import net.lingala.zip4j.exception.ZipException;

@Component
public class InfoScheduler {
	
	@Inject
	private ImporterDataService importerDataService;

	@org.springframework.scheduling.annotation.Scheduled(cron = "0 0 */8 ? * *")
	public void schedules() throws IOException, ZipException {
		importerDataService.importHistoric();
		importerDataService.checkAlreadyDrawn();
		importerDataService.fillDatasetFields();
		importerDataService.generateDozenInfo();
		importerDataService.generateRules();
	}
}
