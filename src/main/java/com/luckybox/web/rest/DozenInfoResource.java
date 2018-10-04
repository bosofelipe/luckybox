package com.luckybox.web.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckybox.domain.DozenInfo;
import com.luckybox.service.DozenInfoService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/dozeninfo")
public class DozenInfoResource {

	
	@Inject
	private DozenInfoService dozenInfoService;

	@ApiOperation(value="Generate info to dozens of lottery types", notes="")
	@GetMapping(path = "/generate/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<DozenInfo> generate(@PathVariable String type) throws IOException, ZipException {
		return dozenInfoService.generateDozenInfo(type);
	}
}
