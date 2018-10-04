package com.luckybox.web.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luckybox.bet.rule.RuleDTO;
import com.luckybox.domain.Bet;
import com.luckybox.dto.BetInfoDTO;
import com.luckybox.dto.DozenDTO;
import com.luckybox.dto.GroupBetMessageDTO;
import com.luckybox.service.BetService;

import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping("/bet")
@Produces(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
@Consumes(value=javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class BetResource {

	@Inject
	private BetService betService;

	@ApiOperation(value="save a list of bets", notes="")
	@PostMapping(path = "/toBet", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<Bet>> toBet(@RequestBody DozenDTO dozenDTO) {
		return new ResponseEntity<List<Bet>>(betService.save(dozenDTO), HttpStatus.OK);
	}
	
	@ApiOperation(value="Validate bets by rules defined", notes="")
	@PostMapping(path = "/validateAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<DozenDTO>> validateBets(@RequestBody List<DozenDTO> dozens) {
		return new ResponseEntity<List<DozenDTO>>(betService.isAlreadyDrawn(dozens), HttpStatus.OK);
	}
	
	@ApiOperation(value="Check if bets are inside rules", notes="")
	@PostMapping(path = "/checkRules", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<List<RuleDTO>> checkRules(@RequestBody List<DozenDTO> dozens) {
		return new ResponseEntity<List<RuleDTO>>(betService.checkRules(dozens), HttpStatus.OK);
	}
	
	@ApiOperation(value="Check if bets are inside rules by type of lottery", notes="")
	@GetMapping(path = "/check/{type}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public List<BetInfoDTO> checkBets(@PathVariable String type) throws IOException, ZipException {
		return betService.checkBets(type);
	}

	/*@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String name = "file";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name + "-uploaded")));
				stream.write(bytes);
				stream.close();
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@ApiOperation(value="Save bets using file path", notes="")
	@RequestMapping(value = "/saveByPath", method = RequestMethod.POST)
	public GroupBetMessageDTO saveBetsByPath(@RequestParam("file") String path) throws IOException {
		return betService.saveBetsByPath(path);
	}
}
