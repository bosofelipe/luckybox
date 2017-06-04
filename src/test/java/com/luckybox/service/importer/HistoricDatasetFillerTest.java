package com.luckybox.service.importer;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.luckybox.domain.Historic;
import com.luckybox.service.HistoricDatasetFiller;
import com.luckybox.service.HistoricService;

public class HistoricDatasetFillerTest {

	@InjectMocks
	private HistoricDatasetFiller datasetFiller;
	
	@Mock
	private HistoricService historicService;
	
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void dozensLastConcurseWhenFirstConcurse() throws Exception {
		Integer dozensLastConcurse = datasetFiller.dozensLastConcurse(1L);
		MatcherAssert.assertThat(dozensLastConcurse, CoreMatchers.equalTo(0));
	}
	
	@Test
	public void dozensLastConcurse() throws Exception {
		Mockito.doReturn(createHistoricConcurse2()).when(historicService).findByConcurse(2L);
		Mockito.doReturn(createHistoricConcurse1()).when(historicService).findByConcurse(1L);
		Integer dozensLastConcurse = datasetFiller.dozensLastConcurse(2L);
		MatcherAssert.assertThat(dozensLastConcurse, CoreMatchers.equalTo(12));
	}
	
	@Test
	public void calculateVariationSumWhenFirstConcurse() throws Exception {
		Integer dozensLastConcurse = datasetFiller.dozensLastConcurse(1L);
		MatcherAssert.assertThat(dozensLastConcurse, CoreMatchers.equalTo(0));
	}
	
	@Test
	public void calculateVariationSum() throws Exception {
		Mockito.doReturn(createHistoricConcurse2()).when(historicService).findByConcurse(2L);
		Mockito.doReturn(createHistoricConcurse1()).when(historicService).findByConcurse(1L);
		Integer dozensLastConcurse = datasetFiller.calculateVariationSum(2L);
		MatcherAssert.assertThat(dozensLastConcurse, CoreMatchers.equalTo(30));
	}
	
	@Test
	public void calculateVariationSumWhenNegative() throws Exception {
		Mockito.doReturn(createHistoricConcurse1()).when(historicService).findByConcurse(2L);
		Mockito.doReturn(createHistoricConcurse2()).when(historicService).findByConcurse(1L);
		Integer dozensLastConcurse = datasetFiller.calculateVariationSum(2L);
		MatcherAssert.assertThat(dozensLastConcurse, CoreMatchers.equalTo(30));
	}

	private Historic createHistoricConcurse1() {
		return Historic.builder().concurse(1L)
		.dozen1(1)
		.dozen2(2)
		.dozen3(3)
		.dozen4(4)
		.dozen5(5)
		.dozen6(6)
		.dozen7(7)
		.dozen8(8)
		.dozen9(9)
		.dozen10(10)
		.dozen11(11)
		.dozen12(12)
		.dozen13(13)
		.dozen14(14)
		.dozen15(15).build();
	}
	
	private Historic createHistoricConcurse2() {
		return Historic.builder().concurse(2L)
				.dozen1(1)
				.dozen2(2)
				.dozen3(3)
				.dozen4(4)
				.dozen5(5)
				.dozen6(6)
				.dozen7(7)
				.dozen8(8)
				.dozen9(9)
				.dozen10(10)
				.dozen11(11)
				.dozen12(12)
				.dozen13(23)
				.dozen14(24)
				.dozen15(25).build();
	}
}
