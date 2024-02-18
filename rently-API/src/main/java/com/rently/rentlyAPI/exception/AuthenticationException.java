package com.rently.rentlyAPI.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends org.springframework.security.core.AuthenticationException {
	
	private final HttpStatus httpStatus;
	
	public AuthenticationException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}

