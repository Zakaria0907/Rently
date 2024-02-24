package com.rently.rentlyAPI.handlers;

import com.rently.rentlyAPI.exceptions.ObjectValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Everytime an ObjectValidationException is thrown, this method will be called to handle it
	@ExceptionHandler(ObjectValidationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Object not valid exceptioon has occured")
				.errorSource(e.getViolationSource())
				.validationErrors(e.getViolations())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(representation);
	}




}
