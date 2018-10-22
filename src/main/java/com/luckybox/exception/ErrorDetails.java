package com.luckybox.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private HttpStatus status;

	public ErrorDetails(HttpStatus status, String message, String details) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.details = details;
		this.status = status;
	}
}