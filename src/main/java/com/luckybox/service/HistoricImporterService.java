package com.luckybox.service;

import static org.assertj.core.util.Lists.newArrayList;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricRepository;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Service
@Transactional
public class HistoricImporterService {
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private static final String LOTOFACIL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";
	private static final String LOTOMANIA = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotman.zip";
	
	@Inject
	private HistoricDownloaderFileService historicDownloaderFileService;

	@Inject
	private HistoricFileReaderService historicFileReaderService;

	@Inject
	private HistoricRepository repository;

	@Inject
	private DatasetCreator datasetCreator;

	public List<DozenDTO> importConcurses(String type) throws IOException, ZipException {
		LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
		if(LotteryType.LOTOFACIL.equals(lotteryType)) {
			return importLotoFacil(lotteryType);
		}
		if(LotteryType.LOTOMANIA.equals(lotteryType)) {
			return importLotoMania(lotteryType);
		}
		return newArrayList();
	}

	private List<DozenDTO> importLotoFacil(LotteryType lotteryType) throws IOException, ZipException {
		log.info("Start importation lotofacil");
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(LOTOFACIL);
		List<DozenDTO> historicDTO = historicFileReaderService.readHTML(TEMP_DIR + File.separator + "D_LOTFAC.HTM", lotteryType);
		historicDTO.stream().forEach(dto -> persist(dto));
		log.info("Finish importation lotofacil");
		return historicDTO;
	}
	
	private List<DozenDTO> importLotoMania(LotteryType lotteryType) throws IOException, ZipException {
		log.info("Start importation lotomania");
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(LOTOMANIA);
		List<DozenDTO> historicDTO = historicFileReaderService.readHTML(TEMP_DIR + File.separator + "D_LOTFAC.HTM", lotteryType);
		historicDTO.stream().forEach(dto -> persist(dto));
		log.info("Finish importation lotomania");
		return historicDTO;
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
