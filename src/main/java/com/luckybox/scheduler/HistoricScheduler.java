package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricImporterService;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Service
public class HistoricScheduler {

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;
	
	@Inject
	private HistoricImporterService historicService;
	
	@Scheduled(fixedRate=360000)
	public void fillDatasetFields() throws IOException, ZipException {
		log.info("Filling dataset fields...");
		historicDatasetFiller.fillDataSet();
		log.info("Finished dataset fields...");
	}
	
	@Scheduled(fixedRate=360000)
	public void checkHistoricAlreadyDrawn() throws IOException, ZipException {
		log.info("Checking historic already drawn...");
		historicDatasetFiller.fillAlreadyDrawnField();
		log.info("Finished check historic already drawn...");
	}
	
	@Scheduled(fixedRate=360000)
	public void importHistoric() throws IOException, ZipException {
		log.info("Importing historic concurses...");
		historicService.importConcurses();
		log.info("Finished historic concurses...");
	}
	
}
