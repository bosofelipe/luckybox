package com.luckybox.domain;

public enum LotteryType {

	LOTOFACIL("lotomania"), LOTOMANIA("lotofacil");

	private String value;

	LotteryType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
