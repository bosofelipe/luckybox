package com.luckybox.service.importer;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.luckybox.domain.DozenInfoSequence;
import com.luckybox.service.SequenceAnalyser;

public class SequenceAnalyserTest {

	@Test
	public void analyseSequenceOfDozens() throws Exception {
		List<Integer> greaterSequence = new SequenceAnalyser().getGreaterSequence(createNumbers());
		assertThat(greaterSequence.get(0), CoreMatchers.equalTo(5));
		assertThat(greaterSequence.get(1), CoreMatchers.equalTo(5));
	}
	
	@Test
	public void analyseSequencesOfDozens() throws Exception {
		List<DozenInfoSequence> sequences = new SequenceAnalyser().sequence(createNumbers());
		assertThat(sequences.get(0).getQuantity(), CoreMatchers.equalTo(1));
		assertThat(sequences.get(0).getInitialConcurse(), CoreMatchers.equalTo(1000L));
		assertThat(sequences.get(0).getFinalConcurse(), CoreMatchers.equalTo(1001L));
		
		assertThat(sequences.get(1).getQuantity(), CoreMatchers.equalTo(4));
		assertThat(sequences.get(1).getInitialConcurse(), CoreMatchers.equalTo(1003L));
		assertThat(sequences.get(1).getFinalConcurse(), CoreMatchers.equalTo(1007L));
		
		assertThat(sequences.get(2).getQuantity(), CoreMatchers.equalTo(4));
		assertThat(sequences.get(2).getInitialConcurse(), CoreMatchers.equalTo(1015L));
		assertThat(sequences.get(2).getFinalConcurse(), CoreMatchers.equalTo(1019L));

		assertThat(sequences.get(3).getQuantity(), CoreMatchers.equalTo(1));
		assertThat(sequences.get(3).getInitialConcurse(), CoreMatchers.equalTo(1021L));
		assertThat(sequences.get(3).getFinalConcurse(), CoreMatchers.equalTo(1022L));
		
		assertThat(sequences.get(4).getQuantity(), CoreMatchers.equalTo(1));
		assertThat(sequences.get(4).getInitialConcurse(), CoreMatchers.equalTo(1032L));
		assertThat(sequences.get(4).getFinalConcurse(), CoreMatchers.equalTo(1033L));
	}

	private List<Integer> createNumbers() {
		return Lists.newArrayList(1000,1001, 1003,1004,1005,1006,1007,1010,1015,1016,1017, 1018,1019, 1021,1022, 1025, 1032, 1033);
	}
	
}
