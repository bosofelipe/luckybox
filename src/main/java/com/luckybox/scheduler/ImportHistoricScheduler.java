package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.importer.HistoricImporterService;

import net.lingala.zip4j.exception.ZipException;

@Component
public class ImportHistoricScheduler {

	@Inject
	private HistoricImporterService historicService;

	@Scheduled(cron = "0/30 * * * * ?")
	public void updateAlreadyDrawn() throws IOException, ZipException {
		historicService.importConcurses();
	}
	
}
