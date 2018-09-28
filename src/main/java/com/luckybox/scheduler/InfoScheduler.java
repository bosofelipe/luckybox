package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.service.DozenInfoService;
import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricImporterService;

import net.lingala.zip4j.exception.ZipException;

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
		historicService.importConcurses(type);
	}

	private void generateDozenInfo(String type) {
		dozenInfoService.generateDozenInfo(type);
	}

	private void fillDatasetFields() throws IOException, ZipException {
		historicDatasetFiller.fillDataSet(LotteryType.LOTOFACIL);
		historicDatasetFiller.fillDataSet(LotteryType.QUINA);
		historicDatasetFiller.fillDataSet(LotteryType.LOTOMANIA);
	}

	// TODO ajustar para pegar pelo tipo
	private void checkAlreadyDrawn(LotteryType type) throws IOException, ZipException {
		historicDatasetFiller.fillAlreadyDrawnField(type);
	}
}
