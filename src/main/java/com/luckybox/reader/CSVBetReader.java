package com.luckybox.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.luckybox.dto.DozenDTO;
import com.luckybox.exception.FileReaderException;

@Service
public class CSVBetReader {
	private static final String CSV_DIVISOR = ";";

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

		return DozenDTO.builder().concurse(Long.valueOf(info[0]))//
				.dozen1(Integer.valueOf(info[1]))//
				.dozen2(Integer.valueOf(info[2]))//
				.dozen3(Integer.valueOf(info[3]))//
				.dozen4(Integer.valueOf(info[4]))//
				.dozen5(Integer.valueOf(info[5]))//
				.dozen6(Integer.valueOf(info[6]))//
				.dozen7(Integer.valueOf(info[7]))//
				.dozen8(Integer.valueOf(info[8]))//
				.dozen9(Integer.valueOf(info[9]))//
				.dozen10(Integer.valueOf(info[10]))//
				.dozen11(Integer.valueOf(info[11]))//
				.dozen12(Integer.valueOf(info[12]))//
				.dozen13(Integer.valueOf(info[13]))//
				.dozen14(Integer.valueOf(info[14]))//
				.dozen15(Integer.valueOf(info[15]))//
				.build();
	}

}
