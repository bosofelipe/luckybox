package com.luckybox.service;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.LotteryType;
import com.luckybox.repository.BetRuleSettingsRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;

@Service
public class BetRuleSettingsService {
	private static Logger log = LogManager.getLogger(BetRuleSettingsService.class);
	
	@Inject
	private BetRuleSettingsRepository betRuleSettingsRepository;
	
	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepositoryImpl;

	public BetRuleSettings generateBetRuleSettings(String lotteryType) {
		LotteryType type = LotteryType.valueOf(lotteryType.toUpperCase());
		BetRuleSettings savedSettings = betRuleSettingsRepository.findByType(type);
		
		Integer maxDozensLastRaffle = historicDatasetRepositoryImpl.getMaxDozensLastRaffle(type, true);
		Integer maxFibonacci = historicDatasetRepositoryImpl.getMaxFibonacci(type, true);
		Integer maxPrime = historicDatasetRepositoryImpl.getMaxPrime(type, true);
		Integer maxQTDSequence = historicDatasetRepositoryImpl.getMaxSequence(type, true);
		Integer maxGreatherSequence = historicDatasetRepositoryImpl.getMaxGreatherSequence(type, true);
		Integer maxSum = historicDatasetRepositoryImpl.getMaxSum(type, true);
		Integer maxPair = historicDatasetRepositoryImpl.getMaxPair(type, true);
		
		
		Integer minDozensLastRaffle = historicDatasetRepositoryImpl.getMaxDozensLastRaffle(type, false);
		Integer minFibonacci = historicDatasetRepositoryImpl.getMaxFibonacci(type, false);
		Integer minPrime = historicDatasetRepositoryImpl.getMaxPrime(type, false);
		Integer minQTDSequence = historicDatasetRepositoryImpl.getMaxSequence(type, false);
		Integer minGreatherSequence = historicDatasetRepositoryImpl.getMaxGreatherSequence(type, false);
		Integer minSum = historicDatasetRepositoryImpl.getMaxSum(type, false);
		Integer minPair = historicDatasetRepositoryImpl.getMaxPair(type, false);

		if (savedSettings == null) {
			BetRuleSettings settings = BetRuleSettings.builder()
					.maxDozensLastRaffle(maxDozensLastRaffle)//
					.maxFibonacci(maxFibonacci)//
					.maxPrime(maxPrime)//
					.maxSequence(maxQTDSequence)//
					.maxPair(maxPair)//
					.maxSum(maxSum)//
					.maxGreatherSequence(maxGreatherSequence)//
					.minPair(minPair)//
					.minDozensLastRaffle(minDozensLastRaffle)//
					.minFibonacci(minFibonacci)//
					.minPrime(minPrime)//
					.minSequence(minQTDSequence)
					.minGreatherSequence(minGreatherSequence)//
					.minSum(minSum)//
					.type(type)
					.build();
		    log.info(String.format("Saved bet rule settings, type: %s", type.getName()));
			return betRuleSettingsRepository.save(settings);
		}
		savedSettings.setMaxDozensLastRaffle(maxDozensLastRaffle);
		savedSettings.setMaxGreatherSequence(maxGreatherSequence);
		savedSettings.setMaxPair(maxPair);
		savedSettings.setMaxPrime(maxPrime);
		savedSettings.setMaxSequence(maxQTDSequence);
		
		savedSettings.setMinDozensLastRaffle(minDozensLastRaffle);
		savedSettings.setMinGreatherSequence(minGreatherSequence);
		savedSettings.setMinPair(minPair);
		savedSettings.setMinPrime(minPrime);
		savedSettings.setMinSequence(minQTDSequence);
		
		log.info(String.format("Updated bet rule settings, type: %s", type.getName()));
		return betRuleSettingsRepository.save(savedSettings);
	}
}
