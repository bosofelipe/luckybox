package com.luckybox.service.importer;

import static com.luckybox.constants.ConstantsLoto.FIBONACCI_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.PRIME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.CombinationDTO;
import com.luckybox.domain.CombinationDataset;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.historic.dto.HistoricDTO;
import com.luckybox.mapper.CombinationMapper;
import com.luckybox.mapper.HistoricMapper;

@Service
public class DatasetCreator {
	private static final int QUANTITY_OF_DOZENS = 15;
	private List<Integer> dozens = new ArrayList<>();

	public HistoricDataset create(HistoricDTO historicDTO) {
		dozens = HistoricMapper.toList(historicDTO);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return HistoricDataset.builder().sum(sumDozens).average(sumDozens / QUANTITY_OF_DOZENS).pair(countPairs())
				.fibonacci(countFibonacciNumbers()).prime(countPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.concurse(historicDTO.getConcurse()).firstColumn(countDozens(ConstantsLoto.FIRST_COLUMN))
				.secondColumn(countDozens(ConstantsLoto.SECOND_COLUMN))
				.thirdColumn(countDozens(ConstantsLoto.THIRD_COLUMN))
				.fourthColumn(countDozens(ConstantsLoto.FOURTH_COLUMN))
				.fivethColumn(countDozens(ConstantsLoto.FIVETH_COLUMN)).firstLine(countDozens(ConstantsLoto.FIRST_LINE))
				.secondLine(countDozens(ConstantsLoto.SECOND_LINE)).thirdLine(countDozens(ConstantsLoto.THIRD_LINE))
				.fourthLine(countDozens(ConstantsLoto.FOURTH_LINE)).fivethLine(countDozens(ConstantsLoto.FIVETH_LINE)).build();
	}
	
	public CombinationDataset create(CombinationDTO combination) {
		dozens = CombinationMapper.toList(combination);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return CombinationDataset.builder().sum(sumDozens).average(sumDozens / QUANTITY_OF_DOZENS).pair(countPairs())
				.fibonacci(countFibonacciNumbers()).prime(countPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.combinationId(combination.getCombinationId()).firstColumn(countDozens(ConstantsLoto.FIRST_COLUMN))
				.secondColumn(countDozens(ConstantsLoto.SECOND_COLUMN))
				.thirdColumn(countDozens(ConstantsLoto.THIRD_COLUMN))
				.fourthColumn(countDozens(ConstantsLoto.FOURTH_COLUMN))
				.fivethColumn(countDozens(ConstantsLoto.FIVETH_COLUMN)).firstLine(countDozens(ConstantsLoto.FIRST_LINE))
				.secondLine(countDozens(ConstantsLoto.SECOND_LINE)).thirdLine(countDozens(ConstantsLoto.THIRD_LINE))
				.fourthLine(countDozens(ConstantsLoto.FOURTH_LINE)).fivethLine(countDozens(ConstantsLoto.FIVETH_LINE)).build();
	}
	

	private Integer countDozens(List<Integer> dozens) {
		return (int) dozens.stream().filter(c -> dozens.contains(c)).count();
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
		values.add(diffs.get(0));
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
