package com.luckybox.service.importer;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.dto.DozenDTO;
import com.luckybox.service.HistoricFileReaderService;

public class HistoricFileReaderServiceTest {

	private HistoricFileReaderService service = new HistoricFileReaderService();
	
	@Test
	public void readHtmlResultsFile() throws Exception {
		String htmlPath = getClass().getResource("/results/D_LOTFAC.HTM").getFile();
		List<DozenDTO> historic = service.readHTML(htmlPath);
		assertThat(historic.size(), CoreMatchers.equalTo(1494));
		assertConcurse1(historic.get(0));
		assertConcurse1105(historic.get(1104));
	}

	private void assertConcurse1(DozenDTO dozenDTO) {
		MatcherAssert.assertThat(dozenDTO.getConcurse(), CoreMatchers.equalTo(1L));
		MatcherAssert.assertThat(dozenDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(dozenDTO.getDozen1(), CoreMatchers.equalTo(2));
		MatcherAssert.assertThat(dozenDTO.getDozen2(), CoreMatchers.equalTo(3));
		MatcherAssert.assertThat(dozenDTO.getDozen3(), CoreMatchers.equalTo(5));
		MatcherAssert.assertThat(dozenDTO.getDozen4(), CoreMatchers.equalTo(6));	
		MatcherAssert.assertThat(dozenDTO.getDozen5(), CoreMatchers.equalTo(9));
		MatcherAssert.assertThat(dozenDTO.getDozen6(), CoreMatchers.equalTo(10));
		MatcherAssert.assertThat(dozenDTO.getDozen7(), CoreMatchers.equalTo(11));
		MatcherAssert.assertThat(dozenDTO.getDozen8(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(dozenDTO.getDozen9(), CoreMatchers.equalTo(14));
		MatcherAssert.assertThat(dozenDTO.getDozen10(), CoreMatchers.equalTo(16));
		MatcherAssert.assertThat(dozenDTO.getDozen11(), CoreMatchers.equalTo(18));
		MatcherAssert.assertThat(dozenDTO.getDozen12(), CoreMatchers.equalTo(20));
		MatcherAssert.assertThat(dozenDTO.getDozen13(), CoreMatchers.equalTo(23));
		MatcherAssert.assertThat(dozenDTO.getDozen14(), CoreMatchers.equalTo(24));
		MatcherAssert.assertThat(dozenDTO.getDozen15(), CoreMatchers.equalTo(25));
	}
	
	private void assertConcurse1105(DozenDTO dozenDTO) {
		MatcherAssert.assertThat(dozenDTO.getConcurse(), CoreMatchers.equalTo(1105L));
		MatcherAssert.assertThat(dozenDTO.getConcurseDate(), CoreMatchers.notNullValue());
		MatcherAssert.assertThat(dozenDTO.getDozen1(), CoreMatchers.equalTo(1));
		MatcherAssert.assertThat(dozenDTO.getDozen2(), CoreMatchers.equalTo(2));
		MatcherAssert.assertThat(dozenDTO.getDozen3(), CoreMatchers.equalTo(4));
		MatcherAssert.assertThat(dozenDTO.getDozen4(), CoreMatchers.equalTo(6));	
		MatcherAssert.assertThat(dozenDTO.getDozen5(), CoreMatchers.equalTo(7));
		MatcherAssert.assertThat(dozenDTO.getDozen6(), CoreMatchers.equalTo(9));
		MatcherAssert.assertThat(dozenDTO.getDozen7(), CoreMatchers.equalTo(11));
		MatcherAssert.assertThat(dozenDTO.getDozen8(), CoreMatchers.equalTo(13));
		MatcherAssert.assertThat(dozenDTO.getDozen9(), CoreMatchers.equalTo(15));
		MatcherAssert.assertThat(dozenDTO.getDozen10(), CoreMatchers.equalTo(17));
		MatcherAssert.assertThat(dozenDTO.getDozen11(), CoreMatchers.equalTo(18));
		MatcherAssert.assertThat(dozenDTO.getDozen12(), CoreMatchers.equalTo(20));
		MatcherAssert.assertThat(dozenDTO.getDozen13(), CoreMatchers.equalTo(23));
		MatcherAssert.assertThat(dozenDTO.getDozen14(), CoreMatchers.equalTo(24));
		MatcherAssert.assertThat(dozenDTO.getDozen15(), CoreMatchers.equalTo(25));
	}
}
