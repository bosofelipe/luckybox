package com.luckybox.constants;

import static java.util.Arrays.asList;

import java.util.List;

public class ConstantsLoto {

	private ConstantsLoto() {}
	
	public static final List<Integer> ALL_NUMBERS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
			18, 19, 20, 21, 22, 23, 24, 25);

	public static final List<Integer> PAIR_NUMBERS = asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24);

	public static final List<Integer> UNPAIRED_NUMBERS = asList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25);

	public static final List<Integer> FIRST_DOZENS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

	public static final List<Integer> SECOND_DOZENS = asList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19);

	public static final List<Integer> THIRD_DOZENS = asList(20, 21, 22, 23, 24, 25);

	public static final List<Integer> FIBONACCI_SEQUENCE = asList(1, 2, 3, 5, 8, 13, 21, 34, 55, 89);

	public static final List<Integer> PRIME = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
			67, 71, 73, 79, 83, 89, 97);

	public static final List<Integer> FIBONACCI_PRIME_SEQUENCE = asList(2, 3, 5, 13, 89);

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
	
	public static final List<Integer> FIRST_QUADRANT = asList(1,2,3,4,5,11,12,13,14,15,21,22,23,24,25,31,32,33,34,35,41,42,43,44,45);
	
	public static final List<Integer> SECOND_QUADRANT= asList(6,7,8,9,10,16,17,18,19,20,26,27,28,29,30,36,37,38,39,40,46,47,48,49,50);
	
	public static final List<Integer> THIRD_QUADRANT = asList(51,52,53,54,55,61,62,63,64,65,71,72,73,74,75,81,82,83,84,85,91,92,93,94,95);
	
	public static final List<Integer> FOURTH_QUADRANT = asList(56,57,58,59,60,66,67,68,69,70,76,77,78,79,80,86,87,88,89,90,96,97,98,99,0);
}
