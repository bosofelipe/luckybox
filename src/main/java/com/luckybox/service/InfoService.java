package com.luckybox.service;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.LotteryType;

import net.lingala.zip4j.exception.ZipException;

@Service
public class InfoService {

	@Inject
	private DozenInfoService dozenInfoService;

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;

	@Inject
	private HistoricImporterService historicService;

	public void importData() throws IOException, ZipException {
		importHistoric(LotteryType.LOTOFACIL.getName());
		importHistoric(LotteryType.QUINA.getName());
		importHistoric(LotteryType.LOTOMANIA.getName());
		
		checkAlreadyDrawn(LotteryType.LOTOFACIL);
		checkAlreadyDrawn(LotteryType.QUINA);
		checkAlreadyDrawn(LotteryType.LOTOMANIA);
		
		fillDatasetFields(LotteryType.LOTOFACIL);
		fillDatasetFields(LotteryType.QUINA);
		fillDatasetFields(LotteryType.LOTOMANIA);

		generateDozenInfo(LotteryType.LOTOFACIL.getName());
		generateDozenInfo(LotteryType.QUINA.getName());
		generateDozenInfo(LotteryType.LOTOMANIA.getName());
	}

	private void importHistoric(String type) throws IOException, ZipException {
		historicService.importConcurses(type);
	}

	private void generateDozenInfo(String type) {
		dozenInfoService.generateDozenInfo(type);
	}

	private void fillDatasetFields(LotteryType type) throws IOException, ZipException {
		historicDatasetFiller.fillDataSet(type);
	}

	// TODO ajustar para pegar pelo tipo
	private void checkAlreadyDrawn(LotteryType type) throws IOException, ZipException {
		historicDatasetFiller.fillAlreadyDrawnField(type);
	}
}
