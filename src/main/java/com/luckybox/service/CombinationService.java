package com.luckybox.service;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Combination;
import com.luckybox.domain.CombinationDTO;
import com.luckybox.domain.CombinationDataset;
import com.luckybox.domain.Historic;
import com.luckybox.mapper.CombinationMapper;
import com.luckybox.repository.CombinationDatasetRepository;
import com.luckybox.repository.CombinationRepository;
import com.luckybox.repository.CombinationRepositoryImpl;
import com.luckybox.repository.HistoricRepository;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CombinationService {
	private static final String SEPARATOR = "-";

	@Inject
	private CombinationRepository combinationRepository;
	
	@Inject
	private CombinationRepositoryImpl combinationRepositoryImpl;
	
	@Inject
	private CombinationDatasetRepository combinationDatasetRepository;
	
	@Inject
	private HistoricRepository historicRepository;

	public void generateCombination(int maxNumber, int quantityOfNumbers) throws InterruptedException {
		int[][] m = geraCombinacao(25, 15);
		Long combinationId = 1L;
		for (int i = 0; i < m.length; i++) {
			persistCombination(m, combinationId, i);
			combinationId++;
		}
	}

	private void persistCombination(int[][] m, Long combinationId, int i)
			throws InterruptedException {
		StringBuilder combinationGroup = new StringBuilder();
		for (int j = 0; j < m[i].length; j++) {
			String value = (m[i][j] + 1) + SEPARATOR;
			combinationGroup.append(value);
			System.out.print(value);
		}
		System.out.println();
		String[] values = combinationGroup.toString().split(SEPARATOR);
		CombinationDTO combinationDTO = createCombination(values, combinationId);
		Combination combinationPersisted = combinationRepository.findOne(combinationId);
		if (combinationPersisted == null) {
			log.info("Create new Combination -> " + combinationGroup);
			saveData(combinationDTO);
		}
	}

	private void saveData(CombinationDTO combinationDTO) {
		combinationRepository.save(CombinationMapper.toEntity(combinationDTO));
		CombinationDataset dataset = new DatasetCreator().create(combinationDTO);
		combinationDatasetRepository.save(dataset);
	}

	private CombinationDTO createCombination(String values[], Long id) {
		return CombinationDTO.builder().combinationId(id).dozen1(Integer.valueOf(values[0]))
				.dozen2(Integer.valueOf(values[1])).dozen3(Integer.valueOf(values[2]))
				.dozen4(Integer.valueOf(values[3])).dozen5(Integer.valueOf(values[4]))
				.dozen6(Integer.valueOf(values[5])).dozen7(Integer.valueOf(values[6]))
				.dozen8(Integer.valueOf(values[7])).dozen9(Integer.valueOf(values[8]))
				.dozen10(Integer.valueOf(values[9])).dozen11(Integer.valueOf(values[10]))
				.dozen12(Integer.valueOf(values[11])).dozen13(Integer.valueOf(values[12]))
				.dozen14(Integer.valueOf(values[13])).dozen15(Integer.valueOf(values[14])).build();
	}

	private int possibities(int n, int p) {
		if (n == p)
			return 1;
		BigInteger nFat = factorial(n);
		BigInteger pFat = factorial(p);
		BigInteger nMinusPFat = factorial(n - p);
		BigInteger result = nFat.divide(pFat.multiply(nMinusPFat));
		return result.intValue();
	}

	private BigInteger factorial(final int n) {
		BigInteger result = BigInteger.valueOf(n);
		for (int i = n - 1; i > 0; i--)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

	private int[][] geraCombinacao(int n, int p) {
		int c = possibities(n, p);
		int[][] m = new int[c][p];
		int[] vN = new int[p];
		for (int i = 0; i < p; i++) {
			vN[i] = i;
			m[0][i] = i;
		}
		for (int i = 1; i < c; i++)
			for (int j = p - 1; j >= 0; j--)
				if (vN[j] < (n - p + j)) {
					vN[j]++;
					for (int k = j + 1; k < p; k++) {
						vN[k] = vN[j] + k - j;
					}
					for (int l = 0; l < p; l++) {
						m[i][l] = vN[l];
					}
					break;
				}
		return m;
	}

	public void checkCombinationDrawn() {
		List<Historic> historicDataset = historicRepository.findAll();
		historicDataset.stream().forEach(h-> findCombination(h));
	}

	private void findCombination(Historic historic) {
		Combination combination = combinationRepositoryImpl.findCombinationWithHistoric(historic);
		if(combination != null)
			combinationRepositoryImpl.markWithDrawn(combination.getCombinationId());
	}
	
	
}