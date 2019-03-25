package com.luckybox.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "LineColumnDataset")
public class LineColumnDataset {

	public static final Map<Integer, Integer[]> LOTOFACIL_LINES = Collections
			.unmodifiableMap(new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] { 1, 2, 3, 4, 5 });
					put(2, new Integer[] { 6, 7, 8, 9, 10 });
					put(3, new Integer[] { 11, 12, 13, 14, 15 });
					put(4, new Integer[] { 16, 17, 18, 19, 20 });
					put(5, new Integer[] { 21, 22, 23, 24, 25 });
				}
			});

	public static final Map<Integer, Integer[]> LOTOFACIL_COLUMNS = Collections
			.unmodifiableMap(new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] { 1, 6, 11, 16, 21 });
					put(2, new Integer[] { 2, 7, 12, 17, 22 });
					put(3, new Integer[] { 3, 8, 13, 18, 23 });
					put(4, new Integer[] { 4, 9, 14, 19, 24 });
					put(5, new Integer[] { 5, 10, 15, 20, 25 });
				}
			});

	public static final Map<Integer, Integer[]> LOTOMANIA_LINES = Collections
			.unmodifiableMap(new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
					put(2, new Integer[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 });
					put(3, new Integer[] { 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 });
					put(4, new Integer[] { 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 });
					put(5, new Integer[] { 41, 42, 43, 44, 45, 46, 47, 48, 49, 50 });
					put(6, new Integer[] { 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 });
					put(7, new Integer[] { 61, 62, 63, 64, 65, 66, 67, 68, 69, 70 });
					put(8, new Integer[] { 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 });
					put(9, new Integer[] { 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 });
					put(10, new Integer[] { 91, 92, 93, 94, 95, 96, 97, 98, 99, 0 });
				}
			});

	public static final Map<Integer, Integer[]> LOTOMANIA_COLUMNS = Collections
			.unmodifiableMap(new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] { 1, 11, 21, 31, 41, 51, 61, 71, 81, 91 });
					put(1, new Integer[] { 2, 12, 22, 32, 42, 52, 62, 72, 82, 92 });
					put(1, new Integer[] { 3, 13, 23, 33, 43, 53, 63, 73, 83, 93 });
					put(1, new Integer[] { 4, 14, 24, 34, 44, 54, 64, 74, 84, 94 });
					put(1, new Integer[] { 5, 15, 25, 35, 45, 55, 65, 75, 85, 95 });
					put(1, new Integer[] { 6, 16, 26, 36, 46, 56, 66, 76, 86, 96 });
					put(1, new Integer[] { 7, 17, 27, 37, 47, 57, 67, 77, 87, 97 });
					put(1, new Integer[] { 8, 18, 28, 38, 48, 58, 68, 78, 88, 98 });
					put(1, new Integer[] { 9, 19, 29, 39, 49, 59, 69, 79, 89, 99 });
					put(1, new Integer[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 0 });
				}
			});

	private Integer line1;
	private Integer line2;
	private Integer line3;
	private Integer line4;
	private Integer line5;
	private Integer line6;
	private Integer line7;
	private Integer line8;
	private Integer line9;
	private Integer line10;

	private Integer column1;
	private Integer column2;
	private Integer column3;
	private Integer column4;
	private Integer column5;
	private Integer column6;
	private Integer column7;
	private Integer column8;
	private Integer column9;
	private Integer column10;

	private LotteryType type;
	
	@OneToOne(mappedBy = "dataset")
	private Historic historic;
	
	private Long concurse;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
}
