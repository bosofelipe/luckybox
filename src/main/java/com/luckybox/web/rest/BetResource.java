package com.luckybox.web.rest;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.Bet;
import com.luckybox.dto.DozenDTO;
import com.luckybox.service.BetService;

@RestController
@RequestMapping("/bet")
public class BetResource {

	@Inject
	private BetService betService;
	
	@PostMapping(path = "/toBet", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Bet> toBet(@RequestBody DozenDTO dozenDTO){
		return new ResponseEntity<Bet>(betService.save(dozenDTO), HttpStatus.OK);
	}
	
	@PostMapping(path = "/validate", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Boolean> validateBet(@RequestBody DozenDTO dozenDTO){
		return  new ResponseEntity<Boolean>(betService.isAlreadyDrawn(dozenDTO), HttpStatus.OK);
	}
}
