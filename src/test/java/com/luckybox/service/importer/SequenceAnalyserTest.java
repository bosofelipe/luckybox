package com.luckybox.service.importer;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.luckybox.service.SequenceAnalyser;

public class SequenceAnalyserTest {

	@Test
	public void analyseSequenceOfDozens() throws Exception {
		List<Integer> greaterSequence = new SequenceAnalyser().getGreaterSequence(createNumbers());
		assertThat(greaterSequence.get(0), CoreMatchers.equalTo(5));
		assertThat(greaterSequence.get(1), CoreMatchers.equalTo(3));
	}

	private List<Integer> createNumbers() {
		return Lists.newArrayList(1000,1003,1004,1005,1006,1007,1010,1015,1016,1017, 1018,1019, 1021,1022);
	}
	
}
