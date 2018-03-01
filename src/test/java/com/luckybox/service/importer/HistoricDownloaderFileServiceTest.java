package com.luckybox.service.importer;

import static org.hamcrest.CoreMatchers.equalTo;

import java.net.UnknownHostException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.service.HistoricDownloaderFileService;

import net.lingala.zip4j.core.ZipFile;

public class HistoricDownloaderFileServiceTest {

	private static final String CAIXA_URL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_lotfac.zip";
	private HistoricDownloaderFileService service = new HistoricDownloaderFileService();
	
	@Test
	public void downloadZippedFileAtCaixa() throws Exception {
		ZipFile fileZipped = service.downloadHtmlZippedFileAtCaixa(CAIXA_URL, "lotofacil.zip");
		MatcherAssert.assertThat(fileZipped.isValidZipFile(), equalTo(true));
	}
	
	@Test(expected=UnknownHostException.class)
	public void errorOnDownloadZippedFileAtInvalidURL() throws Exception {
		ZipFile fileZipped = service.downloadHtmlZippedFileAtCaixa("http://www1.caixa.gov.sbr/lotserias/_arquivos/lotersias/D_lotfac.zip", "lotofacil.zip");
		MatcherAssert.assertThat(fileZipped.isValidZipFile(), equalTo(false));
	}
	
	
}
