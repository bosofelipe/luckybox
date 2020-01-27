package com.luckybox.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.exception.FileReaderException;

@Service
public class CSVBetReader {
	private static final String CSV_DIVISOR = ",";

	public List<DozenDTO> read(File file) throws IOException {
		return readFile(file);
	}

	private List<DozenDTO> readFile(File file) throws IOException {
		List<DozenDTO> bets = new ArrayList<>();
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null)
				try {
					bets.add(buildDozenDTO(getValues(line)));
				} catch (NumberFormatException e) {
					throw new FileReaderException("Arquivo com valores invalidos ou vazio");
				}
		}
		return bets;
	}

	private String[] getValues(String line) {
		String[] values = line.split(CSV_DIVISOR);
		if (values[0] == "LOTOFACIL" && (values.length >= LotteryType.LOTOFACIL.getMinDozens() && values.length <= LotteryType.LOTOFACIL.getMaxDozens()))
			throw new FileReaderException("Faltando valores para aposta da lotofacil");
		if (values[0] == "MEGASENA" && (values.length >= LotteryType.MEGASENA.getMinDozens() && values.length <= LotteryType.MEGASENA.getMaxDozens()))
			throw new FileReaderException("Faltando valores para aposta da megasena");
		if (values[0] == "QUINA" && (values.length >= LotteryType.QUINA.getMinDozens() && values.length <= LotteryType.QUINA.getMaxDozens()))
			throw new FileReaderException("Faltando valores para aposta da quina");
		if (values[0] == "LOTOMANIA" && (values.length >= LotteryType.LOTOMANIA.getMinDozens() && values.length <= LotteryType.LOTOMANIA.getMaxDozens()))
			throw new FileReaderException("Faltando valores para aposta da lotomania");
		return values;
	}

	private DozenDTO buildDozenDTO(String[] info) {
		List<Integer> dozensOrdered = getDozensOrdered(info);

		LotteryType type = LotteryType.valueOf(info[0].toUpperCase());
		if (LotteryType.QUINA.equals(type)) {
			return DozenDTO.builder()
					.type(type)//
					.concurse(Long.valueOf(info[1]))//
					.dozen1(dozensOrdered.get(0))//
					.dozen2(dozensOrdered.get(1))//
					.dozen3(dozensOrdered.get(2))//
					.dozen4(dozensOrdered.get(3))//
					.dozen5(dozensOrdered.get(4))//
					.build();
		}
		if (LotteryType.MEGASENA.equals(type)) {
			return DozenDTO.builder()
					.type(type)//
					.concurse(Long.valueOf(info[1]))//
					.dozen1(dozensOrdered.get(0))//
					.dozen2(dozensOrdered.get(1))//
					.dozen3(dozensOrdered.get(2))//
					.dozen4(dozensOrdered.get(3))//
					.dozen5(dozensOrdered.get(4))//
					.dozen6(dozensOrdered.get(5))//
					.build();
		}
		if (LotteryType.LOTOFACIL.equals(type)) {
			return DozenDTO.builder()
					.type(type)//
					.concurse(Long.valueOf(info[1]))//
					.dozen1(dozensOrdered.get(0))//
					.dozen2(dozensOrdered.get(1))//
					.dozen3(dozensOrdered.get(2))//
					.dozen4(dozensOrdered.get(3))//
					.dozen5(dozensOrdered.get(4))//
					.dozen6(dozensOrdered.get(5))//
					.dozen7(dozensOrdered.get(6))//
					.dozen8(dozensOrdered.get(7))//
					.dozen9(dozensOrdered.get(8))//
					.dozen10(dozensOrdered.get(9))//
					.dozen11(dozensOrdered.get(10))//
					.dozen12(dozensOrdered.get(11))//
					.dozen13(dozensOrdered.get(12))//
					.dozen14(dozensOrdered.get(13))//
					.dozen15(dozensOrdered.get(14))//
					.build();
		}
		if (LotteryType.LOTOMANIA.equals(type)) {
			return DozenDTO.builder()
					.type(type)//
					.concurse(Long.valueOf(info[1]))//
					.dozen1(dozensOrdered.get(0))//
					.dozen2(dozensOrdered.get(1))//
					.dozen3(dozensOrdered.get(2))//
					.dozen4(dozensOrdered.get(3))//
					.dozen5(dozensOrdered.get(4))//
					.dozen6(dozensOrdered.get(5))//
					.dozen7(dozensOrdered.get(6))//
					.dozen8(dozensOrdered.get(7))//
					.dozen9(dozensOrdered.get(8))//
					.dozen10(dozensOrdered.get(9))//
					.dozen11(dozensOrdered.get(10))//
					.dozen12(dozensOrdered.get(11))//
					.dozen13(dozensOrdered.get(12))//
					.dozen14(dozensOrdered.get(13))//
					.dozen15(dozensOrdered.get(14))//
					.dozen16(dozensOrdered.get(15))//
					.dozen17(dozensOrdered.get(16))//
					.dozen18(dozensOrdered.get(17))//
					.dozen19(dozensOrdered.get(18))//
					.dozen20(dozensOrdered.get(19))//
					.build();
		}
		return  DozenDTO.builder().build();
	}

	private List<Integer> getDozensOrdered(String[] info) {
		List<Integer> values = Lists.newArrayList();
		for (int i = 2; i < info.length; i++) {
			values.add(Integer.parseInt(info[i]));
		}
		Collections.sort(values);
		checkRepeatedDozens(values);
		return values;
	}

	private void checkRepeatedDozens(List<Integer> values) {
		Set<Integer> duplicated = values.stream().filter(n -> values.stream().filter(x -> x == n).count() > 1)
				.collect(Collectors.toSet());
		if (!duplicated.isEmpty()) {
			throw new IllegalArgumentException("Duplicated dozens in bet");
		}
	}

}
