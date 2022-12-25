package com.johnny.boot.openshift.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LoggerException extends RuntimeException {

	private static final long serialVersionUID = 1112493363728774021L;

	public LoggerException(String msg) {
		super(msg);
	}

	public LoggerException(String msg, Throwable cause) {
		super(msg, cause);

	}

}
