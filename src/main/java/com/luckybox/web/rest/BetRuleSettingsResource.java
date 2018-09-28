package com.luckybox.web.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.BetRuleSettings;
import com.luckybox.service.BetRuleSettingsService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/betrule")
@Produces(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class BetRuleSettingsResource {

	@Inject
	private BetRuleSettingsService betRuleSettingsService;
	
	@GetMapping(path = "/generate/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public BetRuleSettings checkBets(@PathVariable String type) throws IOException, ZipException {
		return betRuleSettingsService.generateBetRuleSettings(type);
	}
}