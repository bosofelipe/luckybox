package com.luckybox.web.rest;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.InfoService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/info")
public class ImportAllDataResource {
	
	@Inject
	private InfoService infoService;

	@ApiOperation(value="Import all historic", notes="")
	@GetMapping(path = "/import", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String importData() throws IOException, ZipException {
		infoService.importData();
		return "Importation OK!";
	}
	
}
