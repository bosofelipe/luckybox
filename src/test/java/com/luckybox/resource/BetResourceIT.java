package com.luckybox.resource;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.luckybox.dto.BetDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BetResourceIT {

	@Autowired
	private TestRestTemplate rest;
	
	
	@Test
	public void toBet() throws Exception {
		ResponseEntity<BetDTO> entity = rest.postForEntity("/bet/toBet", createBetDTO(), BetDTO.class);
		MatcherAssert.assertThat(entity.getBody().getConcurse(), CoreMatchers.notNullValue());
	}
	
	private BetDTO createBetDTO() {
		return BetDTO.builder().concurse(1522L)
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
