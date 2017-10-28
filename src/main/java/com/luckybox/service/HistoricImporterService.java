package com.luckybox.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricRepository;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Service
@Transactional
public class HistoricImporterService {
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private static final String CAIXA_URL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";

	@Inject
	private HistoricDownloaderFileService historicDownloaderFileService;

	@Inject
	private HistoricFileReaderService historicFileReaderService;

	@Inject
	private HistoricRepository repository;

	@Inject
	private HistoricDatasetRepository datasetRepository;

	@Inject
	private DatasetCreator datasetCreator;

	public List<DozenDTO> importConcurses() throws IOException, ZipException {
		log.info("Start importation");
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(CAIXA_URL);
		List<DozenDTO> historicDTO = historicFileReaderService.readHTML(TEMP_DIR + File.separator + "D_LOTFAC.HTM");
		historicDTO.stream().forEach(dto -> persist(dto));
		//historicDTO.stream().forEach(dto -> fillDatasetFields(dto));
		log.info("Finish importation");
		return historicDTO;
	}

	private void fillDatasetFields(DozenDTO dto) {
		Historic historic = repository.findOne(dto.getConcurse());
		if (historic != null) {
			HistoricDataset dataset = datasetCreator.createHistoricDataSet(dto);
			dataset.setConcurse(dto.getConcurse());
			datasetRepository.save(dataset);
		}
	}

	public List<DozenDTO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(DozenDTO::new).getContent();
	}

	private void persist(DozenDTO dto) {
		Historic historic = repository.findOne(dto.getConcurse());
		if (historic == null) {
			Historic historicEntity = DozenMapper.toHistoric(dto);
			HistoricDataset dataset = datasetCreator.createHistoricDataSet(dto);
			dataset.setConcurse(dto.getConcurse());
			historicEntity.setDataset(dataset);
			repository.save(historicEntity);
		}
	}

}
