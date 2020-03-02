package com.luckybox.service;

import java.math.BigInteger;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luckybox.domain.Combination;
import com.luckybox.domain.LotteryType;
import com.luckybox.repository.CombinationRepository;

@Transactional
@Service
public class CombinationService {
	
	@Autowired
	private CombinationRepository combinationRepository;
	
	private static Logger LOG = LogManager.getLogger(CombinationService.class);

	public static int possibities(int n, int p) {
		if (n == p)
			return 1;
		BigInteger nFat = factorial(n);
		BigInteger pFat = factorial(p);
		BigInteger nMinusPFat = factorial(n - p);
		BigInteger result = nFat.divide(pFat.multiply(nMinusPFat));
		return result.intValue();
	}

	public static BigInteger factorial(final int n) {
		BigInteger result = BigInteger.valueOf(n);
		for (int i = n - 1; i > 0; i--)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

	public static int[][] geraCombinacao(int n, int p) {
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

	public void createCombinations() {
		int combinationIndex = 0;
		int[][] m = geraCombinacao(25, 15);
		for (int i = 0; i < m.length; i++) {
			StringBuilder value = new StringBuilder("");
			for (int j = 0; j < m[i].length; j++) {
				value.append((m[i][j] + 1) + " ");
			}
			String finalValue = value.toString();
			Combination combination = createCombination(finalValue.split(" "));
			combinationRepository.save(combination);
			combinationIndex++;
			LOG.info(combinationIndex + " " + combination.toString());
		}
		LOG.info("Total de " + m.length + " combinações");
	}

	private Combination createCombination(String[] split) {
		Combination combination = new Combination();
		combination.setDozen1(Integer.valueOf(split[0]));
		combination.setDozen2(Integer.valueOf(split[1]));
		combination.setDozen3(Integer.valueOf(split[2]));
		combination.setDozen4(Integer.valueOf(split[3]));
		combination.setDozen5(Integer.valueOf(split[4]));
		combination.setDozen6(Integer.valueOf(split[5]));
		combination.setDozen7(Integer.valueOf(split[6]));
		combination.setDozen8(Integer.valueOf(split[7]));
		combination.setDozen9(Integer.valueOf(split[8]));
		combination.setDozen10(Integer.valueOf(split[9]));
		combination.setDozen11(Integer.valueOf(split[10]));
		combination.setDozen12(Integer.valueOf(split[11]));
		combination.setDozen13(Integer.valueOf(split[12]));
		combination.setDozen14(Integer.valueOf(split[13]));
		combination.setDozen15(Integer.valueOf(split[14]));
		combination.setCreationDate(new Date());
		combination.setDozenSum();
		combination.setPair();
		combination.setPrime();
		combination.setFibonacci();
		combination.setSequences();
		combination.setType(LotteryType.LOTOFACIL);
		return combination;
	}
}