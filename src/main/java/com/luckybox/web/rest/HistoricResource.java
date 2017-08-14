package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.Historic;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.service.HistoricImporterService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/historic")
public class HistoricResource {

	@Inject
	private HistoricImporterService historicService;
	
	@Inject
	private HistoricRepository historicRepository;

	@GetMapping(path = "/import", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenDTO> importHistoric() throws IOException, ZipException {
		return historicService.importConcurses();
	}
	
	@GetMapping(path = "/findAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenDTO> findAll(Pageable pageable) throws IOException, ZipException {
		return historicService.findAll(pageable);
	}
	
	@PostMapping(path = "/save", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Historic> saveHistoric(@RequestBody DozenDTO historicDTO) throws IOException, ZipException {
		return new ResponseEntity<Historic>(historicRepository.save(DozenMapper.toHistoric(historicDTO)), HttpStatus.ACCEPTED);
	}
}
