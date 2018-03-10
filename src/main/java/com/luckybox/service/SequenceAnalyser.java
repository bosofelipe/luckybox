package com.luckybox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SequenceAnalyser {

	public List<Integer> getGreaterSequence(List<Integer> numbers) {
		int count = 0;
		List<Integer> diffs = new ArrayList<Integer>();
		for (int i = 0; i < numbers.size() - 1; i++)
			count = countSequence(i, count, diffs, numbers);
		if (count != 0)
			diffs.add(count + 1);
		Collections.sort(diffs);
		Collections.reverse(diffs);
		List<Integer> values = new ArrayList<Integer>();
		values.add(diffs.size() >0 ? diffs.get(0) : 0);
		values.add(diffs.size());
		return values;
	}
	
	private int countSequence(int i, int count, List<Integer> diffs, List<Integer> numbers) {
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
	
}
