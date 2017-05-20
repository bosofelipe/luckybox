package com.luckybox.service.importer;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.Comparator.naturalOrder;

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
		Long concurse = Long.valueOf(values[0]);
		Date dateOfConcurse = convertStringToDate(values[1]);
		List<Integer> dozens = createOrdenedListOfIntegers(values);
		return HistoricDTO.builder().concurse(concurse).concurseDate(dateOfConcurse)
				.dozen1(dozens.get(0)).dozen2(dozens.get(1))
				.dozen3(dozens.get(2)).dozen4(dozens.get(3))
				.dozen5(dozens.get(4)).dozen6(dozens.get(5))
				.dozen7(dozens.get(6)).dozen8(dozens.get(7))
				.dozen9(dozens.get(8)).dozen10(dozens.get(9))
				.dozen11(dozens.get(10)).dozen12(dozens.get(11))
				.dozen13(dozens.get(12)).dozen14(dozens.get(13))
				.dozen15(dozens.get(14)).build();
	}

	private List<Integer> createOrdenedListOfIntegers(String[] values) {
		List<Integer> dozens = asList(valueOf(values[2]),valueOf(values[3]),valueOf(values[4]),valueOf(values[5]),valueOf(values[6]),valueOf(values[7]),
				valueOf(values[8]),valueOf(values[9]),valueOf(values[10]),valueOf(values[11]),valueOf(values[12]),valueOf(values[13]),valueOf(values[14]),valueOf(values[15]),
						valueOf(values[16]));
		dozens.sort(naturalOrder());
		return dozens;
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
