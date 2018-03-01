package com.luckybox.service.importer;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.service.LotoManiaHistoricFileReaderService;

public class LotoManiaHistoricFileReaderServiceTest {

	private LotoManiaHistoricFileReaderService service = new LotoManiaHistoricFileReaderService();
	
	@Test
	public void readHtmlResultsFile() throws Exception {
		String htmlPath = getClass().getResource("/results/D_LOTMAN.HTM").getFile();
		List<DozenDTO> historic = service.readHTML(htmlPath, LotteryType.LOTOMANIA);
		assertThat(historic.size(), CoreMatchers.equalTo(1842));
		assertConcurse1(historic.get(0));
		assertConcurse1105(historic.get(1104));
	}

	private void assertConcurse1(DozenDTO dozenDTO) {
		MatcherAssert.assertThat(dozenDTO.getConcurse(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(dozenDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(dozenDTO.getDozen1(), CoreMatchers.equalTo(0));
		MatcherAssert.assertThat(dozenDTO.getDozen2(), CoreMatchers.equalTo(6));
		MatcherAssert.assertThat(dozenDTO.getDozen3(), CoreMatchers.equalTo(11));
		MatcherAssert.assertThat(dozenDTO.getDozen4(), CoreMatchers.equalTo(14));	
		MatcherAssert.assertThat(dozenDTO.getDozen5(), CoreMatchers.equalTo(16));
		MatcherAssert.assertThat(dozenDTO.getDozen6(), CoreMatchers.equalTo(21));
		MatcherAssert.assertThat(dozenDTO.getDozen7(), CoreMatchers.equalTo(22));
		MatcherAssert.assertThat(dozenDTO.getDozen8(), CoreMatchers.equalTo(25));
		MatcherAssert.assertThat(dozenDTO.getDozen9(), CoreMatchers.equalTo(32));
		MatcherAssert.assertThat(dozenDTO.getDozen10(), CoreMatchers.equalTo(33));
		MatcherAssert.assertThat(dozenDTO.getDozen11(), CoreMatchers.equalTo(34));
		MatcherAssert.assertThat(dozenDTO.getDozen12(), CoreMatchers.equalTo(46));
		MatcherAssert.assertThat(dozenDTO.getDozen13(), CoreMatchers.equalTo(61));
		MatcherAssert.assertThat(dozenDTO.getDozen14(), CoreMatchers.equalTo(70));
		MatcherAssert.assertThat(dozenDTO.getDozen15(), CoreMatchers.equalTo(73));
		MatcherAssert.assertThat(dozenDTO.getDozen16(), CoreMatchers.equalTo(78));
		MatcherAssert.assertThat(dozenDTO.getDozen17(), CoreMatchers.equalTo(88));
		MatcherAssert.assertThat(dozenDTO.getDozen18(), CoreMatchers.equalTo(89));
		MatcherAssert.assertThat(dozenDTO.getDozen19(), CoreMatchers.equalTo(90));
		MatcherAssert.assertThat(dozenDTO.getDozen20(), CoreMatchers.equalTo(95));
	}
	
	private void assertConcurse1105(DozenDTO dozenDTO) {
		MatcherAssert.assertThat(dozenDTO.getConcurse(), CoreMatchers.equalTo(1105L));
		MatcherAssert.assertThat(dozenDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(dozenDTO.getDozen1(), CoreMatchers.equalTo(3));
		MatcherAssert.assertThat(dozenDTO.getDozen2(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(dozenDTO.getDozen3(), CoreMatchers.equalTo(14));
		MatcherAssert.assertThat(dozenDTO.getDozen4(), CoreMatchers.equalTo(22));	
		MatcherAssert.assertThat(dozenDTO.getDozen5(), CoreMatchers.equalTo(27));
		MatcherAssert.assertThat(dozenDTO.getDozen6(), CoreMatchers.equalTo(37));
		MatcherAssert.assertThat(dozenDTO.getDozen7(), CoreMatchers.equalTo(40));
		MatcherAssert.assertThat(dozenDTO.getDozen8(), CoreMatchers.equalTo(41));
		MatcherAssert.assertThat(dozenDTO.getDozen9(), CoreMatchers.equalTo(50));
		MatcherAssert.assertThat(dozenDTO.getDozen10(), CoreMatchers.equalTo(56));
		MatcherAssert.assertThat(dozenDTO.getDozen11(), CoreMatchers.equalTo(61));
		MatcherAssert.assertThat(dozenDTO.getDozen12(), CoreMatchers.equalTo(62));
		MatcherAssert.assertThat(dozenDTO.getDozen13(), CoreMatchers.equalTo(80));
		MatcherAssert.assertThat(dozenDTO.getDozen14(), CoreMatchers.equalTo(83));
		MatcherAssert.assertThat(dozenDTO.getDozen15(), CoreMatchers.equalTo(84));
		MatcherAssert.assertThat(dozenDTO.getDozen16(), CoreMatchers.equalTo(88));
		MatcherAssert.assertThat(dozenDTO.getDozen17(), CoreMatchers.equalTo(92));
		MatcherAssert.assertThat(dozenDTO.getDozen18(), CoreMatchers.equalTo(95));
		MatcherAssert.assertThat(dozenDTO.getDozen19(), CoreMatchers.equalTo(97));
		MatcherAssert.assertThat(dozenDTO.getDozen20(), CoreMatchers.equalTo(99));
	}
}
