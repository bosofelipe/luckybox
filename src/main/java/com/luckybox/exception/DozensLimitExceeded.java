package com.luckybox.exception;

public class DozensLimitExceeded extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DozensLimitExceeded(String exception) {
		super(exception);
	}

}
