package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.service.CombinationService;
import com.luckybox.service.DozenInfoService;
import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricImporterService;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Component
public class CombinationScheduler {

	@Inject
	private CombinationService combinationService;

	@Inject
	private DozenInfoService dozenInfoService;

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;
	
	@Inject
	private HistoricImporterService historicService;
	

	//@Scheduled(fixedRate=360000)
	public void schedules() throws IOException, ZipException{
		importHistoric();
		checkHistoricAlreadyDrawn();
		checkCombinationAlreadyDrawn();
		updateAlreadyDrawn();
		fillDatasetFields();
	}
	
	private void checkCombinationAlreadyDrawn(){
		log.info("Checking combinations drawn");
		combinationService.checkCombinationDrawn();
		log.info("Checking Combinations drawn finished...");
	}
	
	private void updateAlreadyDrawn() {
		log.info("Generate number info...");
		dozenInfoService.generateDozenInfo();
		log.info("Finished generate number info...");
	}
	
	private void fillDatasetFields() throws IOException, ZipException {
		log.info("Filling dataset fields...");
		historicDatasetFiller.fillDataSet();
		log.info("Finished dataset fields...");
	}
	
	private void checkHistoricAlreadyDrawn() throws IOException, ZipException {
		log.info("Checking historic already drawn...");
		historicDatasetFiller.fillAlreadyDrawnField();
		log.info("Finished check historic already drawn...");
	}
	
	private void importHistoric() throws IOException, ZipException {
		log.info("Importing historic concurses...");
		historicService.importConcurses();
		log.info("Finished historic concurses...");
	}
	
}
