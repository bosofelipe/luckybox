package com.luckybox.constants;

import static java.util.Arrays.asList;

import java.util.List;

public class ConstantsLoto {

	public static Integer TOTAL_NUMBERS_LOTO_MIN_PLAY = 15;

	public static Integer TOTAL_NUMBERS_LOTO = 25;

	public static String SITE_RESULTS = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";

	//TODO Parametrizar para pasta tmp
	public static String PATH_LOCAL = "C:/tmp/";

	public static String FILE_ZIP_NAME = "D_LOTFAC.HTM";

	public static String FILE_HTML_DEFAULT_NAME = "D_LOTFAC.HTM";

	public static final List<Integer> ALL_NUMBERS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25);

	public static final List<Integer> PAIR_NUMBERS = asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24);

	public static final List<Integer> UNPAIRED_NUMBERS = asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25);

	public static final List<Integer> FIRST_DOZENS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

	public static final List<Integer> SECOND_DOZENS = asList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19);

	public static final List<Integer> THIRD_DOZENS = asList(20, 21, 22, 23, 24, 25);

	public static final List<Integer> FIBONACCI_SEQUENCE = asList(1, 2, 3, 5, 8, 13, 21);

	public static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23);

	public static final List<Integer> FIRST_COLUMN = asList(1, 6, 11, 16, 21);

	public static final List<Integer> SECOND_COLUMN = asList(2, 7, 12, 17, 22);

	public static final List<Integer> THIRD_COLUMN = asList(3, 8, 13, 18, 23);

	public static final List<Integer> FOURTH_COLUMN = asList(4, 9, 14, 19, 24);

	public static final List<Integer> FIVETH_COLUMN = asList(5, 10, 15, 20, 25);

	public static final List<Integer> FIRST_LINE = asList(1, 2, 3, 4, 5);

	public static final List<Integer> SECOND_LINE = asList(6, 7, 8, 9, 10);

	public static final List<Integer> THIRD_LINE = asList(11, 12, 13, 14, 15);

	public static final List<Integer> FOURTH_LINE = asList(16, 17, 18, 19, 20);

	public static final List<Integer> FIVETH_LINE = asList(21, 22, 23, 24, 25);

	public static final List<Integer> FIRST_NUMBERS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
}
