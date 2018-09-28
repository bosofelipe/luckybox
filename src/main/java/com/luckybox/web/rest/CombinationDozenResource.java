package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.CombinationDozen;
import com.luckybox.service.CombinationDozenService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/combinationdozen")
public class CombinationDozenResource {

	@Inject
	private CombinationDozenService combinationDozen;

	@GetMapping(path = "/generate/{quantityOfNumbers}/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<CombinationDozen> generate(@PathVariable Integer quantityOfNumbers, @PathVariable String type) throws IOException, ZipException, InterruptedException {
		return combinationDozen.generateCombination(quantityOfNumbers, type);
	}
}