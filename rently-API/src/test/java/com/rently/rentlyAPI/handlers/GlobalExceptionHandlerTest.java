package com.rently.rentlyAPI.handlers;

import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.exceptions.FileUploadException;
import com.rently.rentlyAPI.exceptions.ObjectValidationException;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    ObjectValidationException mockedObjectValidationException = mock(ObjectValidationException.class);

    AuthenticationException mockedAuthenticationException = mock(AuthenticationException.class);

    EntityNotFoundException mockedEntityNotFoundException = mock(EntityNotFoundException.class);

    OperationNonPermittedException mockedOperationNonPermittedException = mock(OperationNonPermittedException.class);

    DataIntegrityViolationException mockedDataIntegrityViolationException = mock(DataIntegrityViolationException.class);

    FileUploadException mockedFileUploadException = mock(FileUploadException.class);

    Exception mockedException = mock(Exception.class);
    Set<String> mockedSet = mock(Set.class);

    @Test
    public void testHandleExceptionObjectValidationException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedObjectValidationException.getViolationSource()).thenReturn("source");
        when(mockedObjectValidationException.getViolations()).thenReturn(mockedSet);

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleException(mockedObjectValidationException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, testResponseEntity.getStatusCode());
        assertEquals("Object not valid exception has occured", testResponseEntity.getBody().getErrorMessage());
        assertEquals("source", testResponseEntity.getBody().getErrorSource());
        assertEquals(mockedSet, testResponseEntity.getBody().getValidationErrors());

    }
    @Test
    public void testHandleExceptionAuthenticationException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedAuthenticationException.getMessage()).thenReturn("errorMessage");

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleException(mockedAuthenticationException);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, testResponseEntity.getStatusCode());
        assertEquals("errorMessage", testResponseEntity.getBody().getErrorMessage());
        assertEquals("Authentication", testResponseEntity.getBody().getErrorSource());
    }

    @Test
    public void testHandleExceptionEntityNotFoundException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedEntityNotFoundException.getMessage()).thenReturn("errorMessage");

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleException(mockedEntityNotFoundException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, testResponseEntity.getStatusCode());
        assertEquals("errorMessage", testResponseEntity.getBody().getErrorMessage());
    }

    @Test
    public void testHandleExceptionOperationNonPermittedException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedOperationNonPermittedException.getErrorMsg()).thenReturn("errorMessage");

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleException(mockedOperationNonPermittedException);

        // Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE, testResponseEntity.getStatusCode());
        assertEquals("errorMessage", testResponseEntity.getBody().getErrorMessage());
    }

    @Test
    public void testHandleExceptionDataIntegrityViolationException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedDataIntegrityViolationException.getMessage()).thenReturn("errorMessage");

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleException(mockedDataIntegrityViolationException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, testResponseEntity.getStatusCode());
        assertEquals("errorMessage", testResponseEntity.getBody().getErrorMessage());
    }

    @Test
    public void testHandleDisabledException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleDisabledException();

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, testResponseEntity.getStatusCode());
        assertEquals("Cannot access the resource. User is not yet activated", testResponseEntity.getBody().getErrorMessage());
    }

    @Test
    public void testHandleBadCredentialsException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleBadCredentialsException();

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, testResponseEntity.getStatusCode());
        assertEquals("Your e-mail and or password is incorrect", testResponseEntity.getBody().getErrorMessage());
    }

    @Test
    public void testHandleFileUploadException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleFileUploadException(mockedFileUploadException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, testResponseEntity.getStatusCode());
        assertEquals("Failed to upload file", testResponseEntity.getBody().getErrorMessage());
        assertEquals("File Upload", testResponseEntity.getBody().getErrorSource());
    }

    @Test
    public void testHandleHttpMessageNotReadableException() {
        // Arrange
        GlobalExceptionHandler testGlobalExceptionHandler = new GlobalExceptionHandler();
        when(mockedException.getMessage()).thenReturn("errorMessage");

        // Act
        ResponseEntity<ExceptionRepresentation> testResponseEntity = testGlobalExceptionHandler.handleHttpMessageNotReadableException(mockedException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, testResponseEntity.getStatusCode());
        assertEquals("errorMessage", testResponseEntity.getBody().getErrorMessage());
    }


}
