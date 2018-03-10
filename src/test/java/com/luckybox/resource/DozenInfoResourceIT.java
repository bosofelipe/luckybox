package com.luckybox.resource;

import java.util.List;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.luckybox.domain.DozenInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DozenInfoResourceIT {

	@Inject
	private TestRestTemplate restTemplate;
	
	@Test
	public void generateInfoLotoFacil() throws Exception {
		ParameterizedTypeReference<List<DozenInfo>> historic = new ParameterizedTypeReference<List<DozenInfo>>() {};
		ResponseEntity<List<DozenInfo>> response = restTemplate.exchange("/dozeninfo/generate/lotofacil", HttpMethod.GET, null, historic);
		MatcherAssert.assertThat(response.getBody().get(0), CoreMatchers.notNullValue());
	}
}
