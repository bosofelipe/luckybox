package com.luckybox.service;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LineColumnDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QuadrantDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;

@Service
public class DatasetCreator {
	private static final List<Integer> FIBONACCI_SEQUENCE = asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89);
	private static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
			61, 67, 71, 73, 79, 83, 89, 97);

	private List<Integer> dozens = new ArrayList<>();

	public HistoricDataset createHistoricDataSet(DozenDTO dozenDTO) {
		dozens = DozenMapper.toList(dozenDTO);
		Collections.sort(dozens);
		Integer sumDozens = sumDozens();
		return HistoricDataset.builder().type(dozenDTO.getType()).dozenSum(sumDozens).pair(countPairs()).fibonacci(countFibonacciNumbers())
				.prime(countPrimeNumbers())
				.greatherSequence(getGreaterSequence().get(0)).qtdSequences(getGreaterSequence().get(1))
				.concurse(dozenDTO.getConcurse()).build();
	}
	
	public LineColumnDataset createLineColumnDataSet(DozenDTO dto) {
		if(dto.getType() == LotteryType.LOTOFACIL) {
			Map<Integer, Integer[]> lotofacilLines = LineColumnDataset.LOTOFACIL_LINES;
			Map<Integer, Integer[]> lotofacilColumns = LineColumnDataset.LOTOFACIL_COLUMNS;
			return LineColumnDataset.builder()
					.type(dto.getType())
					.concurse(dto.getConcurse())
					.line1(countDozens(lotofacilLines.get(1)))
					.line2(countDozens(lotofacilLines.get(2)))
					.line3(countDozens(lotofacilLines.get(3)))
					.line4(countDozens(lotofacilLines.get(4)))
					.line5(countDozens(lotofacilLines.get(5)))
					.column1(countDozens(lotofacilColumns.get(1)))
					.column2(countDozens(lotofacilColumns.get(2)))
					.column3(countDozens(lotofacilColumns.get(3)))
					.column4(countDozens(lotofacilColumns.get(4)))
					.column5(countDozens(lotofacilColumns.get(5)))
					.build();
		}
		if(dto.getType() == LotteryType.LOTOMANIA) {
			Map<Integer, Integer[]> lines = LineColumnDataset.LOTOMANIA_LINES;
			Map<Integer, Integer[]> columns = LineColumnDataset.LOTOMANIA_COLUMNS;
			return LineColumnDataset.builder()
					.type(dto.getType())
					.concurse(dto.getConcurse())
					.line1(countDozens(lines.get(1)))
					.line2(countDozens(lines.get(2)))
					.line3(countDozens(lines.get(3)))
					.line4(countDozens(lines.get(4)))
					.line5(countDozens(lines.get(5)))
					.line6(countDozens(lines.get(6)))
					.line7(countDozens(lines.get(7)))
					.line8(countDozens(lines.get(8)))
					.line9(countDozens(lines.get(9)))
					.line10(countDozens(lines.get(10)))
					.column1(countDozens(columns.get(1)))
					.column2(countDozens(columns.get(2)))
					.column3(countDozens(columns.get(3)))
					.column4(countDozens(columns.get(4)))
					.column5(countDozens(columns.get(5)))
					.column6(countDozens(columns.get(6)))
					.column7(countDozens(columns.get(7)))
					.column8(countDozens(columns.get(8)))
					.column9(countDozens(columns.get(9)))
					.column10(countDozens(columns.get(10)))
					.build();
		}
		return null;
	}
	
	public QuadrantDataset createQuadrantDataSet(DozenDTO dto) {
		return QuadrantDataset.builder()
				.miniQuadrant1(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(1)))
				.miniQuadrant2(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(2)))
				.miniQuadrant3(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(3)))
				.miniQuadrant4(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(4)))
				.miniQuadrant5(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(5)))
				.miniQuadrant6(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(6)))
				.miniQuadrant7(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(7)))
				.miniQuadrant8(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(8)))
				.miniQuadrant9(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(9)))
				.miniQuadrant10(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(10)))
				.miniQuadrant11(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(11)))
				.miniQuadrant12(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(12)))
				.miniQuadrant13(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(13)))
				.miniQuadrant14(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(14)))
				.miniQuadrant15(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(15)))
				.miniQuadrant16(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(16)))
				.miniQuadrant17(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(17)))
				.miniQuadrant18(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(18)))
				.miniQuadrant19(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(19)))
				.miniQuadrant20(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(20)))
				.miniQuadrant21(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(21)))
				.miniQuadrant22(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(22)))
				.miniQuadrant23(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(23)))
				.miniQuadrant24(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(24)))
				.miniQuadrant25(countDozens(QuadrantDataset.LOTOMANIA_MINI_QUADRANTS.get(25)))
				.type(dto.getType())
				.concurse(dto.getConcurse())
				.build();
	}
	
	private Integer countDozens(Integer[] sequenceDozens) {
		List<Integer> values = Lists.newArrayList(sequenceDozens);
		return (int) dozens.stream().filter(containsValues(values)).mapToInt(c -> c).count();
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
	
	private Predicate<? super Integer> containsValues(List<Integer> values) {
		return c -> values.contains(c);
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
