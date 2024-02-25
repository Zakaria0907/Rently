package com.rently.rentlyAPI.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationException extends org.springframework.security.core.AuthenticationException {

	public AuthenticationException(String message) {
		super(message);
	}
	
}

