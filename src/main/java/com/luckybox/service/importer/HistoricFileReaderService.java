package com.luckybox.service.importer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckybox.historic.dto.HistoricDTO;

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j
public class HistoricFileReaderService {
	private static final String TABLE_LINES = "table tr";
	private static final String SPLIT_SEPARATOR = " ";
	private static final String FIRST_LINE = "Concurso";
	private static final String UTF_8 = "UTF-8";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final Date EMPTY_DATE = null;

	private List<HistoricDTO> historicData = new ArrayList<>();

	public List<HistoricDTO> readHTML(String htmlPath) throws IOException {
		File input = new File(htmlPath);
		Document doc = Jsoup.parse(input, UTF_8);
		Elements allLines = doc.select(TABLE_LINES);
		return readDocumentBody(allLines);
	}

	private List<HistoricDTO> readDocumentBody(Elements allLines) {
		List<String> columnValues = new ArrayList<>();
		allLines.stream().forEach(element -> catchElement(columnValues, element));
		return historicData;
	}

	private void catchElement(List<String> columnValues, Element element) {
		String nodeValue = element.text();
		if (!nodeValue.isEmpty()) {
			String[] values = element.text().split(SPLIT_SEPARATOR);
			if (values.length > 8 && !values[0].equalsIgnoreCase(FIRST_LINE))
				historicData.add(createHistoricDTO(values));
		}
	}

	private HistoricDTO createHistoricDTO(String[] values) {
		return buildHistoricDTO(values);
	}

	private HistoricDTO buildHistoricDTO(String[] values) {
		return HistoricDTO.builder().concurse(Long.valueOf(values[0])).concurseDate(convertStringToDate(values[1]))
				.dozen1(Integer.valueOf(values[2])).dozen2(Integer.valueOf(values[3]))
				.dozen3(Integer.valueOf(values[4])).dozen4(Integer.valueOf(values[5]))
				.dozen5(Integer.valueOf(values[6])).dozen6(Integer.valueOf(values[7]))
				.dozen7(Integer.valueOf(values[8])).dozen8(Integer.valueOf(values[9]))
				.dozen9(Integer.valueOf(values[10])).dozen10(Integer.valueOf(values[11]))
				.dozen11(Integer.valueOf(values[12])).dozen12(Integer.valueOf(values[13]))
				.dozen13(Integer.valueOf(values[14])).dozen14(Integer.valueOf(values[15]))
				.dozen15(Integer.valueOf(values[16])).build();
	}

	private Date convertStringToDate(String dateAtString) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			return format.parse(dateAtString);
		} catch (ParseException e) {
			log.error("Error on parse string to date " + dateAtString);
			return EMPTY_DATE;
		}
	}

}
