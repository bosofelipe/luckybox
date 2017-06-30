package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.DozenInfo;
import com.luckybox.service.DozenInfoService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/dozeninfo")
public class DozenInfoResource {

	
	@Inject
	private DozenInfoService dozenInfoService;

	@GetMapping(path = "/generate", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenInfo> generate() throws IOException, ZipException {
		return dozenInfoService.generateDozenInfo();
	}
}
