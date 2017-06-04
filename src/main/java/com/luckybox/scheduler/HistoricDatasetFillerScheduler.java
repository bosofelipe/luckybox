package com.luckybox.scheduler;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;

import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricService;

import net.lingala.zip4j.exception.ZipException;

public class HistoricDatasetFillerScheduler {

	@Inject
	private HistoricDatasetFiller historicDatasetFiller;
	
	@Inject
	private HistoricService historicService;
	
	private HistoricDatasetRepository historicDatasetRepo;

	@Scheduled(cron = "0/30 * * * * ?")
	public void fillDatasetFields() throws IOException, ZipException {
		List<Historic> allServices = historicService.findAll();
		allServices.stream().forEach(c-> fillFields(c));
	}

	private void fillFields(Historic c) {
		Long concurse = c.getConcurse();
		Integer variationSum = historicDatasetFiller.calculateVariationSum(concurse);
		Integer dozensLastConcurse = historicDatasetFiller.dozensLastConcurse(concurse);
		HistoricDataset dataset = historicDatasetRepo.findByConcurse(concurse);
		dataset.setDozensLastRaffle(dozensLastConcurse);
		dataset.setVariationSum(variationSum);
		historicDatasetRepo.save(dataset);
	}
	
}
