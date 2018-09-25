package com.luckybox.exception;

public class FileReaderException extends RuntimeException {
	private static final long serialVersionUID = 4205582712036293799L;
	
	public FileReaderException() {
        super();
    }
    public FileReaderException(String message) {
        super(message);
    }
    public FileReaderException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public FileReaderException(Throwable throwable) {
        super(throwable);
    }
  }