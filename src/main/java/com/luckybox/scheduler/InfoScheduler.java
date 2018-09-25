package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
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

	//@Scheduled(cron = "0 0/1 * * * ?")
	public void schedules2() throws IOException, ZipException {
		checkAlreadyDrawn(LotteryType.LOTOFACIL);
		checkAlreadyDrawn(LotteryType.QUINA);
		checkAlreadyDrawn(LotteryType.LOTOMANIA);
		fillDatasetFields();

		generateDozenInfo(LotteryType.LOTOFACIL.getName());
		generateDozenInfo(LotteryType.QUINA.getName());
		generateDozenInfo(LotteryType.LOTOMANIA.getName());
	}

	//@Scheduled(cron = "0 0/30 * * * ?")
	public void schedules() throws IOException, ZipException {
		importHistoric(LotteryType.LOTOFACIL.getName());
		importHistoric(LotteryType.QUINA.getName());
		importHistoric(LotteryType.LOTOMANIA.getName());
	}

	private void importHistoric(String type) throws IOException, ZipException {
		log.info(String.format("Importing historic concurses %s...", type));
		historicService.importConcurses(type);
		log.info(String.format("Finished historic concurses %s...", type));
	}

	private void generateDozenInfo(String type) {
		log.info(String.format("Generate dozen info %s ...", type));
		dozenInfoService.generateDozenInfo(type);
		log.info(String.format("Finished dozen info %s ...", type));
	}

	private void fillDatasetFields() throws IOException, ZipException {
		log.info("Filling dataset lotofacil fields...");
		historicDatasetFiller.fillDataSet(LotteryType.LOTOFACIL);
		historicDatasetFiller.fillDataSet(LotteryType.QUINA);
		historicDatasetFiller.fillDataSet(LotteryType.LOTOMANIA);
		log.info("Finished dataset lotofacil fields...");
	}

	// TODO ajustar para pegar pelo tipo
	private void checkAlreadyDrawn(LotteryType type) throws IOException, ZipException {
		log.info(String.format("Checking historic %s already drawn...", type.getName()));
		historicDatasetFiller.fillAlreadyDrawnField(type);
		log.info(String.format("Finished check historic %s already drawn...", type.getName()));
	}
}
