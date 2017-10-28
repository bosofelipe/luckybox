package com.luckybox.web.rest;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.CombinationDozensService;
import com.luckybox.service.CombinationFileService;

@RestController
@RequestMapping("/combination")
public class CombinationResource {

	@Inject
	private CombinationDozensService combinationService;
	
	@Inject
	private CombinationFileService combinationDozensService;

	@GetMapping(path = "/create/files", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public void load() throws IOException, InterruptedException {
		combinationDozensService.generateCombination(25, 15);
	}
	
	@GetMapping(path = "/file/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public void generate(@PathVariable("name") String name) throws InterruptedException, IOException {
		combinationService.saveByFile(name);
	}
}
