package com.luckybox.service;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.LotteryType;

import net.lingala.zip4j.exception.ZipException;

@Service
public class ImporterDataService {

	@Inject
	private DozenInfoService dozenInfoService;

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;

	@Inject
	private HistoricImporterService historicService;
	
	@Inject
	private BetRuleSettingsService betRuleSettingsService;
	
	public void importHistoric() throws IOException, ZipException {
		importHistoric(LotteryType.LOTOFACIL.getName());
		importHistoric(LotteryType.QUINA.getName());
		importHistoric(LotteryType.LOTOMANIA.getName());
	}
	
	public void generateDozenInfo() {
		generateDozenInfo(LotteryType.LOTOFACIL.getName());
		generateDozenInfo(LotteryType.QUINA.getName());
		generateDozenInfo(LotteryType.LOTOMANIA.getName());
	}

	public void checkAlreadyDrawn() throws IOException, ZipException {
		checkAlreadyDrawn(LotteryType.LOTOFACIL);
		checkAlreadyDrawn(LotteryType.QUINA);
		checkAlreadyDrawn(LotteryType.LOTOMANIA);
	}
	
	public void fillDatasetFields() {
		historicDatasetFiller.fillDataSet(LotteryType.LOTOFACIL);
		historicDatasetFiller.fillDataSet(LotteryType.QUINA);
		historicDatasetFiller.fillDataSet(LotteryType.LOTOMANIA);
	}
	
	public void generateRules() {
		betRuleSettingsService.generateBetRuleSettings(LotteryType.LOTOFACIL.getName());
		betRuleSettingsService.generateBetRuleSettings(LotteryType.QUINA.getName());
		betRuleSettingsService.generateBetRuleSettings(LotteryType.LOTOMANIA.getName());
	}

	private void importHistoric(String type) throws IOException, ZipException {
		historicService.importConcurses(type);
	}

	private void generateDozenInfo(String type) {
		dozenInfoService.generateDozenInfo(type);
	}

	private void checkAlreadyDrawn(LotteryType type){
		historicDatasetFiller.fillAlreadyDrawnField(type);
	}
	
}
