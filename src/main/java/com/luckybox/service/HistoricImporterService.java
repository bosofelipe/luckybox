package com.luckybox.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.luckybox.domain.Historic;
import com.luckybox.domain.HistoricDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;

import net.lingala.zip4j.exception.ZipException;

@Service
@Transactional
public class HistoricImporterService {
	private static Logger LOGGER = LogManager.getLogger(HistoricImporterService.class);
	
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

	@Inject
	private HistoricDownloaderFileService historicDownloaderFileService;

	@Inject
	private HistoricFileReaderService historicFileReaderService;

	@Inject
	private HistoricRepository repository;

	@Inject
	private HistoricRepositoryImpl repositoryImpl;

	@Inject
	private HistoricDatasetRepository historicRepository;

	@Inject
	private HistoricDatasetRepositoryImpl historicDatasetRepositoryImpl;

	@Inject
	private DatasetCreator datasetCreator;

	public List<DozenDTO> importConcurses(String type) throws IOException, ZipException {
		if (type.equalsIgnoreCase("all"))
			return importAllLotteries();
		else {
			LotteryType lotteryType = LotteryType.valueOf(type.toUpperCase());
			if (lotteryType != null)
				return importConcurses(lotteryType);
			return Lists.newArrayList();
		}
	}

	private List<DozenDTO> importAllLotteries() throws IOException, ZipException {
		List<DozenDTO> values = Lists.newArrayList();
		Iterable<DozenDTO> combinedIterables = null;
		for (LotteryType type : LotteryType.values()) {
			List<DozenDTO> importConcurses = importConcurses(type);
			combinedIterables = Iterables.unmodifiableIterable(Iterables.concat(values, importConcurses));
		}
		if(combinedIterables != null)
			combinedIterables.forEach(values::add);
		return values;
	}

	private List<DozenDTO> importConcurses(LotteryType lotteryType) throws IOException, ZipException {
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(lotteryType.getZipName());
		List<DozenDTO> historicDTO = historicFileReaderService
				.readHTML(TEMP_DIR + File.separator + lotteryType.getHtmlName(), lotteryType);
		Long lastSavedConcurse = repositoryImpl.getLastIndexRaffle(lotteryType);
		historicDTO.stream().forEach(dto -> persist(dto, lotteryType, lastSavedConcurse));
		return historicDTO;
	}

	public List<DozenDTO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(DozenDTO::new).getContent();
	}

	private void persist(DozenDTO dto, LotteryType type, Long lastSavedConcurse) {
		if (lastSavedConcurse == null || dto.getConcurse() > lastSavedConcurse) {
			Historic historic = repositoryImpl.getHistoryByConcurseAndType(dto.getConcurse(), type);
			if (historic == null) {
				Historic historicEntity = DozenMapper.toHistoric(dto);
				HistoricDataset dataset = null;

				dto.setType(type);
				dataset = datasetCreator.createHistoricDataSet(dto, type.getDozens());

				Historic hist = repositoryImpl.getHistoryByConcurseAndType(historicEntity.getConcurse(),
						historicEntity.getType());
				if (hist == null) {
					dataset.setConcurse(dto.getConcurse());
					saveHistoricDataset(historicEntity, dataset);
					repository.save(historicEntity);
					LOGGER.info(String.format("Saved new concurse %s, type: %s", dto.getConcurse(), dto.getType().getName()));
				}else {
					LOGGER.info(String.format("Concurse %s already imported, type: %s", dto.getConcurse(), dto.getType().getName()));
				}
			}
		}
	}

	private void saveHistoricDataset(Historic historicEntity, HistoricDataset dataset) {
		HistoricDataset histDataset = historicDatasetRepositoryImpl
				.getHistoryByConcurseAndType(historicEntity.getConcurse(), historicEntity.getType());
		if (histDataset != null) {
			dataset.setId(histDataset.getId());
			dataset = historicRepository.save(dataset);
		} else {
			dataset = historicRepository.save(dataset);
			historicEntity.setDataset(dataset);
		}
	}

}
