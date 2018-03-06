package com.luckybox.bet.rule;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepositoryImpl;
import com.luckybox.service.HistoricService;

public class BetValidationChainTest {

	@InjectMocks
	private BetValidationChain chainValidation;
	
	@Mock
	private HistoricRepositoryImpl historicDatasetRepositoryImpl;
	
	@Mock
	private DozenInfoRepository dozenInfoRepository;
	
	@Mock
	private HistoricService historicService;
	
	@Before
	public void start(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void catchRulePrime() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenDTO());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.PRIME_HIGH));
	}
	
	@Test
	public void catchRulePrimeExceed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozensWithAllPrimes());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.PRIME_HIGH));
	}
	
	@Ignore
	@Test
	public void catchRuleFibonacci() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenFibonacci());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.FIBONACCI_LOW));
	}
	
	@Ignore
	@Test
	public void catchRuleFibonacciExceeed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenFibonacci());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.FIBONACCI_HIGH));
	}
	
	@Test
	public void catchRulePair() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenDTOWithUnpairs());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.PAIR_LOW));
	}
	
	@Test
	public void catchRulePairWhenExceed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenDTOWithExceedPairs());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.PAIR_HIGH));
	}
	
	@Test
	public void catchRuleLastRaffle() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList(createHistoricManyDozens()));
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenDTOWithFirstLineDozensWhenExceed());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.LAST_RAFFLE_LOW));
	}
	
	@Test
	public void catchRuleLastRaffleWhenLessDozens() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList(createHistoricLessDozens()));
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozenDTOWithDozenLastRaffle());
		assertThat(validationChain.get(2).getType(), CoreMatchers.equalTo(RuleType.LAST_RAFFLE_LOW));
	}
	
	@Test
	public void catchRuleSumWithLowSum() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozensWithLowSum());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.SUM_LOW));
	}
	
	@Test
	public void catchRuleSumWithHighSum() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozensWithHighSum());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.SUM_HIGH));
	}
	
	@Test
	public void catchRuleCurrentSequenceByDozenInfo() throws Exception {
		when(dozenInfoRepository.findAll()).thenReturn(createDozenInfo());
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.anyObject())).thenReturn(newArrayList());
		List<RuleDTO> validationChain = chainValidation.validationChain(createDozensWithHighSum());
		assertThat(validationChain.get(0).getType(), CoreMatchers.equalTo(RuleType.SUM_HIGH));
	}
	
	private Iterable<DozenInfo> createDozenInfo() {
		Lists.newArrayList(DozenInfo.builder().number(1).currentSequenceDrawn(7L),
				DozenInfo.builder().number(2).currentSequenceDrawn(7L),
				DozenInfo.builder().number(3).currentSequenceDrawn(3L),
				DozenInfo.builder().number(4).currentSequenceDrawn(3L),
				DozenInfo.builder().number(5).currentSequenceDrawn(2L),
				DozenInfo.builder().number(6).currentSequenceDrawn(0L),
				DozenInfo.builder().number(7).currentSequenceDrawn(2L),
				DozenInfo.builder().number(8).currentSequenceDrawn(3L),
				DozenInfo.builder().number(9).currentSequenceDrawn(3L),
				DozenInfo.builder().number(10).currentSequenceDrawn(0L),
				DozenInfo.builder().number(11).currentSequenceDrawn(1L),
				DozenInfo.builder().number(12).currentSequenceDrawn(0L),
				DozenInfo.builder().number(13).currentSequenceDrawn(4L),
				DozenInfo.builder().number(14).currentSequenceDrawn(0L),
				DozenInfo.builder().number(15).currentSequenceDrawn(2L),
				DozenInfo.builder().number(16).currentSequenceDrawn(0L),
				DozenInfo.builder().number(17).currentSequenceDrawn(1L),
				DozenInfo.builder().number(18).currentSequenceDrawn(0L),
				DozenInfo.builder().number(19).currentSequenceDrawn(0L),
				DozenInfo.builder().number(20).currentSequenceDrawn(1L),
				DozenInfo.builder().number(21).currentSequenceDrawn(0L),
				DozenInfo.builder().number(22).currentSequenceDrawn(1L),
				DozenInfo.builder().number(23).currentSequenceDrawn(0L),
				DozenInfo.builder().number(24).currentSequenceDrawn(1L),
				DozenInfo.builder().number(25).currentSequenceDrawn(0L)
				);
		return null;
	}

	private Historic createHistoricManyDozens() {
		return Historic.builder()
				.dozen1(1)//
				.dozen2(2)//
				.dozen3(5)//
				.dozen4(6)//
				.dozen5(7)//
				.dozen6(8)//
				.dozen7(10)//
				.dozen8(11)//
				.dozen9(12)//
				.dozen10(13)//
				.dozen11(15)//
				.dozen12(17)//
				.dozen13(21)//
				.dozen14(24)//
				.dozen15(25)//
				.build();
	}
	
	private Historic createHistoricLessDozens() {
		return Historic.builder()
				.dozen1(1)//
				.dozen2(2)//
				.dozen3(3)//
				.dozen4(4)//
				.dozen5(5)//
				.dozen6(6)//
				.dozen7(9)//
				.dozen8(11)//
				.dozen9(14)//
				.dozen10(13)//
				.dozen11(15)//
				.dozen12(17)//
				.dozen13(18)//
				.dozen14(23)//
				.dozen15(25)//
				.build();
	}

	private DozenDTO createDozenDTO() {
		return DozenDTO.builder()
		.dozen1(2)//
		.dozen2(3)//
		.dozen3(4)//
		.dozen4(5)//
		.dozen5(7)//
		.dozen6(8)//
		.dozen7(10)//
		.dozen8(11)//
		.dozen9(12)//
		.dozen10(13)//
		.dozen11(15)//
		.dozen12(17)//
		.dozen13(21)//
		.dozen14(24)//
		.dozen15(25)//
		.build();
	}
	
	//1, 2, 3, 5, 8, 13, 21
	private DozenDTO createDozenFibonacci() {
		return DozenDTO.builder()
		.dozen1(1)//
		.dozen2(3)//
		.dozen3(6)//
		.dozen4(6)//
		.dozen5(7)//
		.dozen6(9)//
		.dozen7(10)//
		.dozen8(11)//
		.dozen9(12)//
		.dozen10(14)//
		.dozen11(15)//
		.dozen12(17)//
		.dozen13(22)//
		.dozen14(24)//
		.dozen15(25)//
		.build();
	}
	
	private DozenDTO createDozenDTOWithExceedPairs() {
		return DozenDTO.builder()
		.dozen1(2)//
		.dozen2(4)//
		.dozen3(6)//
		.dozen4(8)//
		.dozen5(10)//
		.dozen6(12)//
		.dozen7(14)//
		.dozen8(16)//
		.dozen9(17)//
		.dozen10(19)//
		.dozen11(20)//
		.dozen12(21)//
		.dozen13(23)//
		.dozen14(24)//
		.dozen15(25)//
		.build();
	}
	
	private DozenDTO createDozenDTOWithUnpairs() {
		return DozenDTO.builder()
		.dozen1(1)//
		.dozen2(3)//
		.dozen3(4)//
		.dozen4(7)//
		.dozen5(8)//
		.dozen6(10)//
		.dozen7(11)//
		.dozen8(12)//
		.dozen9(14)//
		.dozen10(15)//
		.dozen11(17)//
		.dozen12(19)//
		.dozen13(21)//
		.dozen14(23)//
		.dozen15(25)//
		.build();
	}
	
	private DozenDTO createDozenDTOWithFirstLineDozensWhenExceed() {
		return DozenDTO.builder()
		.dozen1(1)//
		.dozen2(2)//
		.dozen3(3)//
		.dozen4(4)//
		.dozen5(5)//
		.dozen6(8)//
		.dozen7(10)//
		.dozen8(11)//
		.dozen9(12)//
		.dozen10(13)//
		.dozen11(15)//
		.dozen12(17)//
		.dozen13(21)//
		.dozen14(24)//
		.dozen15(25)//
		.build();
	}
	
	private DozenDTO createDozenDTOWithDozenLastRaffle() {
		return DozenDTO.builder()
				.dozen1(1)//
				.dozen2(3)//
				.dozen3(4)//
				.dozen4(5)//
				.dozen5(7)//
				.dozen6(8)//
				.dozen7(9)//
				.dozen8(11)//
				.dozen9(14)//
				.dozen10(13)//
				.dozen11(15)//
				.dozen12(17)//
				.dozen13(18)//
				.dozen14(23)//
				.dozen15(25)//
				.build();
	}
	
	private DozenDTO createDozensWithLowSum() {
		return DozenDTO.builder()
				.dozen1(1)//
				.dozen2(2)//
				.dozen3(3)//
				.dozen4(4)//
				.dozen5(5)//
				.dozen6(6)//
				.dozen7(7)//
				.dozen8(8)//
				.dozen9(9)//
				.dozen10(10)//
				.dozen11(11)//
				.dozen12(12)//
				.dozen13(13)//
				.dozen14(14)//
				.dozen15(15)//
				.build();
	}
	
	private DozenDTO createDozensWithHighSum() {
		return DozenDTO.builder()
				.dozen1(10)//
				.dozen2(11)//
				.dozen3(12)//
				.dozen4(13)//
				.dozen5(14)//
				.dozen6(15)//
				.dozen7(16)//
				.dozen8(17)//
				.dozen9(18)//
				.dozen10(19)//
				.dozen11(20)//
				.dozen12(21)//
				.dozen13(22)//
				.dozen14(23)//
				.dozen15(24)//
				.build();
	}
	
	private DozenDTO createDozensWithAllPrimes() {
		return DozenDTO.builder()
				.dozen1(2)//
				.dozen2(3)//
				.dozen3(5)//
				.dozen4(7)//
				.dozen5(11)//
				.dozen6(13)//
				.dozen7(14)//
				.dozen8(15)//
				.dozen9(16)//
				.dozen10(17)//
				.dozen11(18)//
				.dozen12(19)//
				.dozen13(20)//
				.dozen14(22)//
				.dozen15(23)//
				.build();
	}
	
}
