package com.luckybox.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.CombinationDozens;
import com.luckybox.domain.CombinationDozensDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.CombinationDozensRepository;
import com.luckybox.repository.CombinationDozensRepositoryImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CombinationDozensService {
	private static final String SEPARATOR = "-";
	private static String PATH_LOCAL = System.getProperty("java.io.tmpdir");

	@Inject
	private CombinationDozensRepository combinationDozensRepository;

	@Inject
	private CombinationDozensRepositoryImpl combinationRepositoryImpl;

	@Inject
	private DatasetCreator datasetCreator;

	public void saveByFile(String name) throws IOException {
		long maxConcurseSaved = combinationRepositoryImpl.getMaxConcurseSaved();
		List<String> collectCombinationsByFile = collectCombinationsByFile(name, maxConcurseSaved);
		for (String combination : collectCombinationsByFile) {
			readLine(combination);
		}
	}

	public List<String> collectCombinationsByFile(String name, long starts) {
		List<String> values = Lists.newArrayList();
		try (Stream<String> stream = Files.lines(Paths.get(PATH_LOCAL + name + ".txt")).skip(starts)) {
			values = stream.map(String::toUpperCase).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

	private void readLine(String f) {
		log.info("Saving-> " + f);
		String[] combination = f.split(SEPARATOR);
		DozenDTO dozenDTO = createCombination(combination);
		saveCombinations(dozenDTO);
	}

	private void saveCombinations(DozenDTO dozenDTO) {
		CombinationDozensDataset dataset = datasetCreator.createCombinationDozensDataset(dozenDTO);
		CombinationDozens combinationDozens = DozenMapper.toCombinationDozens(dozenDTO);

		dataset.setId(combinationDozens.getId());
		combinationDozens.setDataset(dataset);
		combinationDozensRepository.save(combinationDozens);
	}

	private DozenDTO createCombination(String values[]) {
		return DozenDTO.builder().id(Long.valueOf(values[17]) + 1).dozen1(Integer.valueOf(values[1]))
				.dozen2(Integer.valueOf(values[2])).dozen3(Integer.valueOf(values[3]))
				.dozen4(Integer.valueOf(values[4])).dozen5(Integer.valueOf(values[5]))
				.dozen6(Integer.valueOf(values[6])).dozen7(Integer.valueOf(values[7]))
				.dozen8(Integer.valueOf(values[8])).dozen9(Integer.valueOf(values[9]))
				.dozen10(Integer.valueOf(values[10])).dozen11(Integer.valueOf(values[11]))
				.dozen12(Integer.valueOf(values[12])).dozen13(Integer.valueOf(values[13]))
				.dozen14(Integer.valueOf(values[14])).dozen15(Integer.valueOf(values[15])).build();
	}
}