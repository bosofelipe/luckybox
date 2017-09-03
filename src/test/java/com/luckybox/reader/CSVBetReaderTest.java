package com.luckybox.reader;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.luckybox.dto.DozenDTO;
import com.luckybox.exception.FileReaderException;

public class CSVBetReaderTest {

	private CSVBetReader reader = new CSVBetReader();
	
	@Test
	public void checkBets() throws Exception {
		String fileBets = getClass().getResource("/bets/bets.txt").getFile();
		List<DozenDTO> bets = reader.read(fileBets);
		MatcherAssert.assertThat(bets.size(), CoreMatchers.equalTo(3));
		checkBet(0, bets,  Lists.newArrayList(1556, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
		checkBet(1, bets,  Lists.newArrayList(1556, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,16));
		checkBet(2, bets,  Lists.newArrayList(1556, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,17));
	}
	
	@Test(expected= FileReaderException.class)
	public void throwErrorWhenFileEmpty() throws Exception {
		String fileBets = getClass().getResource("/bets/errorFile.txt").getFile();
		reader.read(fileBets);
	}
	
	@Test(expected= FileReaderException.class)
	public void invalidBet() throws Exception {
		String fileBets = getClass().getResource("/bets/invalidBets.txt").getFile();
		reader.read(fileBets);
	}

	private void checkBet(int index, List<DozenDTO> bets, List<Integer> values) {
		DozenDTO bet = bets.get(index);
		MatcherAssert.assertThat(bet.getConcurse(), CoreMatchers.equalTo(values.get(0).longValue()));
		MatcherAssert.assertThat(bet.getDozen1(), CoreMatchers.equalTo(values.get(1)));
		MatcherAssert.assertThat(bet.getDozen2(), CoreMatchers.equalTo(values.get(2)));
		MatcherAssert.assertThat(bet.getDozen3(), CoreMatchers.equalTo(values.get(3)));
		MatcherAssert.assertThat(bet.getDozen4(), CoreMatchers.equalTo(values.get(4)));
		MatcherAssert.assertThat(bet.getDozen5(), CoreMatchers.equalTo(values.get(5)));
		MatcherAssert.assertThat(bet.getDozen6(), CoreMatchers.equalTo(values.get(6)));
		MatcherAssert.assertThat(bet.getDozen7(), CoreMatchers.equalTo(values.get(7)));
		MatcherAssert.assertThat(bet.getDozen8(), CoreMatchers.equalTo(values.get(8)));
		MatcherAssert.assertThat(bet.getDozen9(), CoreMatchers.equalTo(values.get(9)));
		MatcherAssert.assertThat(bet.getDozen10(), CoreMatchers.equalTo(values.get(10)));
		MatcherAssert.assertThat(bet.getDozen11(), CoreMatchers.equalTo(values.get(11)));
		MatcherAssert.assertThat(bet.getDozen12(), CoreMatchers.equalTo(values.get(12)));
		MatcherAssert.assertThat(bet.getDozen13(), CoreMatchers.equalTo(values.get(13)));
		MatcherAssert.assertThat(bet.getDozen14(), CoreMatchers.equalTo(values.get(14)));
		MatcherAssert.assertThat(bet.getDozen15(), CoreMatchers.equalTo(values.get(15)));
	}
	
}
