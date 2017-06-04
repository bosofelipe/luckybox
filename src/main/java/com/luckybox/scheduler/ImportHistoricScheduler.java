package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.HistoricImporterService;

import net.lingala.zip4j.exception.ZipException;

@Component
public class ImportHistoricScheduler {

	@Inject
	private HistoricImporterService historicService;

	@Scheduled(fixedRate=360000)
	public void updateAlreadyDrawn() throws IOException, ZipException {
		historicService.importConcurses();
	}
	
}
