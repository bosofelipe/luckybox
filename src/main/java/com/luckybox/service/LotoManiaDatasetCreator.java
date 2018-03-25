package com.luckybox.service;

import static com.luckybox.constants.ConstantsLoto.FIBONACCI_PRIME_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.FIBONACCI_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.PRIME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Service
public class LotoManiaDatasetCreator {
	private static final int QUANTITY_OF_DOZENS = 20;
	private List<Integer> dozens = new ArrayList<>();

	public HistoricDataset createHistoricDataSet(DozenDTO dozenDTO) {
		dozens = DozenMapper.toList(dozenDTO);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return HistoricDataset.builder().type(dozenDTO.getType()).dozenSum(sumDozens).average(sumDozens / QUANTITY_OF_DOZENS).pair(countPairs())
				.fibonacci(countFibonacciNumbers()).prime(countPrimeNumbers()).fibonacciPrime(countFibonacciPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.concurse(dozenDTO.getConcurse()).build();
	}
	
	private Integer sumDozens() {
		return dozens.stream().mapToInt(Number::intValue).sum();
	}

	public Integer sumNumbers(List<Integer> numbers) {
		return (int) numbers.stream().mapToInt(c -> c).sum();
	}

	private Integer countPairs() {
		return (int) dozens.stream().filter(n -> n % 2 == 0).count();
	}

	private Integer countFibonacciNumbers() {
		return (int) dozens.stream().filter(c -> FIBONACCI_SEQUENCE.contains(c)).mapToInt(c -> c).count();
	}

	private Integer countPrimeNumbers() {
		return (int) dozens.stream().filter(c -> PRIME.contains(c)).mapToInt(c -> c).count();
	}
	
	private Integer countFibonacciPrimeNumbers() {
		return (int) dozens.stream().filter(c -> FIBONACCI_PRIME_SEQUENCE.contains(c)).mapToInt(c -> c).count();
	}

	private List<Integer> getGreaterSequence() {
		int count = 0;
		List<Integer> diffs = new ArrayList<Integer>();
		for (int i = 0; i < dozens.size() - 1; i++)
			count = countSequence(count, diffs, i);
		if (count != 0)
			diffs.add(count + 1);
		Collections.sort(diffs);
		Collections.reverse(diffs);
		List<Integer> values = new ArrayList<Integer>();
		values.add(diffs.size() > 0 ? diffs.get(0) : 0);
		values.add(diffs.size());
		return values;
	}

	private int countSequence(int count, List<Integer> diffs, int i) {
		int value = dozens.get(i + 1) - dozens.get(i);
		if (value == 1)
			count++;
		else {
			if (count != 0)
				diffs.add(count + 1);
			count = 0;
		}
		return count;
	}
}
