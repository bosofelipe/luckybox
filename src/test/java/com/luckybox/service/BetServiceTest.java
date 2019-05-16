package com.luckybox.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.luckybox.bet.rule.BetValidationChain;
import com.luckybox.domain.Bet;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.BetInfoDTO;
import com.luckybox.reader.CSVBetReader;
import com.luckybox.repository.BetRepository;
import com.luckybox.repository.BetRepositoryImpl;
import com.luckybox.repository.HistoricRepositoryImpl;

public class BetServiceTest{
	
	@InjectMocks
	public BetService betService;
	
	@Mock
	private BetRepository betRepository;

	@Mock
	private BetRepositoryImpl betRepositoryImpl;

	@Mock
	private HistoricRepositoryImpl historicRepository;

	@Mock
	private CSVBetReader reader;

	@Mock
	private BetValidationChain chainOfRules;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void checkBet() throws Exception {
		Mockito.when(historicRepository.getHistoryByConcurseAndType(1000L, LotteryType.LOTOMANIA)).thenReturn(getHistoryByConcurse(1000L));
		Mockito.when(betRepositoryImpl.findAllNotChecked(LotteryType.LOTOMANIA)).thenReturn(getBets());
		List<BetInfoDTO> checkedBets = betService.checkBets("LOTOMANIA");
		MatcherAssert.assertThat(checkedBets.get(0).getHits(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(checkedBets.get(1).getHits(), CoreMatchers.equalTo(20));
	}
	
	private List<Bet> getBets() {
		return Lists.newArrayList(getBetByConcurse(1000L, 
				Lists.newArrayList(0,1,2,3,4,6,7,8,9,10,12,15,17,20,24,26,27,28,30,31,34,35,37,38,49,56,57,58,59,60,64,70,71,72,73,74,75,76,77,79,80,82,84,85,87,88,93,96,97,99)),
				getBetByConcurse(1000L, 
						Lists.newArrayList(0,1,2,3,4,6,7,8,9,11,12,15,17,21,24,26,27,28,30,33,34,35,37,46,49,56,57,58,69,60,64,70,71,72,73,74,75,76,77,79,80,82,84,85,87,89,95,96,97,99)));
	}
	
	private Bet getBetByConcurse(Long concurse, List<Integer> dozens) {
		return Bet.builder().concurse(concurse)
		.dozen1(dozens.get(0))
		.dozen2(dozens.get(1))
		.dozen3(dozens.get(2))
		.dozen4(dozens.get(3))
		.dozen5(dozens.get(4))
		.dozen6(dozens.get(5))
		.dozen7(dozens.get(6))
		.dozen8(dozens.get(7))
		.dozen9(dozens.get(8))
		.dozen10(dozens.get(9))
		.dozen11(dozens.get(10))
		.dozen12(dozens.get(11))
		.dozen13(dozens.get(12))
		.dozen14(dozens.get(13))
		.dozen15(dozens.get(14))
		.dozen16(dozens.get(15))
		.dozen17(dozens.get(16))
		.dozen18(dozens.get(17))
		.dozen19(dozens.get(18))
		.dozen20(dozens.get(19))
		.dozen21(dozens.get(20))
		.dozen22(dozens.get(21))
		.dozen23(dozens.get(22))
		.dozen24(dozens.get(23))
		.dozen25(dozens.get(24))
		.dozen26(dozens.get(25))
		.dozen27(dozens.get(26))
		.dozen28(dozens.get(27))
		.dozen29(dozens.get(28))
		.dozen30(dozens.get(29))
		.dozen31(dozens.get(30))
		.dozen32(dozens.get(31))
		.dozen33(dozens.get(32))
		.dozen34(dozens.get(33))
		.dozen35(dozens.get(34))
		.dozen36(dozens.get(35))
		.dozen37(dozens.get(36))
		.dozen38(dozens.get(37))
		.dozen39(dozens.get(38))
		.dozen40(dozens.get(39))
		.dozen41(dozens.get(40))
		.dozen42(dozens.get(41))
		.dozen43(dozens.get(42))
		.dozen44(dozens.get(43))
		.dozen45(dozens.get(44))
		.dozen46(dozens.get(45))
		.dozen47(dozens.get(46))
		.dozen48(dozens.get(47))
		.dozen49(dozens.get(48))
		.dozen50(dozens.get(49))
		.build();
	} 

	private Historic getHistoryByConcurse(Long concurse) {
			return Historic.builder().concurse(concurse)
			.dozen1(11)
			.dozen2(21)
			.dozen3(33)
			.dozen4(34)
			.dozen5(35)
			.dozen6(46)
			.dozen7(49)
			.dozen8(58)
			.dozen9(69)
			.dozen10(70)
			.dozen11(71)
			.dozen12(72)
			.dozen13(73)
			.dozen14(74)
			.dozen15(75)
			.dozen16(89)
			.dozen17(95)
			.dozen18(96)
			.dozen19(97)
			.dozen20(0).build();
		} 
}