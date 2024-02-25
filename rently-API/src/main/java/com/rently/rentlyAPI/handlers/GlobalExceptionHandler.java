package com.rently.rentlyAPI.handlers;

import com.rently.rentlyAPI.exceptions.ObjectValidationException;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//Everytime an ObjectValidationException is thrown, this method will be called to handle it
	@ExceptionHandler(ObjectValidationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Object not valid exception has occured")
				.errorSource(e.getViolationSource())
				.validationErrors(e.getViolations())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(representation);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(AuthenticationException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(e.getMessage())
				.errorSource("Authentication")
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(representation);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(representation);
	}

	@ExceptionHandler(OperationNonPermittedException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(e.getErrorMsg())
				.errorSource(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(representation);
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(DataIntegrityViolationException e) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(representation);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ExceptionRepresentation> handleDisabledException() {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Cannot access the resource. User is not yet activated")
				.build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(representation);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionRepresentation> handleBadCredentialsException() {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Your e-mail and or password is incorrect")
				.build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(representation);
	}



}
