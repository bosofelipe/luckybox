package com.luckybox.scheduler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.luckybox.domain.LotteryType;
import com.luckybox.service.BetRuleSettingsService;
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
	
	@Inject
	private BetRuleSettingsService betRuleSettingsService;

	@org.springframework.scheduling.annotation.Scheduled(cron = "0 0 */8 ? * *")
	public void schedules() throws IOException, ZipException {
		importHistoric();
		generateRules();
		checkAlreadyDrawn();
		fillDatasetFields();
		generateDozenInfo();
	}

	private void importHistoric() throws IOException, ZipException {
		importHistoric(LotteryType.LOTOFACIL.getName());
		importHistoric(LotteryType.QUINA.getName());
		importHistoric(LotteryType.LOTOMANIA.getName());
	}
	
	private void generateDozenInfo() {
		generateDozenInfo(LotteryType.LOTOFACIL.getName());
		generateDozenInfo(LotteryType.QUINA.getName());
		generateDozenInfo(LotteryType.LOTOMANIA.getName());
	}

	private void checkAlreadyDrawn() throws IOException, ZipException {
		checkAlreadyDrawn(LotteryType.LOTOFACIL);
		checkAlreadyDrawn(LotteryType.QUINA);
		checkAlreadyDrawn(LotteryType.LOTOMANIA);
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
	
	private void generateRules() {
		betRuleSettingsService.generateBetRuleSettings(LotteryType.LOTOFACIL.getName());
		betRuleSettingsService.generateBetRuleSettings(LotteryType.QUINA.getName());
		betRuleSettingsService.generateBetRuleSettings(LotteryType.LOTOMANIA.getName());
	}

	// TODO ajustar para pegar pelo tipo
	private void checkAlreadyDrawn(LotteryType type) throws IOException, ZipException {
		historicDatasetFiller.fillAlreadyDrawnField(type);
	}
}
