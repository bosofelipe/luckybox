package com.luckybox.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.dto.DozenDTO;
import com.luckybox.exception.FileReaderException;

@Service
public class CSVBetReader {
	private static final String CSV_DIVISOR = ",";

	public List<DozenDTO> read(File file) throws IOException {
		return readFile(file);
	}

	private List<DozenDTO> readFile(File file) throws IOException, FileNotFoundException {
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
		if (values.length != 16)
			throw new FileReaderException("Faltando valores para aposta");
		return values;
	}

	private DozenDTO buildDozenDTO(String[] info) {
		List<Integer> dozensOrdered = getDozensOrdered(info);
		return DozenDTO.builder().concurse(Long.valueOf(info[0]))//
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

	private List<Integer> getDozensOrdered(String[] info) {
		List<Integer> values = Lists.newArrayList();
		for (int i = 1; i < info.length; i++) {
			values.add(Integer.parseInt(info[i]));
		}
		Collections.sort(values);
		return values;
	}

}
