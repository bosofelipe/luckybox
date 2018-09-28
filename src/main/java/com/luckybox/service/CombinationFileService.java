package com.luckybox.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class CombinationFileService {
	private static final Integer MAX_COMBINATION_BY_FILE = 100000;
	private static final String SEPARATOR = "-";

	public void generateCombination(int quantityOfNumbers) throws InterruptedException, IOException {
		int[][] m = geraCombinacao(25, 15);
		List<String> values = Lists.newArrayList();
		int fileNumber = 1;
		for (int i = 0; i < m.length; i++) {
			if (values.size() < MAX_COMBINATION_BY_FILE) {
				String combination = persistCombination(m, i);
				values.add(combination);
			} else {
				String combination = persistCombination(m, i);
				values.add(combination);
				createFile(values, fileNumber);
				values = Lists.newArrayList();
				fileNumber++;
			}
		}
	}
	
	public void generateCombinationUniqueFile() throws InterruptedException, IOException {
		int[][] m = geraCombinacao(25, 15);
		List<String> values = Lists.newArrayList();
		for (int i = 0; i < m.length; i++) {
				String combination = persistCombination(m, i);
				values.add(combination);
		}
		createFile(values);
	}

	private String persistCombination(int[][] m, int i) throws InterruptedException, IOException {
		StringBuilder combinationGroup = new StringBuilder();
		for (int j = 0; j < m[i].length; j++) {
			String value = (m[i][j] + 1) + SEPARATOR;
			combinationGroup.append(value);
		}
		String value = combinationGroup.append(combinationGroup.hashCode()).append(SEPARATOR).append(i).toString();
		return value;
	}

	private void createFile(List<String> values) throws IOException {
		File fout = new File(System.getProperty("java.io.tmpdir") + "lotofacil.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		for (int i = 0; i < values.size(); i++) {
			osw.write(i + SEPARATOR + values.get(i) + System.getProperty("line.separator"));
		}
		osw.close();
		fos.close();
	}
	
	private void createFile(List<String> values, int fileNumber) throws IOException {
		File fout = new File(System.getProperty("java.io.tmpdir") + "lotofacil-" + fileNumber + ".txt");
		FileOutputStream fos = new FileOutputStream(fout);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		for (int i = 0; i < values.size(); i++) {
			osw.write(i + SEPARATOR + values.get(i) + System.getProperty("line.separator"));
		}
		osw.close();
		fos.close();
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
	
	public static void main(String[] args) throws InterruptedException, IOException {
		new CombinationFileService().generateCombinationUniqueFile();
	}
}