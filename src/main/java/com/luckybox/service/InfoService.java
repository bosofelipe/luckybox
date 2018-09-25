package com.luckybox.service;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.LotteryType;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
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
		log.info(String.format("Importing historic concurses %s...", type));
		historicService.importConcurses(type);
		log.info(String.format("Finished historic concurses %s...", type));
	}

	private void generateDozenInfo(String type) {
		log.info(String.format("Generate dozen info %s ...", type));
		dozenInfoService.generateDozenInfo(type);
		log.info(String.format("Finished dozen info %s ...", type));
	}

	private void fillDatasetFields(LotteryType type) throws IOException, ZipException {
		log.info(String.format("Filling dataset %s fields...", type));
		historicDatasetFiller.fillDataSet(type);
		log.info(String.format("Finished dataset %s fields...", type));
	}

	// TODO ajustar para pegar pelo tipo
	private void checkAlreadyDrawn(LotteryType type) throws IOException, ZipException {
		log.info(String.format("Checking historic %s already drawn...", type.getName()));
		historicDatasetFiller.fillAlreadyDrawnField(type);
		log.info(String.format("Finished check historic %s already drawn...", type.getName()));
	}
}
