package com.luckybox.dto;

import java.util.Comparator;

public class HitsDTOComparator implements Comparator<HitsDTO> {

	public int compare(HitsDTO hit1, HitsDTO hit2) {
		return hit2.getHits().compareTo(hit1.getHits());
	}
}