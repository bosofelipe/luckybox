package com.luckybox.service.importer;

import static com.luckybox.constants.ConstantsLoto.FIBONACCI_SEQUENCE;
import static com.luckybox.constants.ConstantsLoto.PRIME;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.constants.ConstantsLoto;
import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.historic.dto.HistoricDTO;
import com.luckybox.mapper.HistoricMapper;
import com.luckybox.repository.HistoricRepository;

@Service
public class HistoricDatasetCreator {
	private static final int QUANTITY_OF_DOZENS = 15;
	private List<Integer> numbers = new ArrayList<>();

	@Inject
	private HistoricRepository historicRepository;

	public HistoricDataset create(HistoricDTO historicDTO) {
		numbers = HistoricMapper.toList(historicDTO);
		Collections.sort(numbers);
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
				.fourthLine(countDozens(ConstantsLoto.FOURTH_LINE)).fivethLine(countDozens(ConstantsLoto.FIVETH_LINE))
				.alreadyDrawn(checkAlreadyDrawn()).variationSum(calculateVariationSum(historicDTO.getConcurse())).build();
	}

	private Integer sumDozens() {
		return numbers.stream().mapToInt(Number::intValue).sum();
	}

	public Integer sumNumbers(List<Integer> numbers) {
		return (int) numbers.stream().mapToInt(c -> c).sum();
	}

	private Integer countPairs() {
		return (int) numbers.stream().filter(n -> n % 2 == 0).count();
	}

	private Integer countFibonacciNumbers() {
		return (int) numbers.stream().filter(c -> FIBONACCI_SEQUENCE.contains(c)).mapToInt(c -> c).count();
	}

	private Integer countPrimeNumbers() {
		return (int) numbers.stream().filter(c -> PRIME.contains(c)).mapToInt(c -> c).count();
	}

	private List<Integer> getGreaterSequence() {
		int count = 0;
		List<Integer> diffs = new ArrayList<Integer>();
		for (int i = 0; i < numbers.size() - 1; i++)
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
		int value = numbers.get(i + 1) - numbers.get(i);
		if (value == 1)
			count++;
		else {
			if (count != 0)
				diffs.add(count + 1);
			count = 0;
		}
		return count;
	}

	public List<Integer> getDozensAtHistoric(List<Integer> historic, List<Integer> dozens) {
		return historic.stream().filter(c -> dozens.contains(c)).collect(toList());
	}

	public Integer countDozens(List<Integer> dozens) {
		return (int) numbers.stream().filter(c -> dozens.contains(c)).count();
	}

	public Integer calculateVariationSum(Long concurse) {
		if (concurse == 1)
			return 0;
		else
			return calculateVariationWhenNotFirstConcurse(concurse);
	}

	private Integer calculateVariationWhenNotFirstConcurse(Long concurse) {
		Historic previousConcurse = historicRepository.findByConcurse(concurse - 1);
		List<Integer> listOfNumbersPreviousConcurse = HistoricMapper.toList(previousConcurse);
		Collections.sort(listOfNumbersPreviousConcurse);
		int variation = this.sumNumbers(numbers) - this.sumNumbers(listOfNumbersPreviousConcurse);
		return variation < 0 ? variation * -1 : variation;
	}

	public Boolean checkAlreadyDrawn() {
		return !(historicRepository.findHistoricWithDozens(numbers.get(0), numbers.get(1), numbers.get(2), numbers.get(3),
				numbers.get(4), numbers.get(5), numbers.get(6), numbers.get(7), numbers.get(8), numbers.get(9),
				numbers.get(10), numbers.get(11), numbers.get(12), numbers.get(13), numbers.get(14)).isEmpty());
	}
}
