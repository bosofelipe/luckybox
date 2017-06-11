package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.NumberInfo;
import com.luckybox.service.NumberInfoService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/numberinfo")
public class NumberInfoResource {

	
	@Inject
	private NumberInfoService numberInfoService;

	@GetMapping(path = "/generate", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<NumberInfo> generate() throws IOException, ZipException {
		return numberInfoService.generateNumberInfo();
	}
}
