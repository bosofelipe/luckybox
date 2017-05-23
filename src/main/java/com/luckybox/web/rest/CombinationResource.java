package com.luckybox.web.rest;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.importer.CombinationService;

@RestController
@RequestMapping("/combination")
public class CombinationResource {

	@Inject
	private CombinationService combinationService;

	@GetMapping(path = "/load", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public void load() throws InterruptedException {
		combinationService.generateCombination(25, 15);
	}
}
