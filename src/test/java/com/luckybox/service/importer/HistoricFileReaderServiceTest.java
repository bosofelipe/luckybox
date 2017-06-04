package com.luckybox.service.importer;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.dto.HistoricDTO;
import com.luckybox.service.HistoricFileReaderService;

public class HistoricFileReaderServiceTest {

	private HistoricFileReaderService service = new HistoricFileReaderService();
	
	@Test
	public void readHtmlResultsFile() throws Exception {
		String htmlPath = getClass().getResource("/results/D_LOTFAC.HTM").getFile();
		List<HistoricDTO> historic = service.readHTML(htmlPath);
		assertThat(historic.size(), CoreMatchers.equalTo(1494));
		assertConcurse1(historic.get(0));
		assertConcurse1105(historic.get(1104));
	}

	private void assertConcurse1(HistoricDTO historicDTO) {
		MatcherAssert.assertThat(historicDTO.getConcurse(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(historicDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(historicDTO.getDozen1(), CoreMatchers.equalTo(2));
		MatcherAssert.assertThat(historicDTO.getDozen2(), CoreMatchers.equalTo(3));
		MatcherAssert.assertThat(historicDTO.getDozen3(), CoreMatchers.equalTo(5));
		MatcherAssert.assertThat(historicDTO.getDozen4(), CoreMatchers.equalTo(6));	
		MatcherAssert.assertThat(historicDTO.getDozen5(), CoreMatchers.equalTo(9));
		MatcherAssert.assertThat(historicDTO.getDozen6(), CoreMatchers.equalTo(10));
		MatcherAssert.assertThat(historicDTO.getDozen7(), CoreMatchers.equalTo(11));
		MatcherAssert.assertThat(historicDTO.getDozen8(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(historicDTO.getDozen9(), CoreMatchers.equalTo(14));
		MatcherAssert.assertThat(historicDTO.getDozen10(), CoreMatchers.equalTo(16));
		MatcherAssert.assertThat(historicDTO.getDozen11(), CoreMatchers.equalTo(18));
		MatcherAssert.assertThat(historicDTO.getDozen12(), CoreMatchers.equalTo(20));
		MatcherAssert.assertThat(historicDTO.getDozen13(), CoreMatchers.equalTo(23));
		MatcherAssert.assertThat(historicDTO.getDozen14(), CoreMatchers.equalTo(24));
		MatcherAssert.assertThat(historicDTO.getDozen15(), CoreMatchers.equalTo(25));
	}
	
	private void assertConcurse1105(HistoricDTO historicLotoDTO) {
		MatcherAssert.assertThat(historicLotoDTO.getConcurse(), CoreMatchers.equalTo(1105L));
		MatcherAssert.assertThat(historicLotoDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(historicLotoDTO.getDozen1(), CoreMatchers.equalTo(1));
		MatcherAssert.assertThat(historicLotoDTO.getDozen2(), CoreMatchers.equalTo(2));
		MatcherAssert.assertThat(historicLotoDTO.getDozen3(), CoreMatchers.equalTo(4));
		MatcherAssert.assertThat(historicLotoDTO.getDozen4(), CoreMatchers.equalTo(6));	
		MatcherAssert.assertThat(historicLotoDTO.getDozen5(), CoreMatchers.equalTo(7));
		MatcherAssert.assertThat(historicLotoDTO.getDozen6(), CoreMatchers.equalTo(9));
		MatcherAssert.assertThat(historicLotoDTO.getDozen7(), CoreMatchers.equalTo(11));
		MatcherAssert.assertThat(historicLotoDTO.getDozen8(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(historicLotoDTO.getDozen9(), CoreMatchers.equalTo(15));
		MatcherAssert.assertThat(historicLotoDTO.getDozen10(), CoreMatchers.equalTo(17));
		MatcherAssert.assertThat(historicLotoDTO.getDozen11(), CoreMatchers.equalTo(18));
		MatcherAssert.assertThat(historicLotoDTO.getDozen12(), CoreMatchers.equalTo(20));
		MatcherAssert.assertThat(historicLotoDTO.getDozen13(), CoreMatchers.equalTo(23));
		MatcherAssert.assertThat(historicLotoDTO.getDozen14(), CoreMatchers.equalTo(24));
		MatcherAssert.assertThat(historicLotoDTO.getDozen15(), CoreMatchers.equalTo(25));
	}
}
