package com.rently.rentlyAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public ResponseEntity<String> handleThirdPartyAuthenticationException(AuthenticationException ex) {
		return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
	}


}
