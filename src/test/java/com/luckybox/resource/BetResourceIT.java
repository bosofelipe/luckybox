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

import com.luckybox.dto.DozenDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BetResourceIT {

	@Autowired
	private TestRestTemplate rest;
	
	
	@Test
	public void toBet() throws Exception {
		ResponseEntity<DozenDTO> entity = rest.postForEntity("/bet/toBet", createDozenDTO(), DozenDTO.class);
		MatcherAssert.assertThat(entity.getBody().getConcurse(), CoreMatchers.notNullValue());
	}
	
	@Test
	public void betNotDrawn() throws Exception {
		ResponseEntity<Boolean> entity = rest.postForEntity("/bet/validate", createDozenDTO2(), Boolean.class);
		MatcherAssert.assertThat(entity.getBody().booleanValue(), CoreMatchers.equalTo(false));
	}
	
	@Test
	public void betAlreadyDrawn() throws Exception {
		rest.postForEntity("/historic/save", createHistoricDTO(), DozenDTO.class);
		ResponseEntity<Boolean> entity = rest.postForEntity("/bet/validate", createHistoricDTO(), Boolean.class);
		MatcherAssert.assertThat(entity.getBody().booleanValue(), CoreMatchers.equalTo(true));
	}
	
	private DozenDTO createDozenDTO() {
		return DozenDTO.builder().concurse(1522L)
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
	
	private DozenDTO createDozenDTO2() {
		return DozenDTO.builder().concurse(1522L)
		.dozen1(1)
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
	
	private DozenDTO createHistoricDTO() {
		return DozenDTO.builder().concurse(1522L)
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
