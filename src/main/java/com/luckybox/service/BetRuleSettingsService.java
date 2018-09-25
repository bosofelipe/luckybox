package com.luckybox.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.LotteryType;
import com.luckybox.repository.BetRuleSettingsRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;

@Service
public class BetRuleSettingsService {
	
	@Inject
	private BetRuleSettingsRepository betRuleSettingsRepository;
	
	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepositoryImpl;

	public BetRuleSettings generateBetRuleSettings(String lotteryType) {
		LotteryType type = LotteryType.valueOf(lotteryType.toUpperCase());
		BetRuleSettings savedSettings = betRuleSettingsRepository.findByType(type);
		Integer maxDozensLastRaffle = historicDatasetRepositoryImpl.getMaxDozensLastRaffle(type);
		Integer maxFibonacci = historicDatasetRepositoryImpl.getMaxFibonacci(type);
		Integer maxPrime = historicDatasetRepositoryImpl.getMaxPrime(type);
		Integer maxFibonacciPrime = historicDatasetRepositoryImpl.getMaxFibonacciPrime(type);
		Integer maxQTDSequence = historicDatasetRepositoryImpl.getMaxSequence(type);
		Integer maxGreatherSequence = historicDatasetRepositoryImpl.getMaxGreatherSequence(type);
		
		Integer minSum = historicDatasetRepositoryImpl.getMinSum(type);
		Integer maxSum = historicDatasetRepositoryImpl.getMaxSum(type);
		
		Integer maxPair = historicDatasetRepositoryImpl.getMaxPair(type);
		if (savedSettings == null) {
			BetRuleSettings settings = BetRuleSettings.builder()
					.maxDozensLastRaffle(maxDozensLastRaffle)//
					.maxFibonacci(maxFibonacci)//
					.maxPrime(maxPrime)//
					.maxFibonacciPrime(maxFibonacciPrime)//
					.maxSequence(maxQTDSequence)//
					.maxPair(maxPair)//
					.minDozensLastRaffle(0)//
					.minFibonacci(0)//
					.minPrime(0)//
					.minSequence(0)
					.minFibonacciPrime(0)//
					.maxGreatherSequence(maxGreatherSequence)//
					.minGreatherSequence(0)//
					.minSum(minSum)//
					.maxSum(maxSum)//
					.type(type)
					.minPair(0)//
					.build();
			return betRuleSettingsRepository.save(settings);
		}
		savedSettings.setMaxDozensLastRaffle(maxDozensLastRaffle);
		savedSettings.setMaxFibonacci(maxFibonacciPrime);
		savedSettings.setMaxFibonacciPrime(maxFibonacciPrime);
		savedSettings.setMaxGreatherSequence(maxGreatherSequence);
		savedSettings.setMaxPair(maxPair);
		savedSettings.setMaxPrime(maxPrime);
		savedSettings.setMaxSequence(maxQTDSequence);
		return betRuleSettingsRepository.save(savedSettings);

	}

}
