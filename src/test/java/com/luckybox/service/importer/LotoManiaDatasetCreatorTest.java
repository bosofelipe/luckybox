package com.luckybox.service.importer;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.service.LotoManiaDatasetCreator;

public class LotoManiaDatasetCreatorTest {

	@Test
	public void createHistoricDataset() {
		HistoricDataset dataSet = new LotoManiaDatasetCreator().createHistoricDataSet(createDozenDTO());
		MatcherAssert.assertThat(dataSet.getConcurse(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(dataSet.getDozenSum(), equalTo(1213));
		MatcherAssert.assertThat(dataSet.getAverage(), equalTo(60));
		MatcherAssert.assertThat(dataSet.getFibonacci(), equalTo(3));
		MatcherAssert.assertThat(dataSet.getPrime(), equalTo(8));
		MatcherAssert.assertThat(dataSet.getPair(), equalTo(9));
		MatcherAssert.assertThat(dataSet.getGreatherSequence(), equalTo(6));
		MatcherAssert.assertThat(dataSet.getQtdSequences(), equalTo(4));
	}
	
	private DozenDTO createDozenDTO() {
		return DozenDTO.builder()
		.concurse(1L)
		.concurseDate(new Date())
		.id(1L)
		.dozen1(23)
		.dozen2(11)
		.dozen3(43)
		.dozen4(12)
		.dozen5(54)
		.dozen6(56)
		.dozen7(67)
		.dozen8(89)
		.dozen9(90)
		.dozen10(20)
		.dozen11(21)
		.dozen12(61)
		.dozen13(62)
		.dozen14(67)
		.dozen15(78)
		.dozen16(89)
		.dozen17(91)
		.dozen18(92)
		.dozen19(93)
		.dozen20(94).build();
	}
}
