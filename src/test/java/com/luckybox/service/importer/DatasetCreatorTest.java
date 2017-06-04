package com.luckybox.service.importer;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.domain.CombinationDTO;
import com.luckybox.domain.CombinationDataset;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.HistoricDTO;
import com.luckybox.service.DatasetCreator;

public class DatasetCreatorTest {

	@Test
	public void createHistoricDataset() {
		HistoricDataset dataSet = new DatasetCreator().create(createHistoricDTO());
		MatcherAssert.assertThat(dataSet.getConcurse(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(dataSet.getSum(), equalTo(180));
		MatcherAssert.assertThat(dataSet.getAverage(), equalTo(12));
		MatcherAssert.assertThat(dataSet.getFibonacci(), equalTo(6));
		MatcherAssert.assertThat(dataSet.getPrime(), equalTo(6));
		MatcherAssert.assertThat(dataSet.getPair(), equalTo(7));
		MatcherAssert.assertThat(dataSet.getGreatherSequence(), equalTo(4));
		MatcherAssert.assertThat(dataSet.getQtdSequences(), equalTo(5));
		
		MatcherAssert.assertThat(dataSet.getFirstColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getSecondColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getThirdColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFourthColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFivethColumn(), equalTo(5));
		
		MatcherAssert.assertThat(dataSet.getFirstLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getSecondLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getThirdLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFourthLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFivethLine(), equalTo(5));
	}
	
	@Test
	public void createCombinationDataset() {
		CombinationDataset dataSet = new DatasetCreator().create(createCombinationDTO());
		MatcherAssert.assertThat(dataSet.getCombinationId(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(dataSet.getSum(), equalTo(180));
		MatcherAssert.assertThat(dataSet.getAverage(), equalTo(12));
		MatcherAssert.assertThat(dataSet.getFibonacci(), equalTo(6));
		MatcherAssert.assertThat(dataSet.getPrime(), equalTo(6));
		MatcherAssert.assertThat(dataSet.getPair(), equalTo(7));
		MatcherAssert.assertThat(dataSet.getGreatherSequence(), equalTo(4));
		MatcherAssert.assertThat(dataSet.getQtdSequences(), equalTo(5));
		
		MatcherAssert.assertThat(dataSet.getFirstColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getSecondColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getThirdColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFourthColumn(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFivethColumn(), equalTo(5));
		
		MatcherAssert.assertThat(dataSet.getFirstLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getSecondLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getThirdLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFourthLine(), equalTo(5));
		MatcherAssert.assertThat(dataSet.getFivethLine(), equalTo(5));
	}

	private HistoricDTO createHistoricDTO() {
		return HistoricDTO.builder().
		concurse(1L).concurseDate(new Date())
		.dozen1(2)
		.dozen2(3)
		.dozen3(4)
		.dozen4(5)
		.dozen5(7)
		.dozen6(8)
		.dozen7(10)
		.dozen8(11)
		.dozen9(12)
		.dozen10(13)
		.dozen11(15)
		.dozen12(20)
		.dozen13(21)
		.dozen14(24)
		.dozen15(25).build();
	}
	
	private CombinationDTO createCombinationDTO() {
		return CombinationDTO.builder().
		combinationId(1L)
		.dozen1(2)
		.dozen2(3)
		.dozen3(4)
		.dozen4(5)
		.dozen5(7)
		.dozen6(8)
		.dozen7(10)
		.dozen8(11)
		.dozen9(12)
		.dozen10(13)
		.dozen11(15)
		.dozen12(20)
		.dozen13(21)
		.dozen14(24)
		.dozen15(25).build();
	}
}
