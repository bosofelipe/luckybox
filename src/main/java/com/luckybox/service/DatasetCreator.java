package com.luckybox.service;

import static com.luckybox.constants.ConstantsLoto.FIBONACCI_PRIME_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.FIBONACCI_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.PRIME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Service
public class DatasetCreator {
	private List<Integer> dozens = new ArrayList<>();

	public HistoricDataset createHistoricDataSet(DozenDTO dozenDTO, Integer quantityOfDozens) {
		dozens = DozenMapper.toList(dozenDTO);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return HistoricDataset.builder().type(dozenDTO.getType()).dozenSum(sumDozens)
				.average(sumDozens / quantityOfDozens).pair(countPairs()).fibonacci(countFibonacciNumbers())
				.prime(countPrimeNumbers()).fibonacciPrime(countFibonacciPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.concurse(dozenDTO.getConcurse()).firstColumn(countDozens(ConstantsLoto.FIRST_COLUMN))
				.secondColumn(countDozens(ConstantsLoto.SECOND_COLUMN))
				.thirdColumn(countDozens(ConstantsLoto.THIRD_COLUMN))
				.fourthColumn(countDozens(ConstantsLoto.FOURTH_COLUMN))
				.fivethColumn(countDozens(ConstantsLoto.FIVETH_COLUMN)).firstLine(countDozens(ConstantsLoto.FIRST_LINE))
				.secondLine(countDozens(ConstantsLoto.SECOND_LINE)).thirdLine(countDozens(ConstantsLoto.THIRD_LINE))
				.fourthLine(countDozens(ConstantsLoto.FOURTH_LINE)).fivethLine(countDozens(ConstantsLoto.FIVETH_LINE))
				.build();
	}

	private Integer countDozens(List<Integer> values) {
		return (int) dozens.stream().filter(contains(values)).count();
	}

	private Predicate<? super Integer> contains(List<Integer> values) {
		return c -> values.contains(c);
	}

	private Integer sumDozens() {
		return dozens.stream().mapToInt(Number::intValue).sum();
	}

	public Integer sumNumbers(List<Integer> numbers) {
		return numbers.stream().mapToInt(c -> c).sum();
	}

	private Integer countPairs() {
		return (int) dozens.stream().filter(n -> n % 2 == 0).count();
	}

	private Integer countFibonacciNumbers() {
		return (int) dozens.stream().filter(containsFibonacciSequenceNumber()).mapToInt(c -> c).count();
	}

	private Predicate<? super Integer> containsFibonacciSequenceNumber() {
		return c -> FIBONACCI_SEQUENCE.contains(c);
	}

	private Integer countPrimeNumbers() {
		return (int) dozens.stream().filter(containsPrimeNumber()).mapToInt(c -> c).count();
	}

	private Predicate<? super Integer> containsPrimeNumber() {
		return c -> PRIME.contains(c);
	}

	private Integer countFibonacciPrimeNumbers() {
		return (int) dozens.stream().filter(containsFibonacciPrimeNumber()).mapToInt(c -> c).count();
	}

	private Predicate<? super Integer> containsFibonacciPrimeNumber() {
		return c -> FIBONACCI_PRIME_SEQUENCE.contains(c);
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
		values.add(!diffs.isEmpty() ? diffs.get(0) : 0);
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
