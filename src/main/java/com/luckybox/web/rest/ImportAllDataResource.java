package com.luckybox.web.rest;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.ImporterDataService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/importer")
public class ImportAllDataResource {

	@Inject
	private ImporterDataService importDataService;

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String importAllData() throws IOException, ZipException {
		importDataService.importHistoric();
		importDataService.checkAlreadyDrawn();
		importDataService.fillDatasetFields();
		importDataService.generateDozenInfo();
		importDataService.generateRules();
		return "Importation OK!";
	}

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/importHistoric", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String importAllHistoric() throws IOException, ZipException {
		importDataService.importHistoric();
		return "Importation all data OK!";
	}

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/checkAlreadyDrawn", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public String checkAlreadyDrawn() throws IOException, ZipException {
		importDataService.checkAlreadyDrawn();
		return "checkAlreadyDrawn OK!";
	}

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/fillDatasetFields", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public String fillDatasetFields() {
		importDataService.fillDatasetFields();
		return "Fill dataset fields OK!";
	}

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/generateDozenInfo", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	public String generateDozenInfo() {
		importDataService.generateDozenInfo();
		return "Generate dozen info OK!";
	}

	@ApiOperation(value = "Import all historic", notes = "")
	@GetMapping(path = "/generateRules", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String generateRules() {
		importDataService.generateRules();
		return "Generate rules OK!";
	}

}
