package com.luckybox.service;

import static java.net.CookiePolicy.ACCEPT_ALL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckybox.exception.FileReaderException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Service
@Transactional
public class HistoricDownloaderFileService {
	private static String PATH_LOCAL = System.getProperty("java.io.tmpdir");
	private static String FILE_ZIP_PATH = System.getProperty("java.io.tmpdir") + "/";

	public ZipFile downloadHtmlZippedFileAtCaixa(String caixaURL, String zipName) throws IOException, ZipException {
		downloadZipFile(caixaURL, zipName);
		ZipFile zippedFile = extractZipFile(zipName);
		return zippedFile;
	}

	private File downloadZipFile(String caixaURL, String zipName) throws IOException {
		CookieHandler.setDefault(new CookieManager(null, ACCEPT_ALL));
		URL url = new URL(caixaURL);
		readFileByURL(url, zipName);
		return new File(FILE_ZIP_PATH + zipName);
	}

	private void readFileByURL(URL url, String zipName) throws IOException {
		InputStream is = url.openStream();
		FileOutputStream fos = new FileOutputStream(FILE_ZIP_PATH + zipName);
		readFile(is, fos);
	}

	private void readFile(InputStream is, FileOutputStream fos) throws IOException {
		int umByte = 0;
		try {
			while ((umByte = is.read()) != -1)
				fos.write(umByte);
		} catch (IOException e) {
			throw new FileReaderException("Error on write file", e);
		} finally {
			is.close();
			fos.close();
		}
	}

	private ZipFile extractZipFile(String zipName) {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(FILE_ZIP_PATH + zipName);
			zipFile.extractAll(PATH_LOCAL);
			return zipFile;
		} catch (ZipException e) {
			throw new FileReaderException("Error on extract file", e);
		}
	}
}
