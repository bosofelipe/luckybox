package com.luckybox.domain;

public enum LotteryType {

	LOTOFACIL("lotofacil", 15, "D_lotfac.zip", "d_lotfac.htm"),
	LOTOMANIA("lotomania", 20, "D_lotoma.zip", "d_lotman.htm"), QUINA("quina", 5, "D_quina.zip", "d_quina.htm"),
	MEGASENA("megasena", 6, "D_megase.zip", "d_mega.htm");

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
