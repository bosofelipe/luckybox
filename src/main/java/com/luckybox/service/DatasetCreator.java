package com.luckybox.service;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Service
public class DatasetCreator {
	private static final List<Integer> FIBONACCI_SEQUENCE = asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89);
	private static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
			61, 67, 71, 73, 79, 83, 89, 97);
	private static final List<Integer> FIBONACCI_PRIME_SEQUENCE = asList(2, 3, 5, 13, 89);
	private static final List<Integer> FIRST_COLUMN = asList(1, 6, 11, 16, 21);
	private static final List<Integer> SECOND_COLUMN = asList(2, 7, 12, 17, 22);
	private static final List<Integer> THIRD_COLUMN = asList(3, 8, 13, 18, 23);
	private static final List<Integer> FOURTH_COLUMN = asList(4, 9, 14, 19, 24);
	private static final List<Integer> FIVETH_COLUMN = asList(5, 10, 15, 20, 25);
	private static final List<Integer> FIRST_LINE = asList(1, 2, 3, 4, 5);
	private static final List<Integer> SECOND_LINE = asList(6, 7, 8, 9, 10);
	private static final List<Integer> THIRD_LINE = asList(11, 12, 13, 14, 15);
	private static final List<Integer> FOURTH_LINE = asList(16, 17, 18, 19, 20);
	private static final List<Integer> FIVETH_LINE = asList(21, 22, 23, 24, 25);

	private List<Integer> dozens = new ArrayList<>();

	public HistoricDataset createHistoricDataSet(DozenDTO dozenDTO, Integer quantityOfDozens) {
		dozens = DozenMapper.toList(dozenDTO);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return HistoricDataset.builder().type(dozenDTO.getType()).dozenSum(sumDozens)
				.average(sumDozens / quantityOfDozens).pair(countPairs()).fibonacci(countFibonacciNumbers())
				.prime(countPrimeNumbers()).fibonacciPrime(countFibonacciPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.concurse(dozenDTO.getConcurse()).firstColumn(countDozens(FIRST_COLUMN))
				.secondColumn(countDozens(SECOND_COLUMN)).thirdColumn(countDozens(THIRD_COLUMN))
				.fourthColumn(countDozens(FOURTH_COLUMN)).fivethColumn(countDozens(FIVETH_COLUMN))
				.firstLine(countDozens(FIRST_LINE)).secondLine(countDozens(SECOND_LINE))
				.thirdLine(countDozens(THIRD_LINE)).fourthLine(countDozens(FOURTH_LINE))
				.fivethLine(countDozens(FIVETH_LINE)).build();
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
		List<Integer> diffs = new ArrayList<>();
		for (int i = 0; i < dozens.size() - 1; i++)
			count = countSequence(count, diffs, i);
		if (count != 0)
			diffs.add(count + 1);
		Collections.sort(diffs);
		Collections.reverse(diffs);
		List<Integer> values = new ArrayList<>();
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
