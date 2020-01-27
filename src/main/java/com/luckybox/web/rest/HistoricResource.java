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

import com.luckybox.ApiPageable;
import com.luckybox.domain.Historic;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.service.HistoricImporterService;
import com.luckybox.service.HistoricService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/historic")
public class HistoricResource {

	@Inject
	private HistoricService historicService;

	@Inject
	private HistoricImporterService historicImporterService;
	
	@Inject
	private HistoricRepository historicRepository;

	@Inject
	private HistoricDatasetRepository historicDatasetRepository;

	
	@ApiOperation(value="Import historic of concurses", notes="")
	@GetMapping(path = "/import/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenDTO> importHistoric(@PathVariable String type) throws IOException, ZipException {
		return historicImporterService.importConcurses(type);
	}

	@ApiPageable
	@ApiOperation(value="List paginated concurses", notes="")
	@GetMapping(path = "/list", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Page<Historic> list(Pageable pageable) {
		return historicRepository.findAll(pageable);
	}
	
	@ApiPageable
	@ApiOperation(value="List paginated concurses by type of lottery", notes="")
	@GetMapping(path = "/list/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public Page<Historic> listByType(@PathVariable String type,Pageable pageable) {
		return historicRepository.findAllByType(LotteryType.valueOf(type.toUpperCase()),pageable);
	}
	
	@ApiOperation(value="Save a historic of lottery", notes="")
	@PostMapping(path = "/save", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<Historic> saveHistoric(@RequestBody DozenDTO historicDTO) {
		return new ResponseEntity<>(historicRepository.save(DozenMapper.toHistoric(historicDTO)), HttpStatus.ACCEPTED);
	}
	
	@ApiIgnore
	@GetMapping(path = "/clear", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public String clear() {
		historicRepository.deleteAll();
		historicDatasetRepository.deleteAll();
		return "OK";
	}
}
