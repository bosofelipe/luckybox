package com.luckybox.resource;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.luckybox.dto.DozenDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HistoricResourceIT {

	@Autowired
	private TestRestTemplate rest;
	
	
	@Test
	public void importConcursesLotofacil() throws Exception {
		ParameterizedTypeReference<List<DozenDTO>> historic = new ParameterizedTypeReference<List<DozenDTO>>() {};
		ResponseEntity<List<DozenDTO>> response = rest.exchange("/historic/import/lotofacil", HttpMethod.GET, null, historic);
		MatcherAssert.assertThat(response.getBody().get(0), CoreMatchers.notNullValue());
	}
	
	@Test
	public void importConcursesLotomania() throws Exception {
		ParameterizedTypeReference<List<DozenDTO>> historic = new ParameterizedTypeReference<List<DozenDTO>>() {};
		ResponseEntity<List<DozenDTO>> response = rest.exchange("/historic/import/lotomania", HttpMethod.GET, null, historic);
		MatcherAssert.assertThat(response.getBody().get(0), CoreMatchers.notNullValue());
	}

}
