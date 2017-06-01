package com.luckybox.web.rest;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.dto.BetDTO;
import com.luckybox.service.importer.BetService;

@RestController
@RequestMapping("/bet")
public class BetResource {

	@Inject
	private BetService betService;
	
	@PostMapping(path = "/toBet", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public void toBet(@RequestBody BetDTO betDTO){
		betService.save(betDTO);
	}
}
