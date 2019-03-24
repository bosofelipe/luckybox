package com.luckybox.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "QuadrantDataset")
public class QuadrantDataset {

	private static final Map<Integer, Integer[]> LOTOMANIA_MINI_QUADRANTS = Collections
			.unmodifiableMap(new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] { 1, 2, 11, 12 });
					put(2, new Integer[] { 3, 4, 13, 14 });
					put(3, new Integer[] { 5, 6, 15, 16 });
					put(4, new Integer[] { 7, 8, 17, 18 });
					put(5, new Integer[] { 9, 10, 19, 20 });
					put(6, new Integer[] { 21, 22, 31, 32 });
					put(7, new Integer[] { 23, 24, 33, 34 });
					put(8, new Integer[] { 25, 26, 35, 36 });
					put(9, new Integer[] { 27, 28, 37, 38 });
					put(10, new Integer[] { 29, 30, 39, 40 });
					put(11, new Integer[] { 41, 42, 51, 52 });
					put(12, new Integer[] { 43, 44, 53, 54 });
					put(13, new Integer[] { 45, 46, 55, 56 });
					put(14, new Integer[] { 47, 48, 57, 58 });
					put(15, new Integer[] { 49, 50, 59, 60 });
					put(16, new Integer[] { 61, 62, 71, 72 });
					put(17, new Integer[] { 63, 64, 73, 74 });
					put(18, new Integer[] { 65, 66, 75, 76 });
					put(19, new Integer[] { 67, 68, 77, 78 });
					put(20, new Integer[] { 69, 70, 79, 80 });
					put(21, new Integer[] { 81, 82, 91, 92 });
					put(22, new Integer[] { 83, 84, 93, 94 });
					put(23, new Integer[] { 85, 86, 95, 96 });
					put(24, new Integer[] { 87, 88, 97, 98 });
					put(25, new Integer[] { 89, 90, 99, 0 });
				}
			});

	private Long quadrant1;
	private Long quadrant2;
	private Long quadrant3;
	private Long quadrant4;

	private Long miniQuadrant1;
	private Long miniQuadrant2;
	private Long miniQuadrant3;
	private Long miniQuadrant4;
	private Long miniQuadrant5;
	private Long miniQuadrant6;
	private Long miniQuadrant7;
	private Long miniQuadrant8;
	private Long miniQuadrant9;
	private Long miniQuadrant10;
	private Long miniQuadrant11;
	private Long miniQuadrant12;
	private Long miniQuadrant13;
	private Long miniQuadrant14;
	private Long miniQuadrant15;
	private Long miniQuadrant16;
	private Long miniQuadrant17;
	private Long miniQuadrant18;
	private Long miniQuadrant19;
	private Long miniQuadrant20;
	private Long miniQuadrant21;
	private Long miniQuadrant22;
	private Long miniQuadrant23;
	private Long miniQuadrant24;
	private Long miniQuadrant25;
}
