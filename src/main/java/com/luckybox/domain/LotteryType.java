package com.luckybox.domain;

public enum LotteryType {

	LOTOFACIL("lotofacil", 15, "D_lotfac.zip", "D_LOTFAC.HTM"), LOTOMANIA("lotomania", 20, "D_lotoma.zip",
			"D_LOTMAN.HTM"), QUINA("quina", 5, "D_quina.zip", "D_QUINA.HTM");

	private String name;
	private Integer dozens;
	private String zipName;
	private String htmlName;

	LotteryType(String name, Integer dozens, String zipName, String htmlName) {
		this.name = name;
		this.dozens = dozens;
		this.zipName = zipName;
		this.htmlName = htmlName;
	}

	public String getName() {
		return name;
	}

	public Integer getDozens() {
		return dozens;
	}

	public String getZipName() {
		return zipName;
	}

	public String getHtmlName() {
		return htmlName;
	}
}
