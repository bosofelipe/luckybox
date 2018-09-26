package com.luckybox.service;
/*package com.luckybox.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.luckybox.domain.CombinationDozen;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.CombinationDozenRepository;
import com.luckybox.repository.HistoricRepository;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CombinationDozenService {
	private static final String SEPARATOR = "-";

	@Inject
	private HistoricRepository historicRepository;

	@Inject
	private CombinationDozenRepository combinationDozenRepository;

	public List<CombinationDozen> generateCombination(Integer combinations, String type)
			throws InterruptedException, IOException {
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		List<CombinationDozen> combinationDozen = Lists.newArrayList();

		List<Historic> concurses = historicRepository.findAllByType(lotteryType);

		for (int i = 2; i <= combinations; i++) {
			int[][] m = geraCombinacao(25, i);
			for (int j = 0; j < m.length; j++) {
				persistCombination(combinationDozen, concurses, m, j, lotteryType, i);
			}
		}
		return combinationDozen;

	}

	private List<CombinationDozen> persistCombination(List<CombinationDozen> combinationDozens,
			List<Historic> concurses, int[][] m, int i, LotteryType type, Integer combinations)
			throws InterruptedException, IOException {
		StringBuilder combinationGroup = new StringBuilder();
		List<Integer> values = Lists.newArrayList();
		for (int j = 0; j < m[i].length; j++) {
			int number = m[i][j] + 1;
			String value = number + SEPARATOR;
			combinationGroup.append(value);
			values.add(number);
		}

		Map<Integer, List<Integer>> valuesByConcurse = new HashMap<>();
		concurses.forEach(v -> push(v, valuesByConcurse));

		List<Integer> listConcursesWithDozen = Lists.newArrayList();

		valuesByConcurse.forEach((concurse, dozens) -> contains(concurse, dozens, listConcursesWithDozen, values));

		String key = combinationGroup.substring(0, combinationGroup.length() - 1).toString();

		log.info(key + " Combinações -" + combinations);

		combinationDozens.add(save(listConcursesWithDozen, type, key, combinations));

		return combinationDozens;
	}

	private Object contains(Integer concurse, List<Integer> dozens, List<Integer> listConcursesWithDozen,
			List<Integer> values) {
		if (dozens.containsAll(values)) {
			listConcursesWithDozen.add(concurse);
		}
		return dozens;
	}

	private Object push(Historic v, Map<Integer, List<Integer>> valuesByConcurse) {
		valuesByConcurse.put(v.getConcurse().intValue(), DozenMapper.toList(v));
		return v;
	}

	private CombinationDozen save(List<Integer> listConcursesWithDozen, LotteryType type, String key,
			Integer combinations) {

		CombinationDozen dozen = combinationDozenRepository.findByKeyAndType(key, type);
		if (dozen != null && dozen.getId() != null) {
			dozen.setKeyValues(combinations);
			dozen.setType(type);
			dozen.setQuantity(listConcursesWithDozen.size());
			dozen.setLastConcurse(listConcursesWithDozen.stream().reduce((first, second) -> second).orElse(null));
			return combinationDozenRepository.save(dozen);

		} else {
			CombinationDozen combinationDozen = CombinationDozen.builder().type(type)//
					.keyValues(combinations)//
					.key(key)//
					.quantity(listConcursesWithDozen.size())//
					.lastConcurse(listConcursesWithDozen.stream().reduce((first, second) -> second).orElse(null))//
					.build();
			return combinationDozenRepository.save(combinationDozen);
		}
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

}
*/