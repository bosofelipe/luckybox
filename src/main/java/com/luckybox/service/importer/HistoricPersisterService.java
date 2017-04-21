package com.luckybox.service.importer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.luckybox.domain.Historic;
import com.luckybox.historic.dto.HistoricDTO;
import com.luckybox.mapper.HistoricMapper;
import com.luckybox.repository.HistoricRepository;

import lombok.extern.log4j.Log4j;
import net.lingala.zip4j.exception.ZipException;

@Log4j
@Service
@Transactional
public class HistoricPersisterService {
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	private static final String CAIXA_URL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";

	@Inject
	private HistoricDownloaderFileService historicDownloaderFileService;

	@Inject
	private HistoricFileReaderService historicFileReaderService;

	@Inject
	private HistoricRepository repository;

	public void persistHistoricConcurses() throws IOException, ZipException {
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(CAIXA_URL);
		List<HistoricDTO> historicDTO = historicFileReaderService.readHTML(TEMP_DIR + File.separator + "D_LOTFAC.HTM");
		historicDTO.stream().forEach(dto -> persist(dto));
		log.info("Finish importation");

	}

	private void persist(HistoricDTO dto) {
		Historic historic = repository.findOne(dto.getConcurse());
		if (historic == null)
			repository.save(new HistoricMapper().toEntity(dto));
	}
}
