package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckybox.service.DozenInfoService;
import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricImporterService;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Component
public class InfoScheduler {

	@Inject
	private DozenInfoService dozenInfoService;

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;
	
	@Inject
	private HistoricImporterService historicService;

	@Scheduled(fixedDelay = 7200000)
	public void schedules() throws IOException, ZipException{
		importHistoric();
		checkAlreadyDrawn();
		generateDozenInfo();
		fillDatasetFields();
	}
	
	private void importHistoric() throws IOException, ZipException {
		log.info("Importing historic concurses...");
		historicService.importConcurses();
		log.info("Finished historic concurses...");
	}
	
	private void generateDozenInfo() {
		log.info("Generate number info...");
		dozenInfoService.generateDozenInfo();
		log.info("Finished generate number info...");
	}
	
	private void fillDatasetFields() throws IOException, ZipException {
		log.info("Filling dataset fields...");
		historicDatasetFiller.fillDataSet();
		log.info("Finished dataset fields...");
	}
	
	private void checkAlreadyDrawn() throws IOException, ZipException {
		log.info("Checking historic already drawn...");
		historicDatasetFiller.fillAlreadyDrawnField();
		log.info("Finished check historic already drawn...");
	}
}
