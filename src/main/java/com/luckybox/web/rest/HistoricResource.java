package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.dto.HistoricDTO;
import com.luckybox.service.importer.HistoricImporterService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/historic")
public class HistoricResource {

	@Inject
	private HistoricImporterService historicService;

	@GetMapping(path = "/import", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<HistoricDTO> importHistoric() throws IOException, ZipException {
		return historicService.importConcurses();
	}
	
	@GetMapping(path = "/findAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<HistoricDTO> findAll(Pageable pageable) throws IOException, ZipException {
		return historicService.findAll(pageable);
	}
}
