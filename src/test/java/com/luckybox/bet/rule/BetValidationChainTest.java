package com.luckybox.bet.rule;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luckybox.domain.Historic;
import com.luckybox.dto.DozenDTO;
import com.luckybox.repository.HistoricRepositoryImpl;

public class BetValidationChainTest {

	@InjectMocks
	private BetValidationChain chainValidation;
	
	@Mock
	private HistoricRepositoryImpl historicDatasetRepositoryImpl;
	
	@Before
	public void start(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void catchRulePrime() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTO());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PRIME));
	}
	
	@Test
	public void catchRulePrimeExceed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozensWithAllPrimes());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PRIME));
	}
	
	@Ignore
	@Test
	public void catchRuleFibonacci() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenFibonacci());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.FIBONACCI));
	}
	
	@Ignore
	@Test
	public void catchRuleFibonacciExceeed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenFibonacci());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.FIBONACCI));
	}
	
	@Test
	public void catchRulePair() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithUnpairs());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PAIR));
	}
	
	@Test
	public void catchRulePairWhenExceed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithExceedPairs());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.PAIR));
	}

	@Test
	public void catchRuleFirstColumn() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithFirstLineDozensWhenLow());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.FIRST_LINE));
	}

	@Test
	public void catchRuleFirstColumnExceed() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithFirstLineDozensWhenExceed());
		assertThat(validationChain.get(0), CoreMatchers.equalTo(RuleType.FIRST_LINE));
	}
	
	@Test
	public void catchRuleLastRaffle() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList(createHistoricManyDozens()));
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithFirstLineDozensWhenExceed());
		assertThat(validationChain, CoreMatchers.hasItem(RuleType.LAST_RAFFLE));
	}
	
	@Test
	public void catchRuleLastRaffleWhenLessDozens() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList(createHistoricLessDozens()));
		List<RuleType> validationChain = chainValidation.validationChain(createDozenDTOWithDozenLastRaffle());
		assertThat(validationChain, CoreMatchers.hasItem(RuleType.LAST_RAFFLE));
	}
	
	@Test
	public void catchRuleSumWithLowSum() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozensWithLowSum());
		assertThat(validationChain, CoreMatchers.hasItem(RuleType.SUM));
	}
	
	@Test
	public void catchRuleSumWithHighSum() throws Exception {
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt())).thenReturn(newArrayList());
		List<RuleType> validationChain = chainValidation.validationChain(createDozensWithHighSum());
		assertThat(validationChain, CoreMatchers.hasItem(RuleType.SUM));
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
	
	private DozenDTO createDozenDTOWithFirstLineDozensWhenLow() {
		return DozenDTO.builder()
		.dozen1(5)//
		.dozen2(6)//
		.dozen3(7)//
		.dozen4(8)//
		.dozen5(9)//
		.dozen6(10)//
		.dozen7(11)//
		.dozen8(12)//
		.dozen9(13)//
		.dozen10(14)//
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
