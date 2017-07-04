package com.luckybox.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.Combination;
import com.luckybox.service.RecomendationService;

@RestController
@RequestMapping("/recomendation")
public class RecomendationResource {

	@Inject
	private RecomendationService recomendationService;
	
	
	@GetMapping(path = "/{limit}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<Combination> listCombinations(@PathVariable Long limit) throws InterruptedException {
		return recomendationService.listCombinations(limit);
	}
	
	
}
