package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
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

	@GetMapping(path = "/import/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenDTO> importHistoric(@PathVariable String type) throws IOException, ZipException {
		return historicService.importConcurses(type);
	}
	
	@GetMapping(path = "/list", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Page<Historic> list(Pageable pageable) throws IOException, ZipException {
		return historicRepository.findAll(pageable);
	}
	
	@GetMapping(path = "/list/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Page<Historic> listByType(@PathVariable String type, Pageable pageable) throws IOException, ZipException {
		return historicRepository.findAllByType(LotteryType.valueOf(type.toUpperCase()),pageable);
	}
	
	@PostMapping(path = "/save", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Historic> saveHistoric(@RequestBody DozenDTO historicDTO) throws IOException, ZipException {
		return new ResponseEntity<Historic>(historicRepository.save(DozenMapper.toHistoric(historicDTO)), HttpStatus.ACCEPTED);
	}
}
