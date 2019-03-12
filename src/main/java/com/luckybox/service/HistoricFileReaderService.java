package com.luckybox.service;

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

import com.luckybox.domain.LotteryType;
import com.luckybox.dto.DozenDTO;

@Service
@Transactional
public class HistoricFileReaderService {
	private static final String TABLE_LINES = "table tr";
	private static final String SPLIT_SEPARATOR = " ";
	private static final String FIRST_LINE = "Concurso";
	private static final String UTF_8 = "UTF-8";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final Date EMPTY_DATE = null;

	private List<DozenDTO> historicData = new ArrayList<>();

	public List<DozenDTO> readHTML(String htmlPath, LotteryType type) throws IOException {
		File input = new File(htmlPath);
		Document doc = Jsoup.parse(input, UTF_8);
		Elements allLines = doc.select(TABLE_LINES);
		return readDocumentBody(allLines, type);
	}

	private List<DozenDTO> readDocumentBody(Elements allLines, LotteryType type) {
		historicData = new ArrayList<>();
		allLines.stream().forEach(element -> catchElement(element, type));
		return historicData;
	}

	private void catchElement(Element element, LotteryType type) {
		String nodeValue = element.text();
		if (!nodeValue.isEmpty()) {
			String[] values = element.text().split(SPLIT_SEPARATOR);
			if (values.length > 8 && !values[0].equalsIgnoreCase(FIRST_LINE))
				historicData.add(createHistoricDTO(values, type));
		}
	}

	private DozenDTO createHistoricDTO(String[] values, LotteryType type) {
		return buildHistoricDTO(values, type);
	}

	private DozenDTO buildHistoricDTO(String[] values, LotteryType type) {
		Long concurse = Long.valueOf(values[0]);
		Date dateOfConcurse = convertStringToDate(values[1]);
		List<Integer> dozens = createOrdenedListOfIntegers(values, type);
		return buildDozenDTO(type, concurse, dateOfConcurse, dozens);
	}

	private DozenDTO buildDozenDTO(LotteryType type, Long concurse, Date dateOfConcurse, List<Integer> dozens) {
		if (type == LotteryType.LOTOFACIL)
			return DozenDTO.builder().type(type).concurse(concurse).concurseDate(dateOfConcurse).dozen1(dozens.get(0))
					.dozen2(dozens.get(1)).dozen3(dozens.get(2)).dozen4(dozens.get(3)).dozen5(dozens.get(4))
					.dozen6(dozens.get(5)).dozen7(dozens.get(6)).dozen8(dozens.get(7)).dozen9(dozens.get(8))
					.dozen10(dozens.get(9)).dozen11(dozens.get(10)).dozen12(dozens.get(11)).dozen13(dozens.get(12))
					.dozen14(dozens.get(13)).dozen15(dozens.get(14)).build();
		else if (type == LotteryType.QUINA)
			return DozenDTO.builder().type(type).concurse(concurse).concurseDate(dateOfConcurse).dozen1(dozens.get(0))
					.dozen2(dozens.get(1)).dozen3(dozens.get(2)).dozen4(dozens.get(3)).dozen5(dozens.get(4)).build();
		else
			return DozenDTO.builder().type(type).concurse(concurse).concurseDate(dateOfConcurse).dozen1(dozens.get(0))
					.dozen2(dozens.get(1)).dozen3(dozens.get(2)).dozen4(dozens.get(3)).dozen5(dozens.get(4))
					.dozen6(dozens.get(5)).dozen7(dozens.get(6)).dozen8(dozens.get(7)).dozen9(dozens.get(8))
					.dozen10(dozens.get(9)).dozen11(dozens.get(10)).dozen12(dozens.get(11)).dozen13(dozens.get(12))
					.dozen14(dozens.get(13)).dozen15(dozens.get(14)).dozen16(dozens.get(15)).dozen17(dozens.get(16))
					.dozen18(dozens.get(17)).dozen19(dozens.get(18)).dozen20(dozens.get(19)).build();
	}

	private List<Integer> createOrdenedListOfIntegers(String[] values, LotteryType type) {
		if (type == LotteryType.LOTOFACIL) {
			List<Integer> dozens = asList(valueOf(values[2]), valueOf(values[3]), valueOf(values[4]),
					valueOf(values[5]), valueOf(values[6]), valueOf(values[7]), valueOf(values[8]), valueOf(values[9]),
					valueOf(values[10]), valueOf(values[11]), valueOf(values[12]), valueOf(values[13]),
					valueOf(values[14]), valueOf(values[15]), valueOf(values[16]));
			dozens.sort(naturalOrder());
			return dozens;
		} else if (type == LotteryType.QUINA) {
			List<Integer> dozens = asList(valueOf(values[2]), valueOf(values[3]), valueOf(values[4]),
					valueOf(values[5]), valueOf(values[6]));
			dozens.sort(naturalOrder());
			return dozens;
		} else {
			List<Integer> dozens = asList(valueOf(values[2]), valueOf(values[3]), valueOf(values[4]),
					valueOf(values[5]), valueOf(values[6]), valueOf(values[7]), valueOf(values[8]), valueOf(values[9]),
					valueOf(values[10]), valueOf(values[11]), valueOf(values[12]), valueOf(values[13]),
					valueOf(values[14]), valueOf(values[15]), valueOf(values[16]), valueOf(values[17]),
					valueOf(values[18]), valueOf(values[19]), valueOf(values[20]), valueOf(values[21]));
			dozens.sort(naturalOrder());
			return dozens;
		}
	}

	private Date convertStringToDate(String dateAtString) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		try {
			return format.parse(dateAtString);
		} catch (ParseException e) {
			return EMPTY_DATE;
		}
	}

}
