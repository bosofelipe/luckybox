package com.luckybox.bet.rule;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.luckybox.domain.Bet;
import com.luckybox.domain.BetRule;
import com.luckybox.domain.BetRuleSettings;
import com.luckybox.domain.DozenInfo;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.repository.BetRuleSettingsRepository;
import com.luckybox.repository.DozenInfoRepository;
import com.luckybox.repository.HistoricRepository;
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

	@Mock
	private HistoricRepository historicRepository;

	@Mock
	private BetRuleSettingsRepository betRuleSettingsRepository;

	@Before
	public void start() {
		MockitoAnnotations.initMocks(this);
		when(betRuleSettingsRepository.findByType(LotteryType.LOTOFACIL)).thenReturn(createBetRule());
		when(dozenInfoRepository.findAll()).thenReturn(createDozenInfo());
	}

	private BetRuleSettings createBetRule() {
		BetRuleSettings settings = BetRuleSettings.builder().maxDozensLastRaffle(10)//
				.maxFibonacci(5)//
				.minFibonacci(2)//
				.maxPrime(7)//
				.minPrime(3)//
				.maxFibonacciPrime(7)//
				.minFibonacciPrime(1)//
				.maxSequence(6)//
				.minSequence(5)//
				.maxPair(10)//
				.minPair(6)//
				.maxDozensLastRaffle(10)//
				.minDozensLastRaffle(6)//
				.maxGreatherSequence(10)//
				.minGreatherSequence(2)//
				.minQuantitySequence(3)//
				.maxQuantitySequence(5)//
				.minSum(156)//
				.maxSum(256)//
				.type(LotteryType.LOTOFACIL)//
				.build();
		return settings;
	}

	@Test
	public void catchRulePrimeHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 17, 19, 23));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.PRIME_HIGH));
	}

	@Test
	public void catchRulePrimeLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 4, 6, 8, 10, 11, 12, 14, 15, 16, 18, 20, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.PRIME_LOW));
	}

	@Test
	public void catchRuleFibonacciLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 4, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 18, 22, 23));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.FIBONACCI_LOW));
	}

	@Test
	public void catchRuleFibonacciHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 13, 21, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.FIBONACCI_HIGH));
	}

	@Test
	public void catchRulePairLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 4, 6, 5, 9, 10, 11, 13, 15, 17, 19, 21, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.PAIR_LOW));
	}

	@Test
	public void catchRulePairHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.PAIR_HIGH));
	}

	@Test
	public void catchRuleLastRaffleLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(
				newArrayList(createHistoric(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15))));
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.LAST_RAFFLE_LOW));
	}

	@Test
	public void catchRuleLastRaffleHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 18, 19, 20, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(
				newArrayList(createHistoric(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 15, 16, 17, 18, 19, 20, 25))));
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.LAST_RAFFLE_HIGH));
	}

	@Test
	public void catchRuleSumLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.SUM_LOW));
	}

	@Test
	public void catchRuleSumHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.SUM_HIGH));
	}

	@Test
	public void catchRuleAlreadyDrawn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 8, 9, 11, 12, 14, 18, 20, 21, 23));

		Historic createHistoric = createHistoric(
				Lists.newArrayList(1, 2, 3, 4, 5, 6, 8, 9, 11, 12, 14, 18, 20, 21, 23));
		when(historicDatasetRepositoryImpl.findHistoricByDozens(Mockito.any()))
				.thenReturn(Lists.newArrayList(createHistoric));

		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(
				newArrayList(createHistoric(Lists.newArrayList(1, 2, 3, 4, 5, 6, 8, 9, 11, 12, 14, 18, 20, 21, 23))));
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(1).getRuleType(), CoreMatchers.equalTo(RuleType.ALREADY_DRAWN));
	}

	@Test
	public void catchRuleGreatherSequenceLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 3, 5, 7, 9, 10, 12, 13, 15, 16, 19, 20, 22, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.GREATER_SEQUENCE_LOW));
	}

	@Test
	public void catchRuleGreatherSequenceHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), equalTo(RuleType.GREATER_SEQUENCE_HIGH));
	}

	@Test
	public void catchRuleQtdSequenceHigh() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 4, 6, 8, 9, 11, 12, 14, 15, 17, 18, 20, 21, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.QTD_SEQUENCE_HIGH));
	}

	@Test
	public void catchRuleQtdSequenceLow() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 4, 6, 9, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.QTD_SEQUENCE_LOW));
	}

	@Test
	public void catchRuleEmptyFirstLine() throws Exception {
		Bet bet = createBet(Lists.newArrayList(6, 7, 8, 9, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(1).getRuleType(), CoreMatchers.equalTo(RuleType.FIRST_LINE));
	}

	@Test
	public void catchRuleEmptySecondLine() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 12, 14, 16, 17, 18, 19, 20, 21, 22, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(1).getRuleType(), CoreMatchers.equalTo(RuleType.SECOND_LINE));
	}

	@Test
	public void catchRuleEmptyThirdLine() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 6, 7, 8, 9, 16, 17, 18, 19, 20, 21, 22, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.THIRD_LINE));
	}

	@Test
	public void catchRuleEmptyFourthLine() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 14, 21, 22, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(2).getRuleType(), CoreMatchers.equalTo(RuleType.FOURTH_LINE));
	}

	@Test
	public void catchRuleEmptyFivethLine() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 4, 6, 7, 8, 9, 12, 14, 16, 17, 18, 19, 20));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.FIVETH_LINE));
	}

	@Test
	public void catchRuleEmptyFirstColumn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(5, 7, 8, 9, 12, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.FIRST_COLUMN));
	}

	@Test
	public void catchRuleEmptySecondColumn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 3, 6, 8, 9, 10, 11, 14, 16, 18, 19, 20, 21, 23, 24));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.SECOND_COLUMN));
	}

	@Test
	public void catchRuleEmptyThirdColumn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 6, 7, 9, 10, 12, 14, 16, 17, 19, 20, 21, 22, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.THIRD_COLUMN));
	}

	@Test
	public void catchRuleEmptyFourthColumn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(1, 2, 3, 6, 7, 8, 12, 13, 16, 17, 18, 20, 21, 23, 25));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(1).getRuleType(), CoreMatchers.equalTo(RuleType.FOURTH_COLUMN));
	}

	@Test
	public void catchRuleEmptyFivethColumn() throws Exception {
		Bet bet = createBet(Lists.newArrayList(2, 6, 7, 8, 11, 12, 13, 16, 17, 18, 19, 21, 22, 23, 24));
		when(historicDatasetRepositoryImpl.getLastRaffles(anyInt(), Mockito.any())).thenReturn(newArrayList());
		List<BetRule> validationChain = chainValidation.validationChain(bet);
		assertThat(validationChain.get(0).getRuleType(), CoreMatchers.equalTo(RuleType.FIVETH_COLUMN));
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
				DozenInfo.builder().number(25).currentSequenceDrawn(0L));
		return null;
	}

	private Historic createHistoric(List<Integer> dozens) {
		return Historic.builder().dozen1(dozens.get(0))//
				.dozen2(dozens.get(1))//
				.dozen3(dozens.get(2))//
				.dozen4(dozens.get(3))//
				.dozen5(dozens.get(4))//
				.dozen6(dozens.get(5))//
				.dozen7(dozens.get(6))//
				.dozen8(dozens.get(7))//
				.dozen9(dozens.get(8))//
				.dozen10(dozens.get(9))//
				.dozen11(dozens.get(10))//
				.dozen12(dozens.get(11))//
				.dozen13(dozens.get(12))//
				.dozen14(dozens.get(13))//
				.dozen15(dozens.get(14))//
				.type(LotteryType.LOTOFACIL)//
				.build();
	}

	private Bet createBet(List<Integer> dozens) {
		return Bet.builder().dozen1(dozens.get(0))//
				.dozen2(dozens.get(1))//
				.dozen3(dozens.get(2))//
				.dozen4(dozens.get(3))//
				.dozen5(dozens.get(4))//
				.dozen6(dozens.get(5))//
				.dozen7(dozens.get(6))//
				.dozen8(dozens.get(7))//
				.dozen9(dozens.get(8))//
				.dozen10(dozens.get(9))//
				.dozen11(dozens.get(10))//
				.dozen12(dozens.get(11))//
				.dozen13(dozens.get(12))//
				.dozen14(dozens.get(13))//
				.dozen15(dozens.get(14))//
				.type(LotteryType.LOTOFACIL)//
				.build();
	}
}
