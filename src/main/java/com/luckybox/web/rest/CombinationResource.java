package com.luckybox.web.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.CombinationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/combination")
@Produces(value = javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(value = javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class CombinationResource {

	@Inject
	private CombinationService combinationService;

	@ApiOperation(value = "Generate info to dozens of lottery types", notes = "")
	@GetMapping(path = "/generate/lotofacil", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String generate() {
		combinationService.createCombinations();
		return "OK";
	}
}
