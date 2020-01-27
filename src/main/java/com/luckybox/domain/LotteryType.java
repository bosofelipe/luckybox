package com.luckybox.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum LotteryType {

	LOTOFACIL("lotofacil", 15, 18, "D_lotfac.zip", "d_lotfac.htm"),
	LOTOMANIA("lotomania", 20, 20, "D_lotoma.zip", "d_lotman.htm"), 
	QUINA("quina", 5, 8, "D_quina.zip", "d_quina.htm"),
	MEGASENA("megasena", 6, 15, "D_megase.zip", "d_mega.htm");
	
	private String name;
	private Integer minDozens;
	private Integer maxDozens;
	private String zipName;
	private String htmlName;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinDozens() {
		return minDozens;
	}
	public void setMinDozens(Integer minDozens) {
		this.minDozens = minDozens;
	}
	public Integer getMaxDozens() {
		return maxDozens;
	}
	public void setMaxDozens(Integer maxDozens) {
		this.maxDozens = maxDozens;
	}
	public String getZipName() {
		return zipName;
	}
	public void setZipName(String zipName) {
		this.zipName = zipName;
	}
	public String getHtmlName() {
		return htmlName;
	}
	public void setHtmlName(String htmlName) {
		this.htmlName = htmlName;
	}
	

}
