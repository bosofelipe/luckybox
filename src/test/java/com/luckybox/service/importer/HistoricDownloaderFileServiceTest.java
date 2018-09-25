package com.luckybox.service.importer;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileNotFoundException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.luckybox.service.HistoricDownloaderFileService;

import net.lingala.zip4j.core.ZipFile;

public class HistoricDownloaderFileServiceTest {

	private HistoricDownloaderFileService service = new HistoricDownloaderFileService();
	
	@Test
	public void downloadZippedFileAtCaixa() throws Exception {
		ZipFile fileZipped = service.downloadHtmlZippedFileAtCaixa("D_lotfac.zip");
		MatcherAssert.assertThat(fileZipped.isValidZipFile(), equalTo(true));
	}
	
	@Test(expected=FileNotFoundException.class)
	public void errorOnDownloadZippedFileAtInvalidURL() throws Exception {
		ZipFile fileZipped = service.downloadHtmlZippedFileAtCaixa("333.zip");
		MatcherAssert.assertThat(fileZipped.isValidZipFile(), equalTo(false));
	}
	
	
}
