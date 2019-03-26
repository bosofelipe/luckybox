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
import com.luckybox.domain.LineColumnDataset;
import com.luckybox.domain.LotteryType;
import com.luckybox.domain.QuadrantDataset;
import com.luckybox.dto.DozenDTO;
import com.luckybox.mapper.DozenMapper;
import com.luckybox.repository.HistoricDatasetRepository;
import com.luckybox.repository.HistoricDatasetRepositoryImpl;
import com.luckybox.repository.HistoricRepository;
import com.luckybox.repository.HistoricRepositoryImpl;
import com.luckybox.repository.LineColumnDatasetRepository;
import com.luckybox.repository.LineColumnDatasetRepositoryImpl;
import com.luckybox.repository.QuadrantDatasetRepository;
import com.luckybox.repository.QuadrantDatasetRepositoryImpl;

import net.lingala.zip4j.exception.ZipException;

@Service
@Transactional
public class HistoricImporterService {
	private static Logger log = LogManager.getLogger(HistoricImporterService.class);

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
	private LineColumnDatasetRepositoryImpl lineColumnRepositoryImpl;

	@Inject
	private LineColumnDatasetRepository lineColumnDatasetRepository;

	@Inject
	private QuadrantDatasetRepository quadrantDatasetRepository;

	@Inject
	private QuadrantDatasetRepositoryImpl quadrantDatasetRepositoryImpl;
	
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
		if (combinedIterables != null)
			combinedIterables.forEach(values::add);
		return values;
	}

	private List<DozenDTO> importConcurses(LotteryType lotteryType) throws IOException, ZipException {
		historicDownloaderFileService.downloadHtmlZippedFileAtCaixa(lotteryType.getZipName());
		List<DozenDTO> historicDTO = historicFileReaderService
				.readHTML(TEMP_DIR + File.separator + lotteryType.getHtmlName(), lotteryType);
		historicDTO.stream().forEach(dto -> persist(dto, lotteryType));
		return historicDTO;
	}

	public List<DozenDTO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(DozenDTO::new).getContent();
	}

	private void persist(DozenDTO dto, LotteryType type) {
		Historic historicEntity = DozenMapper.toHistoric(dto);

		dto.setType(type);
		HistoricDataset dataset = datasetCreator.createHistoricDataSet(dto);
		LineColumnDataset lineColumnDataset = datasetCreator.createLineColumnDataSet(dto);
		QuadrantDataset quadrantDataset = datasetCreator.createQuadrantDataSet(dto);
		

		Historic hist = repositoryImpl.getHistoryByConcurseAndType(historicEntity.getConcurse(),
				historicEntity.getType());
		if (hist == null) {
			dataset.setConcurse(dto.getConcurse());
			saveHistoricDataset(historicEntity, dataset);
			saveLineColumnDataset(historicEntity, lineColumnDataset);
			saveQuadrantDataset(historicEntity, quadrantDataset);
			repository.save(historicEntity);
			log.info(String.format("Saved new concurse %s, type: %s", dto.getConcurse(), dto.getType().getName()));
		} else {
			log.info(String.format("Concurse %s saved, type: %s", dto.getConcurse(), dto.getType().getName()));
		}
	}

	private void saveHistoricDataset(Historic historicEntity, HistoricDataset dataset) {
		HistoricDataset histDataset = historicDatasetRepositoryImpl
				.getHistoryByConcurseAndType(historicEntity.getConcurse(), historicEntity.getType());
		if (histDataset != null) {
			dataset.setId(histDataset.getId());
			historicRepository.save(dataset);
		} else {
			if(dataset != null) {
				dataset = historicRepository.save(dataset);
				historicEntity.setDataset(dataset);
			}
		}
	}

	private void saveLineColumnDataset(Historic historicEntity, LineColumnDataset dataset) {
		LineColumnDataset lineColumnDataset = lineColumnRepositoryImpl
				.getHistoryByConcurseAndType(historicEntity.getConcurse(), historicEntity.getType());
		if (lineColumnDataset != null) {
			dataset.setId(lineColumnDataset.getId());
			lineColumnDatasetRepository.save(dataset);
		} else {
			if(dataset != null) {
				dataset = lineColumnDatasetRepository.save(dataset);
				historicEntity.setLineColumndataset(dataset);
			}
		}
	}
	
	private void saveQuadrantDataset(Historic historicEntity, QuadrantDataset dataset) {
		QuadrantDataset lineColumnDataset = quadrantDatasetRepositoryImpl
				.getHistoryByConcurseAndType(historicEntity.getConcurse(), historicEntity.getType());
		if (lineColumnDataset != null) {
			dataset.setId(lineColumnDataset.getId());
			quadrantDatasetRepository.save(dataset);
		} else {
			if(dataset != null) {
				dataset = quadrantDatasetRepository.save(dataset);
				historicEntity.setQuadrantDataset(dataset);
			}
		}
	}
}
