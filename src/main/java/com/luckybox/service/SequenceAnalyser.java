package com.luckybox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luckybox.domain.DozenInfoSequence;


@Service
public class SequenceAnalyser {

	public List<Integer> getGreaterSequence(List<Integer> numbers) {
		int count = 0;
		List<Integer> diffs = new ArrayList<>();
		for (int i = 0; i < numbers.size() - 1; i++)
			count = countSequence(i, count, diffs, numbers);
		if (count != 0)
			diffs.add(count + 1);
		Collections.sort(diffs);
		Collections.reverse(diffs);
		List<Integer> values = new ArrayList<>();
		values.add(diffs.isEmpty() ? 0 : diffs.get(0));
		values.add(diffs.size());
		return values;
	}
	
	public List<DozenInfoSequence> sequence(List<Integer> concurses){
		List<DozenInfoSequence> sequences = Lists.newArrayList();
		int cont = 0;   
        StringBuilder str = extractSequences(concurses, cont);   
        String finalSequences = str.toString();
        String values[] = finalSequences.split(",");
        
        for(int i =0;i< values.length;i++){
        	addSequences(sequences, values, i);
        }
        
        return sequences;   
	}
	

	private void addSequences(List<DozenInfoSequence> sequences, String[] values, int i) {
		String value = values[i];
		if(value.contains("-")){
			String concursesInSequence[] = value.split("-");
			Integer initialConcurse = Integer.valueOf(concursesInSequence[0].trim());
			Integer finalConcurse = Integer.valueOf(concursesInSequence[1].trim());
			sequences.add(DozenInfoSequence.builder().quantity(finalConcurse - initialConcurse)
				.initialConcurse(initialConcurse.longValue())
				.finalConcurse(finalConcurse.longValue()).build());
		}
	}

	private StringBuilder extractSequences(List<Integer> concurses, int cont) {
		StringBuilder str = new StringBuilder();   
        String c = String.valueOf(concurses.get(0));   
        for(int i=0; i<concurses.size(); i++) {   
            if(i < concurses.size() -1) {   
                if(concurses.get(i) + 1 == concurses.get(i + 1)) {   
                    cont++;   
                    continue;   
                }   
            }   
            if(cont == 1) {   
                str.append(c).append("-").append(concurses.get(i));   
                cont=0;   
            } else if(cont > 1) {   
                str.append(c).append("-").append(concurses.get(i));   
                cont=0;   
            } else {   
                str.append(concurses.get(i));   
            }   
            if(i < concurses.size()-1) {   
                str.append(", ");   
                c = String.valueOf(concurses.get(i + 1));   
            }   
        }
		return str;
	}
	
	private int countSequence(int i, int count, List<Integer> diffs, List<Integer> numbers) {
		int v1 = numbers.get(i + 1);
		int v2 = numbers.get(i);
		int value = v1 - v2;
		if (value == 1)
			count++;
		else {
			if (count != 0){
				diffs.add(count + 1);
			}
			count = 0;
		}
		return count;
	}
}
