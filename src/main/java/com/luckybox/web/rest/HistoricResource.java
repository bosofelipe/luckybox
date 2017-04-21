package com.luckybox.web.rest;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.service.importer.HistoricPersisterService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/historic")
public class HistoricResource {

	@Inject
	private HistoricPersisterService persister;

	@GetMapping(path = "/import", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public void importHistoric() throws IOException, ZipException {
		persister.persistHistoricConcurses();
	}
}
